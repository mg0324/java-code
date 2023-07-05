package com.mango.bean;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean的三种获取方式
 *
 * @author mango
 * @since 2023/07/04
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 通过ID获取Bean
        TestService testService1 = (TestService) applicationContext.getBean("testService");
        System.out.println(testService1.hashCode());
        System.out.println(testService1.sayHello("by name"));
        // 通过ID加ClassType获取Bean
        TestService testService2 = applicationContext.getBean("testService",TestService.class);
        System.out.println(testService2.hashCode());
        System.out.println(testService1.sayHello("by name and type"));
        // 通过ClassType获取Bean
        TestService testService3 = applicationContext.getBean(TestService.class);
        System.out.println(testService3.hashCode());
        System.out.println(testService1.sayHello("by type"));
    }
}

/**
 * 打印：
 * 1773283386
 * hello by name
 * 1773283386
 * hello by name and type
 * 1773283386
 * hello by type
 */