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
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">

					<div>
						<!-- <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">导出</a> -->
						<!-- <a href="#" class="easyui-linkbutton c3" style="width: 100px;margin-left:55px;">导出</a> -->
						
							<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="exportExcel()" data-options="iconCls:'icon-reload'"
							style="width: 70px;">导出</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<!-- <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">导入</a> -->
						<a href="#" class="easyui-linkbutton c2" style="width: 100px;margin-left:85px;">返回</a>
					</div><br/>

					<div class="wu-toolbar-search">

						&nbsp;&nbsp;&nbsp;<label>提取号：</label> <input id="FPTQM" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>流水号:</label> <input id="FPLSH" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>购方名:</label> <input id="GFMC" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;<label>购方税号:</label> <input id="GFSH" class="easyui-textbox">
						<br /> <br />
					</div>
					<div class="wu-toolbar-search">
					&nbsp;&nbsp;&nbsp;<label>开单时间始</label><span>:</span>
						<!--  <input id="sstartTime"
							name="sstartTime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> -->

						<input type="text" class="easyui-datebox" id="starttime"
							name="starttime"> 
							
							&nbsp;&nbsp;&nbsp;<label>开单时间末</label>
						<!-- <input
							id="sendTime" name="sendTime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" />	 -->
						<input type="text" class="easyui-datebox" id="endtime" name="endtime"> 
						
						&nbsp;&nbsp;&nbsp;<label>发票状态：</label> <select class="easyui-combobox" id="DJZT"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
						
						<option value="-2">发送到盒子校验失败(数据有错误)</option>
						<option value="-1">连接盒子失败</option>
						<option value="0">未开发票</option>
						<option value="1">已拆分</option>
						<option value="2">已开发票</option>
						<option value="3">冲红</option>
						<option value="4">作废</option>
						<option value="5">已合并</option>
						</select>	
							&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="getData()"
							data-options="iconCls:'icon-search'">查询</a>
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
						<th field="fplx" width="100" replace="专票_0,普票_2,电子发票_51">发票类型</th>
						<th field="djzt" width="100">单据状态</th> 
						<th field="fptqm" width="100">提取码</th>
						<th field="fplsh" width="100">流水号</th>
						<th field="fpdm" width="100">发票代码</th>
						<th field="fphm" width="100">发票号码</th>
						<th field="kpsj" width="100">开票时间</th>
						<th field="qybh" width="100">企业编号</th>
						<th field="posId" width="100">pos编号</th>
						
						<th field="gfmc" width="100">购方名称</th>
						<th field="gfsh" width="100">购方税号</th>
						<th field="gfyhzh" width="100">购方银行账号</th>
						<th field="gfdzdh" width="100">购方地址电话</th>
						<th field="xfmc" width="100">销方名称</th>
						<th field="xfsh" width="100">销方税号</th>
						<th field="xfyhzh" width="100">销方银行账号</th>
						<th field="xfdzdh" width="100">销方地址电话</th>
						<th field="pdfxzdz" width="100">pdf下载地址</th>
						<th field="hsje" width="100">含税金额</th>
						
						<th field="hjje" width="100">合计金额</th>
						<th field="hjse" width="100">合计税额</th>
						<th field="kpr" width="100">开票人</th>
						<th field="skr" width="100">收款人</th>
						<th field="fhr" width="100">复核人</th>
						<th field="sjh" width="100">手机号</th>
						<th field="email" width="100">邮箱</th>
						
						
						
						
						
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
								url : '${pageContext.request.contextPath}/api/InvoiceInquiry/InvoiceInquiryList',
								method : 'GET'
							});
			
		 
			
		

		});
		
		
		
		
		/*  //导出
		function exportExcel(){
			window.location.href='${pageContext.request.contextPath}/api/exportBill?fptqm='+condition.fptqm
								+'&fplsh='+condition.fplsh
								+'&gfmc='+condition.gfmc
								+'&gfsh='+condition.gfsh
								+'&starttime='+condition.starttime
								+'&endtime='+condition.endtime
								+'&djzt='+condition.djzt
		}
		  */
		

		
		//查询
		function getData() {
			/*  condition=new Condition($('#FPTQM').val(),$('#FPLSH').val(),$('#GFMC').val(),$('#GFSH').val(),$('#starttime').val(),$('#endtime').val(),djzt);//存放查询数据 */
			$('#dataTable')
					.datagrid(
							{
								 url : '${pageContext.request.contextPath}/api/InvoiceInquiry/InvoiceInquiryList?fptqm=' 
									 
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
