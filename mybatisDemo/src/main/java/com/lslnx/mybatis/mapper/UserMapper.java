package com.lslnx.mybatis.mapper;

import com.lslnx.mybatis.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("select * from user where id = #{id}")
    User selectUser(Integer id);
}
