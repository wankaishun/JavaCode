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
				<div class="wu-toolbar-search">
					<label>用户姓名：</label> <input id="realName" class="easyui-textbox" data-options="prompt:'请输入用户姓名'">

					&nbsp;&nbsp;<label>账户状态：</label> <select class="easyui-combobox" id="selFlag"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
						<option value="-1">全部</option>
						<option value="1">正常</option>
						<option value="0">已禁用</option>
					</select>&nbsp;&nbsp; <label>角色：</label> <select class="easyui-combobox" id="selRole"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
						<option value="-1">全部</option>
						<c:forEach var="role" items="${roles}">
							<option value="${role.roleId}">${role.roleName}</option>
						</c:forEach>
					</select> &nbsp;&nbsp;<a href="#" class="easyui-linkbutton btnSearch"
						iconCls="icon-search">开始检索</a>
				</div>
			</div>
			</div>
			</div>
		<!-- 查询区域 -->
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<shiro:hasPermission name="user:add">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="add()" data-options="iconCls:'icon-add'">新增</a>
				</shiro:hasPermission>
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
						<th field="realName" width="120">用户姓名</th>
						<th field="account" width="120">用户账号</th>
						<th field="roleName" width="120">角色</th>
						<th
							data-options="field:'flag',width:150,align:'center',formatter:formatFlag">账号状态</th>
						<th
							data-options="field:'id',width:150,align:'center',formatter:formatOper">操作</th>
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
					<td>账号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="account" id="account" data-options="required:true,width:180,validType:'length[1,20]'"
						missingMessage="请输入账号" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>员工编号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="employeeNo" id="employeeNo"
						data-options="required:true,width:180,validType:'length[1,30]'" missingMessage="请输入员工编号" maxlength="30"></input></td>
				</tr>
				<tr id='pwdtr'>
					<td>登陆密码:</td>
					<td><input class="easyui-validatebox" type="password"
						id="password" data-options="required:true,width:180"
						missingMessage="请输入登录密码" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>用户姓名:</td>
					<td><input class="easyui-validatebox" type="text"
						name="realName" id="realName"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入用户姓名" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>角色:</td>
					<td><select class="easyui-combobox" id="selRole1"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
							<c:forEach var="role" items="${roles}">
								<option value="${role.roleId}">${role.roleName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>电子邮箱:</td>
					<td><input class="easyui-validatebox" type="text" id="email"
						name="email"
						data-options="required:true,width:180,validType:'email'"
						missingMessage="请输入Email地址" maxlength="128"></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOk" icon="icon-ok">确定</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#addModal').dialog('close')" icon="icon-cancel">取消</a>
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
								url : '${pageContext.request.contextPath}/api/sysuser/sysuserList',
								method : 'GET'
							});
			//检索
			$(".btnSearch").click(function(data) {
				dg_userlist.datagrid('load', {
					realName : $("#realName").val(),
					flag : $("#selFlag").val(),
					roleId : $("#selRole").val()
				});
			});

			//添加用户
			$(".btnOk").click(function() {
								if (userId != "") {
									$('#password').validatebox({
										required : false
									});
								}
								else
									{
									$('#password').validatebox({
										required : true
									});
									}
								if ($("#form_user").form('enableValidation').form('validate')) {
									var postData = $("#form_user").serialize()
											+ "&roleId=" + $("#selRole1").val();
									if (userId == "") {
										var password = $("#password").val();//获取密码
										var Utf8 = CryptoJS.enc.Utf8;
										var Base64 = CryptoJS.enc.Base64;
										var SHA256 = CryptoJS.SHA256;
										var Hex = CryptoJS.enc.Hex;
										var WordArray = CryptoJS.lib.WordArray;
										var publicSalt = 'rbBUYhjC/HmIevbvs2s0g';
										var withsalt = publicSalt + password;
										var passwordSHA = Base64
												.stringify(SHA256(Utf8
														.parse(withsalt)));
										postData += "&password=" + passwordSHA;
									} else {
										postData += "&id=" + userId;
									}

									$.ajax({
														type : 'POST',
														url : "${pageContext.request.contextPath}/api/sysuser/saveSysUser",
														data : postData,
													})
											.then(
													function success(data) {
														if (data > 0) {
															$.messager.alert('操作提示',"保存成功！");
															$('#addModal')
																	.dialog(
																			"close");
															$(".btnSearch")
																	.trigger(
																			"click");
														}
													}, function error() {
														$.messager.alert('警告',"保存失败！");
														return false;
													});
								}
							})
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
	</script>
</body>
</html>
