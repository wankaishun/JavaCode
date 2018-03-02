<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>日志一览</title>
<script>
	// 初期化
	$(function() {
		$("#starttime").datebox({
			onSelect : function(beginDate) {
				$('#endtime').datebox().datebox('calendar').calendar({
					validator : function(date) {
						return beginDate <= date;
					}
				});
			}
		});

		//获取当前iframe高度
		var iframeH = $("iframe", window.parent.document)[0];
		//设置table高度
		var contentHeight = $(iframeH).height() - $(".toolsdiv").height() - 35;
		$("#dataTable").css('min-height', contentHeight + "px");
		getData();
		$('.iframediv').layout();
	});

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

	//	获取数据
	function getData() {
		$('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/sysloginfo?starttime='
									+ $('#starttime').val()
									+ '&endtime='
									+ $('#endtime').val()
									+ '&realname='
									+ $('#realname').val() + '',
							method : 'GET',
						});
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
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- 查询区域 -->
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						<label>操作时间</label><span>:</span> <input id="starttime"
							name="starttime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> <label>～</label> <input
							id="endtime" name="endtime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> &nbsp;&nbsp;<label>操作用户</label>
						<span>:</span> <input id='realname' class="easyui-searchbox"
							data-options="prompt:'请输入操作用户',searcher:getData"
							style="width: 20%; height: 25px;"/> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="getData()" data-options="iconCls:'icon-search'" style="width:80px;">查询</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="realname" width="100">操作用户</th>
						<th field="operationtime" width="130">操作时间</th>
						<th field="permissionname" width="150">使用功能</th>
						<th field="content" width="400">日志内容</th>
						<th field="ipaddress" width="100">IP地址</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
