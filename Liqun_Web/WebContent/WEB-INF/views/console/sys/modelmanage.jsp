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
	<div class="easyui-tabs" style="width: 100%; height: 500px">
		<div title="模板上传" style="padding: 10px">
			<div class="easyui-panel" title="文件上传"
				style="width: 400px; padding: 30px 70px 50px 70px">
				<form action="${pageContext.request.contextPath}/api/upload"
					id="form" method="post" enctype="multipart/form-data">
					<div style="margin-bottom: 20px">
						<div>模板类型:</div>
						<select class="easyui-combobox" name="modelid" style="width: 100%">
							<option value="1">商品信息模板</option>
							<option value="2">客户信息模板</option>
						</select>
					</div>
					<div style="margin-bottom: 20px">
						<div>模板名称:</div>
						<input class="easyui-textbox" name="name" style="width: 100%"
							missingMessage="请填写模板名称" data-options="required:true"
							maxlength="20">
					</div>
					<div style="margin-bottom: 20px">
						<div>请选择上传文件:</div>
						<input class="easyui-filebox" name="file1"
							data-options="required:true" missingMessage="请选择上传文件"
							style="width: 100%">
					</div>
					<div>
						<a href="#" class="easyui-linkbutton" style="width: 100%"
							onclick="submit()">Upload</a>
					</div>
				</form>
			</div>
		</div>
		<div title="模板下载" style="padding: 10px">
			<div class="easyui-panel" title="模板下载" id="downmodel"
				style="width: 400px; padding: 30px 70px 50px 70px">
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		var tbody = ""; 
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/api/getModelList',
			dataType : 'json',
			success : function(data) {
				  $.each(data,function(n,value) {
			           	var trs = ""; 
			             trs+='<div style="margin-bottom: 20px">'+
							  '<a href="${pageContext.request.contextPath}/api/down?id='+value.id +'" class="easyui-linkbutton" style="width: 100%">'+value.name+'</a>'+
							  '</div>'
						tbody += trs;  
			           });  
			         $("#downmodel").append(tbody);  
			       
			},
			error : function(data) {
				
			}
		});
	})
	var data = $("form").serialize();
		function submit(){
			if($("#form").form('enableValidation').form('validate')){
				$("#form").submit( ) 
			}
		}
	</script>
</body>
</html>
