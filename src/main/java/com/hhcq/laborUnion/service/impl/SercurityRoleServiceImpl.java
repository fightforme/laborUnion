package com.hhcq.laborUnion.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhcq.laborUnion.dao.SercurityRoleMapper;
import com.hhcq.laborUnion.domain.SercurityRole;
import com.hhcq.laborUnion.service.SercurityRoleService;

@Service
public class SercurityRoleServiceImpl implements SercurityRoleService {

	@Autowired
	private SercurityRoleMapper sercurityRoleMapper;
	public List<String> getRoleNameByUserName(String userName) {
		List<SercurityRole> sercurityRoles = sercurityRoleMapper.selectByUserName(userName);
		List<String> roleNames = new ArrayList<String>();
		for (SercurityRole sercurityRole : sercurityRoles) {
			roleNames.add(sercurityRole.getRoleName());
		}
		return roleNames;
	}
}
