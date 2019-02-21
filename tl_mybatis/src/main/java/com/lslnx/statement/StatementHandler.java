package com.lslnx.statement;

import com.lslnx.binding.MapperMethod;
import com.lslnx.result.DefaultResultSetHandler;
import com.lslnx.result.ResultSetHandler;
import com.lslnx.session.Configuration;
import com.lslnx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 11:14
 **/
public class StatementHandler {

    private Configuration configuration;

    private ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandler = new DefaultResultSetHandler();
    }

    public StatementHandler() {

    }

    public <T> T query(MapperMethod method, Object param) throws Exception {
        Connection connection = DBUtil.openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(method.getSql(), Integer.parseInt(String.valueOf(param))));
        preparedStatement.execute();
        return resultSetHandler.handle(preparedStatement, method);

    }

}
