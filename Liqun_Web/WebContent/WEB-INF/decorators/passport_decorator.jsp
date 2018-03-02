<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><sitemesh:write property='title' />利群</title>
	
<c:set var="MODE" value="${1 }" />
	
<c:if test="${MODE == 1 }">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources_dev/styles/passport.css">

	<style>
	body {
		padding:0px;
	}
	
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources_dev/lib/jquery-3.2.1/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
	<script	src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>

</c:if>
<c:if test="${MODE == 2 }">

	<link  href="${pageContext.request.contextPath}/resources/build/styles/all-in-one.css" rel="stylesheet" type="text/css">
	<script src="${pageContext.request.contextPath}/resources/build/scripts/all-in-one.js" type="text/javascript"></script>
	
</c:if>
	<sitemesh:write property='head' />
</head>
<body>
<sitemesh:write property='body' />
</body>
</html>