<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="easyui-layout">
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- 查询区域 -->
		<div class="toolsdiv" style="padding: 10px 10px;">
			<shiro:hasPermission name="role:add">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="add()" data-options="iconCls:'icon-add'">新增</a>
			</shiro:hasPermission>		
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="roleName" width="120">角色名称</th>
						<th field="roleDesc" width="200">角色描述</th>
						<th data-options="field:'_operate',width:150,align:'center',formatter:formatOper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="addModal" title="新增用户角色" class="easyui-dialog"
			style="width: 400px; height: 500px;" closed="true"
			data-options="iconCls:'icon-edit',resizable:true,modal:true">
			<form id="editForm">
				<table cellpadding="8">
					<tr>
						<td>角色名称:</td>
						<td><input type="text" id="roleName" name="roleName"
							class="easyui-validatebox"
							data-options="required:true,validType:'length[1,20]'"
							missingMessage="请输入角色名称" maxlength="20"></input>
							 <input type="hidden" id="roleId" name="roleId">
							</td>
					</tr>
					<tr>
						<td>角色描述:</td>
						<td><input type="text" id="roleDesc" name="roleDesc"
							class="easyui-validatebox"
							data-options="required:true,validType:'length[1,256]'"
							missingMessage="请输入角色描述" maxlength="256"></input></td>
					</tr>
					<tr>
						<td>权限选择:</td>
						<td><div
								style="max-height: 250px; height: 350px; overflow-y: scroll;">
								<ul id="editpermission" data-options="checkbox:true"></ul>
							</div></td>
					</tr>
					<tr>
						<td></td>
						<td><a class="easyui-linkbutton btnSave"
							data-options="iconCls:'icon-ok'" href="javascript:void(0)"
							style="width: 80px; float: right;">提交</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script>
		$(function() {
			getData();
			$(".btnSave").click(
							function() {
								if ($("#editForm").form('enableValidation').form('validate')) {
									var menuitems = getChecked();
									if (menuitems == "") {
										$.messager.alert('警告', "请为角色勾选权限！");
										return false;
									}
									$.ajax({
												type : 'POST',
												url : '${pageContext.request.contextPath}/api/save',
												data : $("#editForm").serialize()+ "&menuItems="+ getChecked(),
												dataType : 'json',
												success : function(data,textStatus) {
													if (data == "1") {
														$.messager.alert('操作提示',"保存成功！");
														$('#addModal').dialog("close");
														getData();
													} else {
														$.messager.alert('警告',"保存失败！");
														return false;
													}
												},
												error : function(data,textStatus) {
													$.messager.alert('警告',"保存失败！");
													return false;
												}
											});
								}
							})
		});
		//获取数据
		function getData() {
			$('#dataTable').datagrid({
				url : '${pageContext.request.contextPath}/api/roleList',
				method : 'GET',
			});
		}
		//新增按钮
		function show(roleId) {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/getMenuTree",
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
		
		//编辑按钮
		function edit(roleId) {
			$('#addModal').dialog({
				title : '编辑用户角色',
				closed: true,
			    cache: false,
			    modal: true
			});
			show(roleId);
		}

		function add() {
			$('#addModal').dialog({
				title : '新增用户角色',
				closed: true,
			    cache: false,
			    modal: true
			});
			show(0);
		}
		function setSerializeFormToLower(sysRole) {
			$("#roleName").val(sysRole.roleName);
			$("#roleDesc").val(sysRole.roleDesc);
			$("#roleId").val(sysRole.roleId);
		}

		function clearData() {
			$("#roleName").val("");
			$("#roleDesc").val("");
			$("#roleId").val(0);
		}

		//操作行控制
		function formatOper(val, row, index) {
			return '<shiro:hasPermission name="role:update"><a href="#" class="easyui-linkbutton" style="color:blue;" iconCls="icon-cancel" onclick="edit('+ row.roleId + ')">修改</a></shiro:hasPermission>	';
		};

		//获取权限树选中的值
		function getChecked() {
			var nodes = $('#editpermission').tree('getChecked');
			var menuIdArr = [];
			for (var i = 0; i < nodes.length; i++) {
				if ($.inArray(nodes[i].id, menuIdArr) == -1) {
					menuIdArr.push(nodes[i].id);
					if(nodes[i].menu!=1){
					var parentId = nodes[i].parentId;
					if ($.inArray(parentId, menuIdArr) == -1) {
						menuIdArr.push(parentId);
					}}
					if (nodes[i].menu == 3) {
						if ($.inArray(nodes[i].firMenuId, menuIdArr) == -1) {
							if(nodes[i].firMenuId){
								menuIdArr.push(nodes[i].firMenuId);
							}
						}
					}
				}
			}
			return menuIdArr.join(",");
		}
	</script>
</body>
</html>
