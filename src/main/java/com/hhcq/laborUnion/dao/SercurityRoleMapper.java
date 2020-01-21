package com.hhcq.laborUnion.dao;

import java.util.List;

import com.hhcq.laborUnion.domain.SercurityRole;

public interface SercurityRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SercurityRole record);

    int insertSelective(SercurityRole record);

    SercurityRole selectByPrimaryKey(Integer id);
    
    List<SercurityRole> selectByUserName(String userName);

    int updateByPrimaryKeySelective(SercurityRole record);

    int updateByPrimaryKey(SercurityRole record);
}