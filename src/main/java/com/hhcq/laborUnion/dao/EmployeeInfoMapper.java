package com.hhcq.laborUnion.dao;

import com.hhcq.laborUnion.domain.EmployeeInfo;

public interface EmployeeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    EmployeeInfo selectByPrimaryKey(String id);
    
   // EmployeeInfo selectByOpenId(String OpenId);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);
}