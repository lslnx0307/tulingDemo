package com.lslnx.springIoc;


public class Person {

    String name;
    int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"
                + this.age+" ;"+this.name
                +"}";
    }

    public static Person bulid(String type){
        if(type.equals("A")){
            return new Person("A",12);
        }else if(type.equals("B")){
            return new Person(type,14);
        }else{
            throw new IllegalArgumentException("type must A or B");
        }
    }
}
