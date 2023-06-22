package com.mango.solon.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

/**
 * @Author: mango
 * @Date: 2022/6/13 2:27 下午
 */
@Controller
public class TestController {
    @Mapping("/hello")
    public String hello(){
        return "hello solon";
    }

}
