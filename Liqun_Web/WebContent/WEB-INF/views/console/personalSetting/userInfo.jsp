<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>变更个人信息</title>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="修改个人信息" style="width: 400px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff">
				<table cellpadding="5">
					<tr>
						<td>员工编号:</td>
						<td><input class="easyui-validatebox" type="text"
							id="employeeNo" name="employeeNo" value="${currUser.getEmployeeNo()}" readonly></input></td>
					</tr>
					<tr>
						<td>Email地址:</td>
						<td><input class="easyui-validatebox" type="text" id="email" 
							name="email" value="${currUser.getEmail()}" data-options="required:true,validType:'email'"
							missingMessage="请输入Email地址" maxlength="128"></input></td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input class="easyui-validatebox" type="text" id="realName" maxlength="20" 
							name="realName" value="${currUser.getRealName()}" data-options="required:true,validType:['length[0,20]']"
							missingMessage="请输入姓名" ></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px;float: middle;"
					onclick="submitForm()">提交</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function submitForm() {
			if ($("#ff").form('enableValidation').form('validate')) {
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/api/PersonalSettting/editUserInfo",
					data : {
						email : $("#email").val(),
						realName : $("#realName").val()
					},
				}).then(function success(data) {
					if (data >0) {
						$.messager.alert('操作提示',"变更个人信息成功！");
					}
				}, function error() {
					$.messager.alert('警告',"变更个人信息错误！");
					return false;
				});
			}
		}
	</script>
</body>
</html>