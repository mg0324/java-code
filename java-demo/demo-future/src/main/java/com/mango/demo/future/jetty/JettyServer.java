package com.mango.demo.future.jetty;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @Author: mango
 * @Date: 2022/7/29 9:45 下午
 */
public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server=new Server(8000);
        // 添加静态资源服务处理器
        // 资源目录
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("/Users/mango");
        resourceHandler.setStylesheet("");
        //设置静态目录路径
        ContextHandler staticContextHandler = new ContextHandler();
        staticContextHandler.setContextPath("/dir");
        staticContextHandler.setHandler(resourceHandler);

        // 添加处理servlet的handler
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setGzipHandler(new GzipHandler());
        servletContextHandler.setSecurityHandler(new ConstraintSecurityHandler());
        servletContextHandler.setSessionHandler(new SessionHandler());
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet((Class<? extends Servlet>) HelloServlet.class,"/hello");

        // 组装处理器
        HandlerList handlers = new HandlerList();
        handlers.addHandler(staticContextHandler);
        handlers.addHandler(servletContextHandler);

        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
