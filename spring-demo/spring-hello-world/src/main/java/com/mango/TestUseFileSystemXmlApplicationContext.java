package com.mango;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * test hello spring use FileSystemXmlApplicationContext
 *
 * @author mango
 * @since 2023/06/18
 */
public class TestUseFileSystemXmlApplicationContext {
    public static void main(String[] args) {
        // beans.xml需要在project root path下
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("beans.xml");
        HelloSpring helloBean = (HelloSpring) applicationContext.getBean("helloSpring");
        helloBean.hello();
    }
}

/**
 * 打印：
 * hello spring
 */