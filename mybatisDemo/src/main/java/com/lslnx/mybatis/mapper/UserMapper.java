package com.lslnx.mybatis.mapper;

import com.lslnx.mybatis.model.User;

public interface UserMapper {

    User selectUser(Integer id);
}
