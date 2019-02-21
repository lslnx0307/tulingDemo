package com.lslnx.executor;


import com.lslnx.binding.MapperMethod;

/**
 * 定义执行器接口
 */
public interface Executor {
    <T> T query(MapperMethod mapperMethod, Object param) throws Exception;
}
