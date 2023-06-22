<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP页面</title>
</head>
<body style="color:red;">
	这是我的第一个JSP页面。
    <%
       Date date = new Date();
       out.println( "<h2 align=\"center\">" +date.toString()+"</h2>");
    %>
</body>
</html>