package com.example.demo.mapper.test2;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface Park2_Mapper {



    //测试表
    @Select("select * from user")
    @Results(value = {
            @Result( column="ID", jdbcType= JdbcType.INTEGER, property="id"),
            @Result( column="Name", jdbcType=JdbcType.VARCHAR, property="name"),
    })
    List<Map> getList();
}
