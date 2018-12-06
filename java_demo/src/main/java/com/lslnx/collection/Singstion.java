package com.lslnx.collection;

import sun.security.jca.GetInstance;

/**
 * 单例模式 懒汉 非线程安全
 */
public class Singstion {

    private static Singstion singstion;
    private Singstion() {

    }

    public static Singstion getInstance(){
        return singstion = new Singstion();
    }
}
