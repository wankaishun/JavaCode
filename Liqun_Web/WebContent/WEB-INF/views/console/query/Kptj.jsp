<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path =  request.getContextPath();%>
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
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">

			

					<div class="wu-toolbar-search">
	
					&nbsp;&nbsp;&nbsp;<label>开单时间始</label><span>:</span>
					

						<input type="text" class="easyui-datebox" id="starttime"
							name="starttime"> 
							
							&nbsp;&nbsp;&nbsp;<label>开单时间末</label>
						
						<input type="text" class="easyui-datebox" id="endtime" name="endtime"> 
						
	&nbsp;&nbsp;&nbsp;<label>发票类型：</label>
		<select class="easyui-combobox" id="fplx" panelHeight="auto" data-options="editable:false" style="width: 100px">
			<option value="0">蓝字发票</option>
			<option value="1">红字发票</option>
		</select>		
						&nbsp;&nbsp;&nbsp;<label>开票项目:</label> <input id="kpxm" class="easyui-linkbutton" onclick="jjj()">
						
						<br /> <br />
					</div>
					<div class="wu-toolbar-search">
					<!-- &nbsp;&nbsp;&nbsp;<label>开单时间始</label><span>:</span>
					

						<input type="text" class="easyui-datebox" id="starttime"
							name="starttime"> 
							
							&nbsp;&nbsp;&nbsp;<label>开单时间末</label>
						
						<input type="text" class="easyui-datebox" id="endtime" name="endtime">  -->
						&nbsp;&nbsp;&nbsp;<label>购方名:</label> <input id="gfmc" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>购方税号:</label> <input id="gfsh" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>销方名:</label> <input id="xfmc" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>销方税号:</label> <input id="xfsh" class="easyui-textbox">
							&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="getData()"
							data-options="iconCls:'icon-search'">查询</a>
							
							
								<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="exportExcel()" data-options="iconCls:'icon-reload'"
							style="width: 70px;">导出</a>
						
						<a href="#" class="easyui-linkbutton c2" style="width: 60px;">返回</a>
					</div>
				</div>
			</div>
		</div>

<span>统计信息</span>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
						<th field="tjlx" width="120">统计类型</th>
						<th field="hj" width="120">合计</th>
						<th field="shuilv1" width="120">17%</th>
						<th field="shuilv2" width="120">13%</th>
						<th field="shuilv3" width="120">11%</th>
						<th field="shuilv4" width="120">6%</th>
						<th field="shuilv5" width="120">4%</th>
						<th field="shuilv6" width="120">3%</th>
						<th field="shuilv7" width="120">其他</th>
					</tr>
				</thead>
				
				<!-- <tr>
					<td field="hsje" width="120">销项正数含税金额</td>
					<td field="hsje" width="120" id="1"></td> 
					<td field="hsje" width="120" id="2"></td>
					<td field="hsje" width="120" id="3"></td>
					<td field="hsje" width="120" id="4"></td>
					<td field="hsje" width="120" id="5"></td>
					<td field="hsje" width="120" id="6"></td>
					<td field="hsje" width="120" id="7"></td>
					<td field="hsje" width="120" id="8"></td>
				</tr> -->
				
				<!--  <tr>
					<td field="xxzshsje" width="120">销项正数含税金额</td>
					<td field="xxzshsjehj" width="120"></td>
				</tr>
				
				 <tr>
					<td field="xxzshsje" width="120">销项负数含税金额</td>
				</tr>
				
				<tr>
					<td field="xmje" width="120">销项正数未税金额</td>
				</tr>
				
				<tr>
					<td field="xmje" width="120">销项负数未税金额</td>
				</tr>
				
				<tr>
					<td field="se" width="120">销项正数税额</td>
				</tr>
				
				<tr>
					<td field="se" width="120">销项负数税额</td>
				</tr>
				
				<tr>
					<td field="se" width="120">销项含税金额合计</td>
				</tr>
				
				
				<tr>
					<td field="se" width="120">销项不含税金额合计</td>
				</tr>
				
				<tr>
					<td field="se" width="120">销项税额金额合计</td>
				</tr> -->
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
								url : '${pageContext.request.contextPath}/api/Kptj/KptjList',
								method : 'GET'
							});
			
		
			

		});
		
		function jjj() {
		
			 var a=  window.location.href = "${pageContext.request.contextPath}/api/MProuct/MProuctList";
        									
		}
							
		
		
		/*  //导出
		function exportExcel(){
			window.location.href='${pageContext.request.contextPath}/api/exportBill?fptqm='+condition.fptqm
								+'&fplsh='+condition.fplsh
								+'&gfsh='+condition.gfsh
								+'&starttime='+condition.starttime
								+'&endtime='+condition.endtime
								+'&djzt='+condition.djzt
		}
		  */
		

		
		//查询
		function getData() {
			$('#dataTable')
					.datagrid(
							{
								 url : '${pageContext.request.contextPath}/api/Kptj/KptjList?starttime=' 
									 
										 + $('#starttime').val()
										+ '&endtime='
										+ $('#endtime').val()
										+ '&fplx='
										+ $('#fplx').val()
										
										+ '&gfmc='
										+ $('#gfmc').val()
		
										+ '&gfsh='
										+ $('#gfsh').val()
										
										+ '&xfmc='
										+ $('#xfmc').val()
										
										+ '&"kpxm"='
										+ $('#"kpxm"').val()
										
										+ '&xfsh='
										+ $('#xfsh').val() + '',
										
									
								method : 'GET',
							});
			
		}
		
		 
	</script>
</body>
</html>
