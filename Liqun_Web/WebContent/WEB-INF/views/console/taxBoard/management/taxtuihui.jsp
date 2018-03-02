<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发票退回</title>
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
				starttime : $("#starttime").val(),
				endtime : $("#endtime").val(),
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
//	格式化日期
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
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
	}
	//	扩展"清空"选项
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text : '清空',
		handler : function(target) {
			$(target).datebox('setValue', '');
			$(target).datebox('hidePanel');
		}
	});
</script>
</head>
<body class="easyui-layout">
	<!-- 条件查询 商品名称、商品编码-->
	<div class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- button按钮 -->
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="refresh()"
			data-options="iconCls:'icon-add'">退回</a>
	</div>
	&nbsp;
	    <div class="wu-toolbar-search">
						<label>开始购买时间</label><span>:</span> <input id="starttime"
							name="starttime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 23px; width: 110px" />&nbsp;&nbsp;<label>开始结束时间</label><span>:</span> <input
							id="endtime" name="endtime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 23px; width: 110px" /> &nbsp;&nbsp;<a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="getData()" data-options="iconCls:'icon-search'" style="width:80px;">发票查询</a>
					</div>&nbsp;
		<!-- 查询区域 -->
				<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:false,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
					    <th field="nsrsbh" width="120">税号</th>
						<th field="kpjh" width="120">分机号</th>
						<th field="fpzl" width="120">发票种类</th>
						<th field="lbdm" width="120">发票代码</th>
						<th field="qshm" width="120">起始号码</th>
						<th field="fpfs" width="120">发票份数</th>
						<th field="fpzt" width="120">发票状态</th>
						<th field="gmrq" width="200" formatter="dateFormatter">购买日期</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
		
		
</body>
</html>
