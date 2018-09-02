package com.lslnx.springTx;

import java.sql.*;

/**
 * 事务最高隔离级别Connection.TRANSACTION_SERIALIZABLE 序列化
 * 可以防止脏读，幻读，不可重复读，性能差
 *
 */
public class SerializableCommitTest {

    static{
        try {
            openConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Object lock = new Object();

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

    public static void insert(String accountName,String name,int money){
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

    public static void select(String name,Connection connection){
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


    public static Thread run(Runnable runnable) {
        Thread thread1 = new Thread(runnable);
        thread1.start();
        return thread1;
    }


    public static void main(String args[]) throws InterruptedException {
        Thread t1 = run(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    insert("0307","lslnx",100);
                }
            }
        });

        Thread t2 = run(new Runnable() {
            public void run() {
                try {
                    Connection connection = openConnection();
                    connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                    connection.setAutoCommit(false);
                    select("lslnx",connection);
                    synchronized (lock){
                        lock.notify();
                    }
                    Thread.sleep(3000);
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

        t1.join();
        t2.join();
    }
}
