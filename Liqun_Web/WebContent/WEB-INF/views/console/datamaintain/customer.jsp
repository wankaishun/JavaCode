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
			
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="add()" data-options="iconCls:'icon-add'">新增</a>
				
				
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="Delete()" data-options="iconCls:'icon-add'">删除</a>
				
					
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="editCustomer()" data-options="iconCls:'icon-add'">修改</a>
				
				<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="flush()" data-options="iconCls:'icon-add'">刷新</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="importExcel()" data-options="iconCls:'icon-add'">导入</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="exportExcel()" data-options="iconCls:'icon-add'">导出</a>
				
		</div>
			
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
				<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
			<div id="wu-toolbar">
				<div class="wu-toolbar-search">
					<label>客户名称：</label> <input id="khmc" class="easyui-textbox" data-options="prompt:'请输入客户名称'">

					&nbsp;&nbsp;
					<label>客户税号：</label> <input id="khsh" class="easyui-textbox" data-options="prompt:'请输入客户税号'">
		
					</select> &nbsp;&nbsp;<a href="#" class="easyui-linkbutton btnSearch"
						iconCls="icon-search">开始检索</a>
				</div>
			</div>
			</div>
			</div>
		<!-- 查询区域 -->
	
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:false,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
					    <th field="khbh" width="60">客户编号</th>
						<th field="khmc" width="120">客户名称</th>
						<th field="khsh" width="120">客户税号</th>
						<th field="khdzdh" width="120">地址电话</th>
						<th field="yhzh" width="120">银行帐号</th>
						<th field="khzt" width="120">客户状态</th>
						<th field="zhgxsj" width="200" formatter="dateFormatter">最后更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="addModal" class="easyui-dialog" title="新增系统用户" closed="true" modal="true"
		style="width: 380px; height: 400px; padding: 5px; align: center;">
		<form id='form_user' style="padding: 10px 20px 10px 40px;">
			<table cellpadding="8">
				<tr>
					<td>客户名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="khmc" id="khmc3" data-options="required:true,width:180"
						missingMessage="请输入账号" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>客户税号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="khsh" id="khsh3"
						data-options="required:true,width:180" missingMessage="请输入员工编号" maxlength="30"></input></td>
				</tr>
				<tr >
					<td>地址电话:</td>
					<td><input class="easyui-validatebox" type="text"
						id="khdzdh" name="khdzdh"data-options="required:true,width:180"
						missingMessage="请输入登录密码" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>银行行号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="yhzh" id="yhzh"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入用户姓名" maxlength="20"></input></td>
				</tr>
				
				
				<tr>
					<td></td>
					<td>
						<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOk" icon="icon-ok" onclick="submitForm()">确定</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#addModal').dialog('close')" icon="icon-cancel">取消</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="updateModal" class="easyui-dialog" title="新增系统用户" closed="true" modal="true"
		style="width: 380px; height: 400px; padding: 5px; align: center;">
		<form id='form_update' style="padding: 10px 20px 10px 40px;">
			<table cellpadding="8">
			    <tr>
					<td></td>
					<td><input class="easyui-validatebox" type="hidden"
						name="khbh" id="khbh1" ></input></td>
				</tr>
				<tr>
					<td>客户名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="khmc" id="khmc1" data-options="required:true,width:180,validType:'length[1,20]'"
						missingMessage="请输入账号" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>客户税号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="khsh" id="khsh1"
						data-options="required:true,width:180,validType:'length[1,30]'" missingMessage="请输入员工编号" maxlength="30"></input></td>
				</tr>
				<tr >
					<td>地址电话:</td>
					<td><input class="easyui-validatebox" type="text"
						id="khdzdh1" name="khdzdh"data-options="required:true,width:180"
						missingMessage="请输入登录密码" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>银行行号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="yhzh" id="yhzh1"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入用户姓名" maxlength="20"></input></td>
				</tr>
				
				
				<tr>
					<td></td>
					<td>
						<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOk" icon="icon-ok" onclick="updateForm()">确定</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#updateModal').dialog('close')" icon="icon-cancel">取消</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addcolumn" class="easyui-dialog" title="新增系统用户" closed="true" modal="true"
		style="width: 380px; height: 400px; padding: 5px; align: center;">
		<form id='form_column' style="padding: 10px 20px 10px 40px;">
			<table cellpadding="8">
			    <tr>
					<td>增加的列名:</td>
					<td><input class="easyui-validatebox" type="text"
						name="addcolumn" id="addcolumn" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOk" icon="icon-ok" onclick="addcolumndata()">确定</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#addcolumn').dialog('close')" icon="icon-cancel">取消</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		// 初始化
		var userId = "";	
		$(function() {
			dg_userlist = $('#dataTable')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/api/datamaintain/customerList',
								method : 'GET'
							});
			//检索
			$(".btnSearch").click(function(data) {
				dg_userlist.datagrid('load', {
					khmc : $("#khmc").val(),
					khsh : $("#khsh").val(),		
				});
			});

			//添加用户
		});
		//操作行控制
		function formatOper(val, row, index) {
			var opeHtml='<shiro:hasPermission name="user:update"><a href="#" style="color:blue;" iconCls="icon-cancel" onclick="edit('+ val+ ')">修改</a>&nbsp;&nbsp;</shiro:hasPermission>';
			if(row.flag=="1")
			{
				opeHtml+='<shiro:hasPermission name="user:lock"><a href="#" style="color:blue;" iconCls="icon-cancel" onclick="operate('+ val+ ',\''+row.realName.trim()+'\',1,'+ row.flag+ ')">禁用</a>&nbsp;&nbsp;</shiro:hasPermission>';
			}
			else
			{
				opeHtml+='<shiro:hasPermission name="user:unlock"><a href="#" style="color:blue;" iconCls="icon-cancel" onclick="operate('+ val+ ',\''+row.realName.trim()+'\',1,'+ row.flag+ ')">启用</a>&nbsp;&nbsp;</shiro:hasPermission>';		
			}
			opeHtml+='<shiro:hasPermission name="user:reset_password"><a href="#" style="color:blue;" iconCls="icon-cancel" onclick="operate('+ val + ',\''+row.realName+'\',2)">重置密码</a></shiro:hasPermission>';
		return opeHtml;
		};

		function formatFlag(val, row, index) {
			return val == "1" ? "正常" : "已禁用";
		}

		//新增
		function add() {
			userId = "";
			$('#addModal').dialog({
				title : '新增系统用户',
				closed: true,
			    cache: false,
			    modal: true
			});
			$("#form_user input").each(function() {
				$(this).val("");
			});
			$("#account").removeAttr("readonly");
			$("#employeeNo").removeAttr("readonly");
			$("#pwdtr").show();
			$('#addModal').dialog('open');
		}

		function edit(id) {
			userId = id;
			$("#pwdtr").hide();
			$.ajax(
							{
								type : 'GET',
								url : "${pageContext.request.contextPath}/api/sysuser/getSysUser",
								data : {
									id : id
								},
							}).then(function success(data) {
						$("#form_user input").each(function() {
							var name = $(this).attr("name");
							if (name) {
								$(this).val(data[name]);
							}
						});
						$('#selRole1').combobox('setValue',data["roleId"]);
					}, function error() {
								$.messager.alert('警告',"读取用户信息出错！");
						return false;
					});
			$("#account").prop("readonly", "readonly");
			$("#employeeNo").prop("readonly", "readonly");
			$('#addModal').dialog({
				title : '编辑系统用户',
				closed: true,
			    cache: false,
			    modal: true
			});
			$('#addModal').dialog('open');
		}

		//禁用
		function operate(id,realName, type, flag) {
			var message = "";
			switch (type) {
			case 1:
				if (flag == "1") {
					message = "确定要禁用吗?";
				} else {
					message = "确定要启用吗?";
				}
				break;
			case 2:
				message = "确定要重置密码吗?";
				break;
			}
			
			
			$.messager.confirm('操作提示',message, function(r){
				if(r){
				$.ajax({
									type : 'POST',
									url : "${pageContext.request.contextPath}/api/sysuser/opeSysUser",
									data : {
										id : id,
										realName:realName,
										type : type,
										flag : (flag == "1" ? "0" : "1")
									},
								}).then(function success(data) {
							$.messager.alert('操作提示',"操作成功");
							$(".btnSearch").trigger("click");
						}, function error() {
							$.messager.alert('Warning','操作失败');
							return false;
						});
					}
			})		
			
		}
		function dateFormatter(value) {
			var date = null;
			if (value != null) {
				date = new Date(value);
			} else {
				date = new Date();
			}
			var year = date.getFullYear().toString();
			var month = (date.getMonth() + 1);
			var day = date.getDate().toString();
			var hours=(date.getHours() + 1);
			var minutes=  (date.getMinutes() + 1);     
			var seconds= (date.getSeconds() + 1);     
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			if (hours<10) {
				hours = "0" + hours
			}
			if (minutes<10) {
				minutes = "0" + minutes
			}
			if (seconds<10) {
				seconds = "0" + seconds
			}
			return year + "/" + month + "/" + day + " " + hours + ":" + minutes + ":" + seconds  ;
		}
		function Delete() {
			//返回选中多行  
			var selRow = $('#dataTable').datagrid('getSelected')
			//判断是否选中行  
			var temID = selRow.khbh;
			//提交  
			$.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/api/datamaintain/delete",
						data : {
							khbh : temID
						},
					});
			location.reload([true])	//刷新页面
			debugger;console.log();
		}
		function flush() {
			location.reload([true])	//刷新页面			
		}
		function updateForm() {
			
			if ($("form_update").form('enableValidation').form('validate')) {
				
				$.ajax({
							type : 'POST',
							url : "${pageContext.request.contextPath}/api/datamaintain/update",
							data : {
								khbh : $("#khbh1").val(),
								khmc : $("#khmc1").val(),
								khsh : $("#khsh1").val(),
								khdzdh : $("#khdzdh1").val(),
								yhzh : $("#yhzh1").val()
							},
							
						});
			}
			location.reload([true])	//刷新页面
		}
		function submitForm() {
			if ($("form_user").form('enableValidation').form('validate')) {
				
						$.ajax({
						
							type : 'POST',
							url : "${pageContext.request.contextPath}/api/datamaintain/add",
							data : {
								khmc : $("#khmc3").val(),
								khsh : $("#khsh3").val(),
								khdzdh : $("#khdzdh").val(),
								yhzh : $("#yhzh").val()
								
							},
						});
			}	
			location.reload([true])	//刷新页面
		}
		function editCustomer() {
			//返回选中多行  
			var selRow = $('#dataTable').datagrid('getSelections')
			//判断是否选中行  
			var temID = '';
			//批量获取选中行的评估模板ID  
			for (i = 0; i < selRow.length; i++) {
				temID = selRow[0].khbh;
			}
			$('#updateModal').panel({
				title : '编辑商品信息'
			});
			$('#updateModal').dialog('open');
			  $.ajax(
					{
						type : 'GET',
						url : "${pageContext.request.contextPath}/api/datamaintain/getCustomerInfo",
						data : {
							khbh : temID
						},
					}).then(function success(data) {
				    $("#form_update input").each(function() {
					var name = $(this).attr("name");
					 if (name) {
						$(this).val(data[name]);
					} 
				});
			}) 
		}
		
		//导出
		function exportExcel(){
			window.location.href='${pageContext.request.contextPath}/api/datamaintain/exportCustomer?khmc='+$("#khmc").val()
								+'&khsh='+$("#khsh").val()
							
		}
		//
		function importExcel(){
			 $.ajax({
				url:'${pageContext.request.contextPath}/api/datamaintain/importCustomer',
				type:"get",  
				dataType:"json",
				success:function(data){
					alert(data)
					//刷新页面
					location.reload([true])
					},
				error:function(data){
					alert("出错")
				}
				}
			) 
			
		}
/* 		function addcolumn(){
			$('#addcolumn').dialog('open');
		}
        function addcolumndata(){
        	window.location.href='${pageContext.request.contextPath}/api/datamaintain/addcolumn?addcolumn='+$("#addcolumn").val()
			
		} */
	</script>
</body>
</html>
