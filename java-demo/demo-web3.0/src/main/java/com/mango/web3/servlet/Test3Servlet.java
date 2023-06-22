package com.mango.web3.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @Author: mango
 * @Date: 2022/12/30 3:56 PM
 */
@WebServlet("/test/Servlet")
public class Test3Servlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Test3Servlet init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Test3Servlet service");
        servletResponse.getWriter().println("hello Servlet!!!");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("Test3Servlet destroy");
    }
}
