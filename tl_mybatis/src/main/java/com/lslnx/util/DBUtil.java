package com.lslnx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 13:15
 **/
public class DBUtil {
    /**
     * 打开数据库
     */
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/test";
        username = "root";
        password = "";
    }

    public static Connection openConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            return  connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
