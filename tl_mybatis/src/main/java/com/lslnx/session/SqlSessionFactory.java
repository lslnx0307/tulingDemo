package com.lslnx.session;

import com.lslnx.executor.SimpleExecutor;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 11:38
 **/
public class SqlSessionFactory {

    public SqlSession openSession(Configuration configuration) {
        return new DefaultSqlSession(configuration, new SimpleExecutor(configuration));
    }
}
