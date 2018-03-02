<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>会员信息管理</title>
<script>
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
							url : '${pageContext.request.contextPath}/api/memberList?starttime='
									+ $('#starttime').val()
									+ '&endtime='
									+ $('#endtime').val()
									+ '&mobile='
									+ $('#mobile').val() + '',
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
	 //操作行控制
	function formatOper(val,row,index){
    	//表格中按钮控制 '<shiro:hasPermission name="role:add1"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="edit('+index+')">修改</a></shiro:hasPermission>';
		return '<a href="#" class="easyui-linkbutton" style="color:blue;" iconCls="icon-cancel" onclick="view('+row.id+')">查看</a>';  
	};
	
	function formatDatebox(value) {  
	    if (value == null || value == '') {  
	        return '';  
	    }  
	    var dt;  
	    if (value instanceof Date) {  
	        dt = value;  
	    } else {  
	        dt = new Date(value);  
	    }  
	  
	    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
	}
	
	Date.prototype.format = function (format) {  
	    var o = {  
	        "M+": this.getMonth() + 1, // month  
	        "d+": this.getDate(), // day  
	        "h+": this.getHours(), // hour  
	        "m+": this.getMinutes(), // minute  
	        "s+": this.getSeconds(), // second  
	        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
	        "S": this.getMilliseconds()  
	        // millisecond  
	    }  
	    if (/(y+)/.test(format))  
	        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
	            .substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	        if (new RegExp("(" + k + ")").test(format))  
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
	    return format;  
	}  
	
	function formatImg(value,row,index){
    	if('' != value && null != value)
      	value = '<img style="width:40; height:40px" src="' + value + '">';
      	return  value;
    	}	
	
	function view(idx) {
		$.ajax(
						{
							type : 'GET',
							url : "${pageContext.request.contextPath}/api/getMemberInfoById",
							data : {
								id : idx
							},
						}).then(function success(data) {
					$("#form_view label").each(function() {
						var name = $(this).attr("name");
						if (name) {
							if(name == "headimgurl"){
								$("#headimg").attr("src",data[name]);
							}
							else if(name == "registerTime"){
								$(this).text(formatDatebox(data[name]));
							}
							else{
								$(this).text(data[name]);
							}
						}
					});
				}, function error() {
					alert("读取用户信息出错");
					return false;
				});
		$('#listModal').dialog('open');
	}
</script>
</head>
<body class="easyui-layout">
	<div id="gridModel"
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- 查询区域 -->
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						<label>绑定时间</label><span>:</span> <input id="starttime"
							name="starttime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> <label>～</label> <input
							id="endtime" name="endtime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> &nbsp;&nbsp;<label>手机号</label>
						<span>:</span> <input id='mobile' class="easyui-searchbox"
							data-options="prompt:'请输入手机号',searcher:getData"
							style="width: 20%; height: 25px;" /> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="getData()" data-options="iconCls:'icon-search'">查询</a>
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
						<th
							data-options="field:'headimgurl',width:'150', formatter: formatImg">头像</th>
						<th field="realname" width="120">姓名</th>
						<th field="mobile" width="200">手机号</th>
						<th
							data-options="field:'registerTime',width:150,formatter:formatDatebox">绑定时间</th>
						<th data-options="field:'id',width:150,align:'center',formatter:formatOper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="listModal" class="easyui-dialog" title="会员详情" closed="true" modal="true"
		style="width: 450px; height: 450px; padding: 5px; align: left; display:none">
		<form id='form_view'>
			<table cellpadding="10">
				<tr>
					<td>头像:</td>
					<td><label name="headimgurl" data-options=width:180" style="display:none"></label>
					    <img id="headimg" src="" height="50" width="50"/></td>
				</tr>
				<tr>
					<td>昵称:</td>
					<td><label name="nickname" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td>openID:</td>
					<td><label name="openId" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><label name="realname" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td><label name="mobile" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td>住址:</td>
					<td><label name="provinceName" data-options="width:180"></label>&nbsp;&nbsp;
						<label name="cityName" data-options="width:180"></label>&nbsp;&nbsp;
						<label name="countyName" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td></td>
					<td><label for="address" name="address" id="address"
						value="${currMember.getAddress()}" data-options="width:180"></label></td>
				</tr>
				<tr>
					<td>绑定时间:</td>
					<td><label for="registerTime" name="registerTime"
						data-options="width:180"></label></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
