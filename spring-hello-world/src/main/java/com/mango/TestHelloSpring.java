package com.mango;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * test hello spring
 *
 * @author mango
 * @since 2023/06/18
 */
public class TestHelloSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        HelloSpring helloBean = (HelloSpring) applicationContext.getBean("helloSpring");
        helloBean.hello();
    }
}

/**
 * 打印：
 * hello spring
 */