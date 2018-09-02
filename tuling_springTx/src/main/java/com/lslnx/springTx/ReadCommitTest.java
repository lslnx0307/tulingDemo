package com.lslnx.springTx;

import java.sql.*;

/**
 * 已提交读
 * 一个事务只可以读取到另外一个事务已提交的数据
 */
public class ReadCommitTest {

    static {
        try {
            openConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Object lock= new Object();

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
            PreparedStatement preparedStatement = connection.
                    prepareStatement("insert into account(accountName,user,money) values(?,?,?)");
            preparedStatement.setString(1,accountName);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,money);
            preparedStatement.executeUpdate();
            System.out.println("执行插入");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(String user) {
        try {
            Connection conn = openConnection();
            PreparedStatement prepare = conn.
                    prepareStatement("UPDATE account SET money= money+1 where user=?");
            prepare.setString(1, user);
            prepare.executeUpdate();
            conn.close();
            System.out.println("执行修改成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    public static void main(String args[]){
         Thread insert = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                insert("0307","lslnx",100);
            }
        });

         Thread select = new Thread(new Runnable() {
            public void run() {
                try {
                    Connection connection = openConnection();
                    //每个select都是单独的事务，设置为flase后，两个查询就会在同一个事务里了
                    connection.setAutoCommit(false);
                    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    select("lslnx",connection);
                    synchronized (lock){
                       lock.notify();
                    }
                    Thread.sleep(500);
                    select("lslnx",connection);
                    connection.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        insert.start();
        select.start();
        try {
            insert.join();
            select.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
