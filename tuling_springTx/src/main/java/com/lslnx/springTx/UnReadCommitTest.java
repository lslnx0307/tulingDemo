package com.lslnx.springTx;

import java.sql.*;

/**
 * 未提交读
 * 一个事务可以读取到另外一个事务未提交的数据，会造成脏读
 */
public class UnReadCommitTest {

    static {
        try {
            openConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开sql连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/my_block","root","root");
        return connection;
    }

    /**
     * insert
     * @param accountName
     * @param name
     * @param money
     */
    public static void insert(String accountName, String name, int money){
        try {
            Connection connection = openConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.
                    prepareStatement("insert into account(accountName,user,money) values(?,?,?)");
            preparedStatement.setString(1,accountName);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,money);
            preparedStatement.executeUpdate();
            System.out.println("执行插入");
            Thread.sleep(3000);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void  select(String name,Connection connection){
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from account where user=?");
           preparedStatement.setString(1,name);
           ResultSet resultSet = preparedStatement.executeQuery();
           System.out.println("执行查询");
           while (resultSet.next()){
               for (int i = 1; i <= 4; i++) {
                   System.out.print(resultSet.getString(i) + ",");
               }
               System.out.println();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                insert("0307","lslnx",100);
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500);
                    Connection connection =openConnection();
                    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    select("lslnx",connection);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

}
