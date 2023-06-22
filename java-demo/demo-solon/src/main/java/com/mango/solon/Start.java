package com.mango.solon;

import org.noear.solon.Solon;
import org.noear.solon.SolonApp;

/**
 * @Author: mango
 * @Date: 2022/6/13 1:59 下午
 */
public class Start {
    public static void main(String[] args) {
        SolonApp app = Solon.start(Start.class,args);
        app.get("/",(c)->c.output("Hello world!"));
    }
}
