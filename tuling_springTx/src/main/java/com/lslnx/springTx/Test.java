package com.lslnx.springTx;

public class Test {

    static Object lock = new Object();

    public synchronized void test() {
        System.out.println("test开始..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test结束..");
    }

    public  void test1() {
        synchronized (lock){
            System.out.println("test开始..");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test结束..");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            Test sync = new Test();
            sync.test1();
        }
    }
}
