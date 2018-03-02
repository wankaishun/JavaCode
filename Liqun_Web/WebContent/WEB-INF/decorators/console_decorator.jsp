<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><sitemesh:write property='title' />利群</title>
	
<c:set var="MODE" value="${1 }" />
	
<c:if test="${MODE == 1 }">

	<link href="${pageContext.request.contextPath}/resources/styles/default.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/icon.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/resources_dev/lib/jquery-3.2.1/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/jquery.easyui.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
</c:if>
<c:if test="${MODE == 2 }">

	<link  href="${pageContext.request.contextPath}/resources/build/styles/all-in-one.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/resources/build/scripts/all-in-one.js" type="text/javascript"></script>
	
</c:if>
	<sitemesh:write property='head' />
</head>
<body> 
<sitemesh:write property='body' />
</body>
</html>