package com.lslnx.executor;

import com.lslnx.binding.MapperMethod;
import com.lslnx.session.Configuration;
import com.lslnx.statement.StatementHandler;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 13:50
 **/
public class SimpleExecutor implements Executor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperMethod mapperMethod, Object param) throws Exception {
        StatementHandler statementHandler = new StatementHandler(configuration);
        return statementHandler.query(mapperMethod, param);
    }
}
