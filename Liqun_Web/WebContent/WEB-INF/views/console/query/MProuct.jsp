<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统用户一览</title>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
</head>
<body class="easyui-layout">
	<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
			
					

				
						&nbsp;&nbsp;&nbsp;<label>商品编号:</label> <input id="spbh" class="easyui-textbox">
					
					
				
					
				
							&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="getData()"
							data-options="iconCls:'icon-search'">查询</a>
							
							
								
						
						
					
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
						<th field="spid" width="100">自编号</th>
						<th field="spbh" width="100">商品编码</th> 
						<th field="spm" width="100">商品名</th>
						<th field="spgg" width="100">规格型号</th>
						<th field="spdw" width="100">单位</th>
						<th field="bhsdj" width="100">不含税单价</th>
						<th field="hsdj" width="100">含税单价</th>
						<th field="sl" width="100">税率</th>
						<th field="spbm" width="100">商品编码</th>
						<th field="yhzc" width="100">是否享受优惠政策</th>
						<th field="yhzclx" width="100">优惠政策类型</th>
						<th field="spzt" width="100">商品状态</th>
						<th field="zhgxsj" width="100">最后一次更新时间</th>
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
								url : '${pageContext.request.contextPath}/api/MProuct/MProuctList',
								method : 'GET'
							});
			
		 
		
			
			
			
			  
		

		});
		
		

	
		
		
		
		
		
		
		
		
		
		
		//查询
		function getData() {
			 
			$('#dataTable')
					.datagrid(
							{
								 url : '${pageContext.request.contextPath}/api/MProuct/MProuctList?fptqm=' 
									 
										 + $('#FPTQM').val()
										+ '&fplsh='
										+ $('#FPLSH').val()
										+ '&gfmc='
										+ $('#GFMC').val()
										
										+ '&gfsh='
										+ $('#GFSH').val()
		
										+ '&starttime='
										+ $('#starttime').val()
										
										+ '&endtime='
										+ $('#endtime').val()
										
										
										
										+ '&djzt='
										+ $('#DJZT').val() + '',
										
									
								method : 'GET',
							});
			
		}
		  
		  
		
		
		
		
		
		  
		

		
		/* //操作行控制
		function formatOper(val, row, index) {
			//表格中按钮控制 '<shiro:hasPermission name="role:add1"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit('+index+')">修改</a></shiro:hasPermission>';  
			return '<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="edit('
					+ val
					+ ')">修改</a>&nbsp;&nbsp;<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="operate('
					+ val
					+ ',1,'
					+ row.flag
					+ ')">'
					+ (row.flag == "1" ? "禁用" : "启用")
					+ '</a>&nbsp;&nbsp;<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="operate('
					+ val + ',2)">重置密码</a>';
		}; */

	/* 	function formatFlag(val, row, index) {
			return val == "1" ? "正常" : "已禁用";
		} */

		 
	</script>
</body>
</html>
