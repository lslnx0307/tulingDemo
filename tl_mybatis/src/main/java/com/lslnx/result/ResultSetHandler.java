package com.lslnx.result;

import com.lslnx.binding.MapperMethod;

import java.sql.PreparedStatement;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 13:20
 **/
public interface ResultSetHandler {
    public <T> T handle(PreparedStatement preparedStatement, MapperMethod mapperMethod) throws Exception;
}
