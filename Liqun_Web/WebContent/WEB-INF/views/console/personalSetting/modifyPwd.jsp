<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '两次输入的密码不一致'
		}

	});
</script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="修改密码" style="width: 400px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff">
				<table cellpadding="5">
					<tr>
						<td>原密码:</td>
						<td><input class="easyui-validatebox" type="password"
							id="password" name="password" data-options="required:true"
							missingMessage="请输入原密码" maxlength="20"></input></td>
					</tr>
					<tr>
						<td>新密码:</td>
						<td><input class="easyui-validatebox" type="password"
							id="newPwd" name="newPwd" data-options="required:true"
							missingMessage="请输入新密码" maxlength="20"></input></td>
					</tr>
					<tr>
						<td>确认新密码:</td>
						<td><input class="easyui-validatebox" type="password"
							name="confirmNewPwd" data-options="required:true"
							validType="equalTo['#newPwd']" invalidMessage="两次输入密码不一致"
							missingMessage="请再次输入密码" maxlength="20"></input></td>
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
				var password = $("#password").val();//获取原密码
				var newPwd = $("#newPwd").val();//获取新密码

				var Utf8 = CryptoJS.enc.Utf8;
				var Base64 = CryptoJS.enc.Base64;
				var SHA256 = CryptoJS.SHA256;
				var Hex = CryptoJS.enc.Hex;
				var WordArray = CryptoJS.lib.WordArray;
				var publicSalt = 'rbBUYhjC/HmIevbvs2s0g';
				var withsalt = publicSalt + password;
				var passwordSHA = Base64
						.stringify(SHA256(Utf8.parse(withsalt)));
				var newPwdwithsalt = publicSalt + newPwd;
				var newPwdSHA = Base64.stringify(SHA256(Utf8
						.parse(newPwdwithsalt)));

				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/api/PersonalSettting/modifyPwd",
					data : {
						password : passwordSHA,
						newPwd : newPwdSHA
					},
				}).then(function success(data) {
					if (data == "-1") {
						$.messager.alert('警告',"您输入的原密码不正确！");
						return false;
					} else if (data == "0") {
						$.messager.alert('警告',"输入参数有误！");
						return false;
					} else if (data == "1") {
						$.messager.alert('操作提示',"密码设置成功！");
					}
				}, function error() {
					$.messager.alert('警告',"密码设置错误！");
					return false;
				});
			}
		}
	</script>
</body>
</html>