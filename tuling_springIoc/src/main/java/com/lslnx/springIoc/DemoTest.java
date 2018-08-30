package com.lslnx.springIoc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {

    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Person person = (Person) context.getBean(Person.class);
        System.out.println(person.toString());
    }
}
