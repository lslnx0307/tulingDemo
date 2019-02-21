package com.lslnx.session;

import com.lslnx.binding.MapperMethod;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 11:40
 **/
public interface SqlSession<T> {

    <T> T selectOne(MapperMethod mapperMethod, Object param) throws Exception;
}
