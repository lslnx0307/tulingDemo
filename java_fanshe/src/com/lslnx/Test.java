package com.lslnx;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;
        //获取bean的声明字段s
        Field[] declaredFields = studentClass.getDeclaredFields();
        System.out.println();
    }
}
