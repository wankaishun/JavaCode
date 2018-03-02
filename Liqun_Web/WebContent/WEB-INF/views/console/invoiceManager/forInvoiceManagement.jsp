<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发票换开</title>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
</head>
<body class="easyui-layout">
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						&nbsp;&nbsp;&nbsp;<label>提取号：</label> <input id="FPTQM" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>流水号:</label> <input id="FPLSH" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>购方名:</label> <input id="GFMC" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>购方税号:</label> <input id="GFSH" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>请选择发票类型:</label> 
						<select class="easyui-combobox" id="mergeType"
							panelHeight="auto" data-options="editable:false" style="width: 60px">
				 			<c:forEach var="sysDict" items="${sysDictList}">
								<option value="${sysDict.key}">${sysDict.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="wu-toolbar-search">
						&nbsp;&nbsp;&nbsp;<label>发票状态：</label> 
						<select class="easyui-combobox" id="mergeType"
							panelHeight="auto" data-options="editable:false" style="width: 250px">
				 			<c:forEach var="sysDict" items="${sysDList}">
								<option value="${sysDict.key}">${sysDict.value}</option>
							</c:forEach>
						</select>
							&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="getData()"
							data-options="iconCls:'icon-search'">查询</a>
							<shiro:hasPermission name="forInvoice">
							<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="merge()" data-options="iconCls:'icon-reload'"
							style="width: 70px;">换开</a>
							</shiro:hasPermission>
					</div>
				</div>
			</div>
		</div>

<span>单据信息</span>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
						<th field="ck" checkbox="true" singleSelect:true></th>
						<th field="fplx" width="70" replace="专票_0,普票_2,电子发票_51">发票类型</th>
						<th field="djzt" width="70">单据状态</th> 
						<th field="fptqm" width="150">提取码</th>
						<th field="fplsh" width="150">流水号</th>
						<th field="fpdm" width="150">发票代码</th>
						<th field="fphm" width="150">发票号码</th>
						<th field="kpsj" width="150">开票时间</th>
						<th field="qybh" width="150">企业编号</th>
						<th field="posId" width="150">pos编号</th>		
						<th field="gfmc" width="150">购方名称</th>
						<th field="gfsh" width="150">购方税号</th>
						<th field="gfyhzh" width="150">购方银行账号</th>
						<th field="gfdzdh" width="150">购方地址电话</th>
						<th field="xfmc" width="150">销方名称</th>
						<th field="xfsh" width="150">销方税号</th>
						<th field="xfyhzh" width="150">销方银行账号</th>
						<th field="xfdzdh" width="150">销方地址电话</th>
						<th field="pdfxzdz" width="150">pdf下载地址</th>
						<th field="hsje" width="150">含税金额</th>
						<th field="hjje" width="150">合计金额</th>
						<th field="hjse" width="150">合计税额</th>
						<th field="kpr" width="80">开票人</th>
						<th field="skr" width="80">收款人</th>
						<th field="fhr" width="80">复核人</th>
						<th field="sjh" width="150">手机号</th>
						<th field="email" width="150">邮箱</th>					
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script>
		// 初始化
		/* var userId = ""; */
		var condition;
		$(function() {
			dg_userlist = $('#dataTable')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/api/forInv/forInvoiceList',
								method : 'GET'
							});
		});
		

		//查询
		function getData() {
			/*  condition=new Condition($('#FPTQM').val(),$('#FPLSH').val(),$('#GFMC').val(),$('#GFSH').val(),$('#starttime').val(),$('#endtime').val(),djzt);//存放查询数据 */
			$('#dataTable')
					.datagrid(
							{
								 url : '${pageContext.request.contextPath}/api/forInv/forInvoiceList?fptqm=' 								
										+ $('#FPTQM').val()
										+ '&fplsh='
										+ $('#FPLSH').val()
										+ '&gfmc='
										+ $('#GFMC').val()
										+ '&gfsh='
										+ $('#GFSH').val()
										+ '&djzt='
										+ $('#DJZT').val() + '',
								method : 'POST',
							});
		}
	</script>
</body>
</html>
