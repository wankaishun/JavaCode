<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>RRS Health Web</title>
</head>
<body>
	<h4>利群</h4>
	
	<hr>
	
	<a href="${pageContext.request.contextPath}/console">进入控制台...</a>
	<a href="${pageContext.request.contextPath}/passport/login?redirect_uri=/console">管理员登录...</a>
	<a href="${pageContext.request.contextPath}/passport/logout">注销</a>
</body>
</html>