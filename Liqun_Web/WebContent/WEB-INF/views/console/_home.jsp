<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<title>利群后台管理系统</title>
	<link href="${pageContext.request.contextPath}/resources/styles/default.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/themes/icon.css" rel="stylesheet" type="text/css"/>
	<script src="${pageContext.request.contextPath}/resources_dev/lib/jquery-3.2.1/jquery-3.2.1.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/Angel_main.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/resources_dev/lib/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">

	var _menus = ${_menus};
		$(function() {

			$('#logout').click(function() {
				$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

					if (r) {
						//location.href = '/ajax/loginout.ashx';
						$.ajax({
							url:'${pageContext.request.contextPath}/passport/logout',
							type:'POST',
							dataType:'JSON',
						}).then(function success(resp){
							console.log('success', resp);
							if(resp.ok == 1){
								window.location.href = "${pageContext.request.contextPath}/passport/login";
								
							}
						}); 
					
					}
				});
			})
		});
		
		

	</script>

</head>
<body  class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	<img src="${pageContext.request.contextPath}/resources/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
	<img src="${pageContext.request.contextPath}/resources/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>
	<div region="north" split="true" border="false" style="overflow: hidden; height: 107px;
		line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="qy_toplrk">
			<tr>
				<td width="401" height="73" align="left" valign="middle" class="qy_top2bg"><img src="${pageContext.request.contextPath}/resources/images/logo.png" width="401" height="73" /></td>
				<td align="right" valign="middle" class="qy_top2bg">
					<table width="175" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><a href="#" onclick="javascript:$('#tabs').tabs('select','首页')" target="_parent"><img src="${pageContext.request.contextPath}/resources/images/qyxx_r3_c21.jpg" width="72" height="73" border="0" alt="" /></a></td>
							<td><a href="#" target="_parent"><img src="${pageContext.request.contextPath}/resources/images/qyxx_r3_c24.jpg" id="logout" width="67" height="73" border="0" alt="你确认要退出吗" /></a></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="27" colspan="2" class="qy_topmenubor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0F5F9">
						<tr>
							<td height="27" align="right" valign="middle" class="qy_menuleftbg" style="width: 880px">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="26" align="right" valign="middle">
											<img src="${pageContext.request.contextPath}/resources/images/340.gif" width="16" height="16" />
										</td>
										<td class="qy_topk2px" colspan="2">
											&nbsp;用户名：<span class="qy_lsfont"><shiro:principal property="account"/></span>
											&nbsp;&nbsp;姓名：<span class="qy_lsfont"><shiro:principal property="realName"/></span>&nbsp;&nbsp;登陆时间：<fmt:formatDate value="${sessionScope['com.liqun.web.WebConsts.LOGGED_IN_TIME']}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;
											<span style="color: red"></span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>






	<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
		<div class="footer"></div>
	</div>
	<div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
			<div id="nav">
		
				
			</div>

	</div>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs"  fit="true" border="false" >

			<div title="首页" style="padding:10px;">
				<div style="font-size:40px;color:#1E6FCA;position: absolute;left:35%;top:40%">欢迎进入<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;利群发票管理系统</div>
				
			</div>

		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
	</div>


</body>
</html>