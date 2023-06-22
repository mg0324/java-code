package com.mango.web3.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @Author: mango
 * @Date: 2022/12/30 3:55 PM
 */
@WebServlet("/test/GenericServlet")
public class Test2Servlet extends GenericServlet {
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println("hello GenericServlet!!!");
    }
}
