<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>发票管理</title>
<script>
	// 初期化
	$(function() {
		//初始化显示模态窗口
		var $win;
		$win = $("#addModal").window({
			title:'选择发票类型',
			width:350,
			height:320,
			top: ($(window).height() - 350) * 0.5,
		    left: ($(window).width() - 320) * 0.5,
		    shadow: false,
		    modal: false,
		    minimizable: false,
		    maximizable: false,
		    collapsible: false
		});
		
		$win.window('open');
		//获取下拉框的值
		
		$("#next").click(function(){
			var fplx = $("#fplx option:selected").val();
			window.location.href="${pageContext.request.contextPath}/console/billmanagerList?fplx="+fplx;
		});
		
	});
</script>
</head>
<body>
	<div id="addModal" class="easyui-dialog" title="选择发票类型" closed="true" modal="true"
		style="width: 380px; height: 400px; padding: 5px; align: center;">
		<form id='form_user' style="padding: 10px 20px 10px 40px;">
			<table>
				
				<tr>
					<td align="left">请选择发票类型</td>
					<td  align="left">
						<select class="easyui-combobox" id="fplx"
						panelHeight="auto" data-options="editable:false" style="width: 100px">
								<option></option>
								<option  value="51">电子发票</option>
								<option  value="0">普票</option>
								<option  value="2">专票</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<div style="padding: 20px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOk" icon="icon-ok"id="next">下一页</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#addModal').dialog('close')" icon="icon-cancel">取消</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>
