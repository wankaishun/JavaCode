<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品维护</title>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
<script>
	// 初始化
	var userId = "";

	$(function() {
		dg_userlist = $('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/commodityMaintenance/syscmList',
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
		
		//新增按钮
		function show(roleId) {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/commodityMaintenance/getCmTree",
				data : {
					roleId : roleId
				},
				type : 'POST',
				dataType : 'Json',
				success : function(data) {
					$('#editpermission').tree({
						data : data.menuItemList
					});
					//绑定表单数据
					if (roleId == 0) {
						clearData();
					} else {
						setSerializeFormToLower(data.sysRole);
					}
				},
				error : function(data, textStatus) {
					console.log(data);
				}
			});

			$('#addModal').dialog('open');
		}
		//添加用户
		$(".btnOk")
				.click(
						function() {
							if (userId != "") {
								$('#password').validatebox({
									required : false
								});
							} else {
								$('#password').validatebox({
									required : true
								});
							}
							if ($("#form_user").form('enableValidation').form(
									'validate')) {
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

								$
										.ajax(
												{
													type : 'POST',
													url : "${pageContext.request.contextPath}/api/syscmList",
													data : postData,
												})
										.then(
												function success(data) {
													if (data > 0) {
														$.messager
																.alert('操作提示',
																		"保存成功！");
														$('#addModal').dialog(
																"close");
														$(".btnSearch")
																.trigger(
																		"click");
													}
												},
												function error() {
													$.messager.alert('警告',
															"保存失败！");
													return false;
												});
							}
						})
		});
		//新增
		function add() {

			userId = "";
			$("#addModal").show();
			$('#addModal').panel({
				title : '新增系统用户'
			});
			$("#form_user input").each(function() {
				$(this).val("");
			});
			$("#account").removeAttr("readonly");
			$("#employeeNo").removeAttr("readonly");
			$("#pwdtr").show();
			$('#addModal').dialog('open');
		}
	
		//条件查询功能
		function getData() {
			$('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/commodityMaintenance/syscmList?realname='
									+ $('#realName').val() + ''
									+ '&flag='
									+ $('#flag').val() + '',
							method : 'GET',
						});
		}
	
		//格式化日期
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

	
	//刷新功能
	function refresh() {
		location.reload([true])	//刷新页面			
	}
	
	

	//禁用
	function operate(id, type, flag) {
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

		$.messager
				.confirm(
						'操作提示',
						message,
						function(r) {
							if (r) {
								$
										.ajax(
												{
													type : 'POST',
													url : "${pageContext.request.contextPath}/api/sysuser/opeSysUser",
													data : {
														id : id,
														type : type,
														flag : (flag == "1" ? "0"
																: "1")
													},
												}).then(
												function success(data) {
													$.messager.alert('操作提示',
															"操作成功");
													$(".btnSearch").trigger(
															"click");
												},
												function error() {
													$.messager.alert('Warning',
															'操作失败');
													return false;
												});
							}
						})

	}
</script>
</head>
<body class="easyui-layout">
	<!-- 条件查询 商品名称、商品编码-->
	<div class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- button按钮 -->
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="refresh()"
			data-options="iconCls:'icon-add'">刷新</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="allDelete()"
			data-options="iconCls:'icon-remove'">启用</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()"
			data-options="iconCls:'icon-edit'" >停用</a>&nbsp;&nbsp;
	</div>
	
		<!-- 查询区域 -->
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						<label>是否锁死期：</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<select class="easyui-combobox" id="sfssq"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
						<option value="-1">全部</option>
						<option value="1">已到锁死</option>
						<option value="0">否</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>发票种类：</label> 
						<select class="easyui-combobox" id="sfssq"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
						<option value="-1">全部</option>
						<option value="1">电子票</option>
						<option value="0">卷票</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
						<label>税号：</label> <input id="sh" class="easyui-textbox"> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>分机号：</label> <input id="fjh" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" class="easyui-linkbutton btnSearch" iconCls="icon-search">开始检索</a>
					</div>
				</div>
			</div>
		</div>
		
		
</body>
</html>
