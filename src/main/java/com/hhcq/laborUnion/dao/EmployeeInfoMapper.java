package com.hhcq.laborUnion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhcq.laborUnion.domain.EmployeeInfo;

public interface EmployeeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    EmployeeInfo selectByPrimaryKey(String id);
    
    EmployeeInfo selectByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);

    List<EmployeeInfo> selectByUserName(@Param("userName") String userName);
    
    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);
}