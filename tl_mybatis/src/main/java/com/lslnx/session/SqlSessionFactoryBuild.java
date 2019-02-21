package com.lslnx.session;

import java.io.IOException;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 11:39
 **/
public class SqlSessionFactoryBuild {

    public SqlSessionFactory build(Configuration configuration) throws IOException {
        configuration.loadConfigurations();
        return new SqlSessionFactory();
    }
}
