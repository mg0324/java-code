package com.mango.di;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * di注入
 *
 * @author mango
 * @since 2023/08/07
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student xg = (Student) applicationContext.getBean("student");
        System.out.println(xg);

    }
}

/**
 * 打印：
 * Student(name=小刚, age=20, birthday=Mon Mar 01 00:00:00 CST 1993, teacher=Teacher(name=王老师, sex=1))
 */