/**
* @Title: EmployeeInfoService.java
* @Package com.hhcq.laborUnion.service
* @Description: TODO(用一句话描述该文件做什么)
* @author 贺鸿才
* @date 2020年1月17日
* @version V1.0
*/
package com.hhcq.laborUnion.service;

import com.hhcq.laborUnion.domain.EmployeeInfo;

/**
* @ClassName: EmployeeInfoService
* @Description: 员工信息服务接口
* @author 贺鸿才
* @date 2020年1月17日
*
*/
public interface EmployeeInfoService {
	/**
	 * 根据openId获取员工信息
	 */
	public EmployeeInfo getEmployeeInfoByOpenId(String openId);
	
	/**
	 * 根据用户名和密码查询用户
	 * */
	public EmployeeInfo getEmployeeInfoByUsernameAndPassword(String userName,String password);
	
	/**
	 * 根据用户名查询用户
	 * */
	public EmployeeInfo getEmployeeInfoByUsername(String userName);
}
