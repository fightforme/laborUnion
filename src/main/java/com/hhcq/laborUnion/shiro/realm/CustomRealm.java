package com.hhcq.laborUnion.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.hhcq.laborUnion.domain.EmployeeInfo;
import com.hhcq.laborUnion.service.EmployeeInfoService;
import com.hhcq.laborUnion.service.SercurityRoleService;

public class CustomRealm extends AuthorizingRealm {

	@Resource
	private SercurityRoleService sercurityRoleService;
	
	@Resource
	private EmployeeInfoService employeeInfoService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	    //1.从主体传过来的认证信息中，获取用户名
	    String userName = (String) principalCollection.getPrimaryPrincipal();
	    
	    //2.从数据库和缓存中获取角色数据
	    Set<String> roles = getRolesByUserName(userName);
	    
	    Set<String> permissions = getPermissionsByUserName(userName);
	    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
	    simpleAuthorizationInfo.setStringPermissions(permissions);
	    simpleAuthorizationInfo.setRoles(roles);
	    return simpleAuthorizationInfo;
	}
	
	private Set<String> getPermissionsByUserName(String userName) {
	    Set<String> sets = new HashSet<String>();
	    sets.add("user:delete");
	    sets.add("user:add");
	    return sets;
	}
	
	private Set<String> getRolesByUserName(String userName) {
	    List<String> list = sercurityRoleService.getRoleNameByUserName(userName);
	    Set<String> sets = new HashSet<String>(list);
	    return sets;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
	    //1.从主体传过来的认证信息中，获取用户名
	    String userName = (String) authenticationToken.getPrincipal();
	    //2.通过用户名到数据库中获取凭证
	    String password = getPasswordByUserName(userName);
	    if(password==null){
	        return null;
	    }
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"customRealm");
	    authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
	    return authenticationInfo;
	}

	/**
	 * 模拟数据库查询凭证
	 * @param userName
	 * @return
	 */
	private String getPasswordByUserName(String userName) {
		EmployeeInfo user = employeeInfoService.getEmployeeInfoByUsername(userName);
	    if(user!=null){
	        return user.getPassword();
	    }
	    return null;
	}
}
