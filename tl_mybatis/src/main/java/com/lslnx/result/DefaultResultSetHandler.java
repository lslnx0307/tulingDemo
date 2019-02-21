package com.lslnx.result;

import com.lslnx.binding.MapperMethod;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: tulingDemo
 * @description: 拿出结果，进行反射赋值
 * @author: v-jasperli
 * @create: 2019-02-21 13:21
 **/
public class DefaultResultSetHandler implements ResultSetHandler{

    @Override
    public <T> T handle(PreparedStatement preparedStatement, MapperMethod mapperMethod) throws Exception {
        Object resultObj = new DefaultObjectFactory().create(mapperMethod.getType());
        ResultSet resultSet = preparedStatement.getResultSet();
        if (resultSet.next()) {
            int i = 0;
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, resultSet, i);
            }
        }
        return (T)resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet resultSet, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCaptal(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field, resultSet));
    }

    private String upperCaptal(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(String.class == type){
            return rs.getString(field.getName());
        }
        if(Long.class==type){
            return  rs.getLong(field.getName());
        }
        return rs.getString(field.getName());
    }
}
