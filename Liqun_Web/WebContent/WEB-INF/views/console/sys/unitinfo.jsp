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
		 
		<!-- 表单内容区 -->
		<div data-options="region:'center'" style="padding: 10px 100px;">
			<form id="unitinfo" method="post">
				<table cellpadding="5" style="width: 500px">
				
					<tr>
						<td>单位代码:</td>
						<td><input class="easyui-textbox" type="text" id="qydm" missingMessage="请输入单位代码" data-options="required:true,validType:['integer','length[0,20]']"
							name="qydm" style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
					<td>单位名称:</td>
					<td><input class="easyui-textbox" type="text"
						id="qymc" missingMessage="请输入单位名称" name="qymc"  data-options="required:true"
						style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>纳税登记号:</td>
						<td><input class="easyui-textbox" type="text" name="qysh" id="qysh" missingMessage="请输入纳税登记号" data-options="required:true,validType:['integer','length[0,30]']"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>地址电话:</td>
						<td><input class="easyui-textbox" type="text" name="qydzdh" id="qydzdh"  missingMessage="请输入地址电弧" data-options="required:true,validType:'length[0,80]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>银行账号:</td>
						<td><input class="easyui-textbox" type="text" name="yhzh" id="yhzh" missingMessage="请输入银行账号" data-options="required:true,validType:'length[0,50]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>票面限额:</td>
						<td><input class="easyui-textbox" type="text" name="zdxe" id="zdxe" missingMessage="请输入票面金额" data-options="required:true,validType:'length[0,18]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>开票人:</td>
						<td><input class="easyui-textbox" type="text" id="kpr"  missingMessage="请输入开票人" data-options="required:true"
							name="kpr" style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>复核人:</td>
						<td><input class="easyui-textbox" type="text" name="fhr" id="fhr" missingMessage="请输入复核人" data-options="required:true,validType:'length[0,30]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>
					<tr>
						<td>收款人:</td>
						<td><input class="easyui-textbox" type="text" name="skr" id="skr" missingMessage="请输入收款人" data-options="required:true,validType:'length[0,30]'"
							style="width: 80%; height: 30px"></input></td>
					</tr>

				</table>
			</form>
			<div style="margin-left: 30px; margin-top: 20px">
				<a href="#" onclick="save()" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width:40%; height: 30px">保存</a>
			</div>


		</div>
	</div>

	<script>
		// 初始化
		$(function() {
			getUnitInfo();
		})
		function getUnitInfo() {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/getUinitInfo",
				type : 'POST',
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
			$('#unitinfo').form('load', data);
		}
		function save() {
				if($("#unitinfo").form('enableValidation').form('validate')){
					saveUnitInfo()
				}
		}

		function saveUnitInfo() {
			var qydm = $("#qydm").val();
			var qymc = $("#qymc").val();
			var qysh = $("#qysh").val();
			var qydzdh = $("#qydzdh").val();
			var yhzh = $("#yhzh").val();
			var zdxe = $("#zdxe").val();
			var kpr = $("#kpr").val();
			var fhr = $("#fhr").val();
			var skr = $("#skr").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/api/saveUnitInfo",
				type : 'POST',
				data : {
					qydm : qydm,
					qymc : qymc,
					qysh : qysh,
					qydzdh : qydzdh,
					yhzh : yhzh,
					zdxe : zdxe,
					kpr : kpr,
					fhr : fhr,
					skr : skr 
				},
				dataType : 'Json',
				success : function(data) {
					$.messager.alert('操作提示',"保存成功！");
					getUnitInfo();
				},
				error : function(data) {
				}
			})
		}
	</script>
</body>
</html>
