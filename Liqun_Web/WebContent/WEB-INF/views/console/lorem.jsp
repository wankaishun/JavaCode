<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
</head>
<body>
	<div style="padding:10px">
		<p style="font-size:14px">${title}</p>
		<ul>
			<c:forEach items="${sentences}" var="sentence"><li>${sentence}</li></c:forEach>
		</ul>
	</div>
	
</body>
</html>