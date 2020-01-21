/**
* @Title: EmployeeInfoServiceImpl.java
* @Package com.hhcq.laborUnion.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author 贺鸿才
* @date 2020年1月17日
* @version V1.0
*/
package com.hhcq.laborUnion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhcq.laborUnion.dao.EmployeeInfoMapper;
import com.hhcq.laborUnion.domain.EmployeeInfo;
import com.hhcq.laborUnion.service.EmployeeInfoService;

/**
* @ClassName: EmployeeInfoServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 贺鸿才
* @date 2020年1月17日
*
*/
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	public EmployeeInfo getEmployeeInfoByOpenId(String openId) {
		return employeeInfoMapper.selectByPrimaryKey(openId);
	}

	public EmployeeInfo getEmployeeInfoByUsernameAndPassword(String userName,String password) {
		return employeeInfoMapper.selectByUserNameAndPassword(userName, password);
	}

	public EmployeeInfo getEmployeeInfoByUsername(String userName) {
		return employeeInfoMapper.selectByUserName(userName).get(0);
	}

}
