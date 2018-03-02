<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>您无权访问该页</title>
</head>
<body>
	<div style="padding:10px">
		<p style="font-size:14px">您无权访问该页</p>
		<ul>
			<li>请联系系统管理员获得授权</li>
			<li>请尝试<a href="${pageContext.request.contextPath}/passport/logout">注销</a>，然后以其他身份登录系统...</li>
		</ul>
	</div>
	
	
</body>
</html>