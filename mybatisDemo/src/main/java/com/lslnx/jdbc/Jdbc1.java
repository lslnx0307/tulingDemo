package com.lslnx.jdbc;



import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 传统的执行insert jdbc操作
 */
@Slf4j
public class Jdbc1 {


    public static void main(String args[]) throws SQLException {
        User user = select(3);
        log.info("userid:{},name:{}",user.getId(),user.getName());
    }

    public static void insert() throws SQLException {
        String sql = "insert into user(name,remark,age) value (?,?,?)";
        Connection connection = DbUtil.openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"lslnx");
        preparedStatement.setString(2,"小白");
        preparedStatement.setInt(3,26);
        preparedStatement.executeUpdate();
        System.out.println("insert success");
        connection.close();
    }


    public static User select(int id) throws SQLException {
        String sql = "select * from user where id = ?";
        Connection connection = DbUtil.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(2);
                User user = new User();
                user.setId(id);
                user.setName(name);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection !=null){
                connection.close();
            }
        }
        return null;
    }
}
