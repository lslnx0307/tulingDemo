package com.lslnx.springTx;


import java.sql.*;

/**
 *
 * Connection.TRANSACTION_REPEATABLE_READ
 * 可重复读，在一个事物中同一SQL语句 无论执行多少次都会得到相同的结果
 */
public class ReadRepeatableTest {

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

    public static Thread run(Runnable runnable){
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static void main(String args[]){
        Thread t1 = run(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                update("lslnx");
            }
        });

        Thread t2 = run(new Runnable() {
            public void run() {
                try {
                    Connection connection = openConnection();
                    //要保持同一个事务中的查询结果完全一致需要Connection.TRANSACTION_REPEATABLE_READ级别的事务隔离
                    connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    connection.setAutoCommit(false);
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
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
