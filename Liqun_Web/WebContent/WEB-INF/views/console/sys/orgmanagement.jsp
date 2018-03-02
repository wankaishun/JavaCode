<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>组织架构管理</title>
</head>
<body class="easyui-layout">
	<div class="easyui-layout" data-options="fit:true">
		<!-- 左侧菜单树 -->
		<div data-options="region:'west',split:true"
			style="width: 300px; padding: 10px">
			<div
				class="iframediv panel-body panel-body-noheader panel-body-noborder">
				<ul id="editpermission" class="easyui-tree"
					"
					data-options="animate:true,lines:true"></ul>
			</div>
		</div>
		<!-- 表单内容区 -->
		<div data-options="region:'center'" style="padding: 10px 100px;">
			<div style="padding: 15px 15px; margin-left: 30px">
				<a href="#" class="easyui-linkbutton"
					style="width: 15%; height: 30px" data-options="iconCls:'icon-add'"
					onclick="clearForm()">新增</a> <a href="#" class="easyui-linkbutton"
					style="width: 15%; height: 30px"
					data-options="iconCls:'icon-remove'" onclick="deleteMenu()">删除</a>

			</div>
			<form id="orgInfo" method="post">
				<table cellpadding="5" style="width: 500px">
					<tr>
						<td>单位代码:</td>
						<td><input class="easyui-textbox" type="text" id="orgCode" missingMessage="请输入单位代码" data-options="required:true,validType:'length[0,20]'"
							name="orgCode" style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
					<td>单位名称:</td>
					<td><input class="easyui-textbox" type="text"
						id="orgName" missingMessage="请输入单位名称" name="orgName"  data-options="required:true,validType:'length[0,30]'"
						style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>纳税登记号:</td>
						<td><input class="easyui-textbox" type="text" name="taxNumber" id="taxNumber" missingMessage="请输入纳税登记号" data-options="required:true,validType:'length[0,30]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input class="easyui-textbox" type="text" name="address" id="address"  missingMessage="请输入地址" data-options="required:true,validType:'length[0,80]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>电话:</td>
						<td><input class="easyui-textbox" type="text" name="phoneNumber" id="phoneNumber"  missingMessage="请输入纳税登记号" data-options="required:true,validType:'length[0,80]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>银行账号:</td>
						<td><input class="easyui-textbox" type="text" name="bankAccount" id="bankAccount" missingMessage="请输入银行账号" data-options="required:true,validType:'length[0,50]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>票面限额:</td>
						<td><input class="easyui-textbox" type="text" name="ticketLimit" id="ticketLimit" missingMessage="请输入票面金额" data-options="required:true,validType:'length[0,18]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>开票人:</td>
						<td><input class="easyui-textbox" type="text" id="ticketHolder"  missingMessage="请输入开票人" data-options="required:true,validType:'length[0,50]'"
							name="ticketHolder" style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>复核人:</td>
						<td><input class="easyui-textbox" type="text" name="reviewer" id="reviewer" missingMessage="请输入复核人" data-options="required:true,validType:'length[0,50]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>收款人:</td>
						<td><input class="easyui-textbox" type="text" name="payee" id="payee" missingMessage="请输入收款人" data-options="required:true,validType:'length[0,50]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>

				</table>
			</form>
			<div style="margin-left: 30px; margin-top: 20px">
				<a href="#" onclick="save()" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width: 50%; height: 30px">保存</a>
			</div>


		</div>
	</div>

	<script>
		// 初始化
		$(function() {
			show();
			$('#addModal').dialog('open');
			//单击树获得树的属性
			$('#editpermission').tree({
				onClick : function(node) {
					getOrgInfo(node.id)
				}
			})
		})
		function show() {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/getOrgTree",
				type : 'POST',
				dataType : 'Json',
				success : function(data) {
					$('#editpermission').tree({
						data : data
					});

				},
				error : function(data, textStatus) {
					console.log(data);
				}
			})
		};
		function getOrgInfo(id) {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/getOrgInfo",
				type : 'POST',
				data : {
					id : id
				},
				dataType : 'Json',
				success : function(data) {
					loadLocal(data)
				},
				error : function(data, textStatus) {
					console.log(data);
				}
			})
		};
		//form表单绑定数据
		function loadLocal(data) {
			$('#orgInfo').form('load', data);
		}
		//点击新增清空表单
		function clearForm() {
			$('#orgInfo').form('clear');
			//获得当前节点的id

		}
		function save() {
			var node = $('#editpermission').tree('getSelected');
			if (node) {
				var id = node.id;
				//根绝id 新增节点
				if($("#orgInfo").form('enableValidation').form('validate')){
					addMenu(id)
				}
			}
		}

		function addMenu(id) {
			var orgCode = $("#orgCode").val();
			var orgName = $("#orgName").val();
			var taxNumber = $("#taxNumber").val();
			var address = $("#address").val();
			var phoneNumber = $("#phoneNumber").val();
			var bankAccount = $("#bankAccount").val();
			var ticketLimit = $("#ticketLimit").val();
			var ticketHolder = $("#ticketHolder").val();
			var reviewer = $("#reviewer").val();
			var payee = $("#payee").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/api/saveOrgInfo",
				type : 'POST',
				data : {
					id : id,
					orgCode : orgCode,
					orgName : orgName,
					taxNumber : taxNumber,
					address : address,
					phoneNumber : phoneNumber,
					bankAccount : bankAccount,
					ticketLimit : ticketLimit,
					ticketHolder : ticketHolder,
					reviewer : reviewer,
					payee : payee
				},
				dataType : 'Json',
				success : function(data) {
					show();
				},
				error : function(data, textStatus) {
				}
			})
		}
		function deleteMenu() {
			var node = $('#editpermission').tree('getSelected');
			if (node) {
				var id = node.id;
				//根绝id 新增节点

				var arr = getChildren(id)
				arr.push(id);
				console.log(arr)
				$
						.ajax({
							url : "${pageContext.request.contextPath}/api/deleteOrgInfo",
							type : 'POST',
							data : {
								id : arr
							},
							dataType : 'Json',
							success : function(data) {
								show();
							},
							error : function(data, textStatus) {
							}
						})
			}
		}
		//获得单击节点下的所有子节点的id 数组
		function getChildren(id) {
			var $tree = $('#editpermission');
			var node = $tree.tree('find', id);
			var childrenNodes = $tree.tree('getChildren', node.target);
			var arr = new Array();//创建空数组
			for (var i = 0; i < childrenNodes.length; i++) {
				var node = childrenNodes[i];
				var id = node.id;
				arr.push(id);
			}
			return arr;
		}
	</script>
</body>
</html>
