package com.lslnx.session;

import com.lslnx.binding.MapperMethod;
import com.lslnx.binding.MapperProxy;
import com.lslnx.executor.Executor;

import java.lang.reflect.Proxy;

/**
 * @program: tulingDemo
 * @description: sqlSession的实现类
 * @author: v-jasperli
 * @create: 2019-02-21 11:42
 **/
public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;
    private Executor executor;

    public Configuration getConfiguration() {
        return configuration;
    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMappers(Class<T> type){
        return (T)Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type}, new MapperProxy<>(this,type));
    }

    @Override
    public Object selectOne(MapperMethod mapperMethod, Object param) throws Exception {
        return executor.query(mapperMethod, param);
    }
}
