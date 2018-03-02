<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>日志一览</title>
<script>
	// 初期化
	$(function() {
		var condition;
		$("#starttime").datebox({
			onSelect : function(beginDate) {
				$('#endtime').datebox().datebox('calendar').calendar({
					validator : function(date) {
						return beginDate <= date;
					}
				});
			}
		});
		//双击事件
		$('#dataTable').datagrid({
			//双击事件
			onDblClickRow : function(rowIndex, rowData) {
				show(rowData.fplsh);
			}
		});
		//获取当前iframe高度
		var iframeH = $("iframe", window.parent.document)[0];
		//设置table高度
		var contentHeight = $(iframeH).height() - $(".toolsdiv").height() - 35;
		$("#dataTable").css('min-height', contentHeight + "px");
		getData(0);
		$('.iframediv').layout();
	});
	function Condition(fptqm,fplsh,gfmc,gfsh,starttime,endtime,djzt){
		this.fptqm=fptqm;
		this.fplsh=fplsh;
		this.gfmc=gfmc;
		this.gfsh=gfsh;
		this.starttime=starttime;
		this.endtime=endtime;
		this.djzt=djzt;
	}
	function isAllEqual(array){
	    if(array.length>0){
	       return !array.some(function(value,index){
	         return value !== array[0];
	       });   
	    }else{
	        return true;
	    }
	}
	/* function merge() {
		//返回选中多行  
		var selRow = $('#dataTable').datagrid('getSelections');
		if(selRow==""||selRow.length==1){
			$.messager.alert('操作提示',"请选择需要合并的列！")
			return;
		}
		var temID = [];
		var gfsh=[];
		//批量获取选中行的评估模板ID  
		for (i = 0; i < selRow.length; i++) {
			if(selRow[i].fplsh.indexOf("-")>-1){
				$.messager.alert('操作提示',"已经合并过不能再进行合并！");
				return;
			}
			temID.push(selRow[i].fplsh);
			gfsh.push(selRow[i].gfsh)
		}
		if(!isAllEqual(gfsh)){
			$.messager.alert('操作提示',"请选择相同购方进行合并！");
			return;
		}
		//提交  
		/* $.ajax({
			type : 'GET',
			url : "${pageContext.request.contextPath}/api/merge",
			data : {
				fplsh : temID,
				type : $("#mergeType").val()
			},
		}).then(
				function success(data) {
					$.messager.alert('提示',"合并成功！");
					getData(0);
					return true;
					}, function error() {
					$.messager.alert('警告',"合并失败！");
					return false;
				}
			)
		} */ 
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
	//导出
/* 	function exportExcel(){
		window.location.href='${pageContext.request.contextPath}/api/exportBill?fptqm='+condition.fptqm
							+'&fplsh='+condition.fplsh
							+'&gfmc='+condition.gfmc
							+'&gfsh='+condition.gfsh
							+'&starttime='+condition.starttime
							+'&endtime='+condition.endtime
							+'&djzt='+condition.djzt
	} */
	//	获取数据
	function getData(djzt) {
		  condition=new Condition($('#fptqm').val(),$('#fplsh').val(),$('#gfmc').val(),$('#gfsh').val(),$('#starttime').val(),$('#endtime').val(),djzt);//存放查询数据
		$('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/billManager?starttime='
									+ $('#starttime').val()
									+ '&endtime='
									+ $('#endtime').val()
									+ '&fplsh='
									+ $('#fplsh').val()
									+ '&fptqm='
									+ $('#fptqm').val()
									+'&djzt='
									+ djzt
									+ '&gfmc='
									+ $('#gfmc').val()
									+ '&gfsh='
									+ $('#gfsh').val() + '',
							method : 'GET',
						});
	}
	/* function show(fplsh) {
		$('#billdel')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/billdel?fplsh='
									+ fplsh,
							method : 'GET',
						});

		$('#addModal').dialog('open');
	} */
	//	扩展"清空"选项
	/* var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text : '清空',
		handler : function(target) {
			$(target).datebox('setValue', '');
			$(target).datebox('hidePanel');
		}
	}); */
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
						<label>提取号：</label> <input id="fptqm" class="easyui-textbox"
							data-options="prompt:'请输入提取号'"> <label>流水号：</label> <input
							id="fplsh" class="easyui-textbox" data-options="prompt:'请输入流水号'">
						<label>购方名称：</label> <input id="gfmc" class="easyui-textbox"
							data-options="prompt:'请输入购方名称'"> <label>购方税号：</label> <input
							id="gfsh" class="easyui-textbox" data-options="prompt:'请输入购方税号'">
							<label>合并级别：</label> <select class="easyui-combobox" id="mergeType"
						panelHeight="auto" data-options="editable:false" style="width: 200px">
				 		<c:forEach var="sysDict" items="${sysDictList}">
							<option value="${sysDict.key}">${sysDict.value}</option>
						</c:forEach>
					</select>
					</div>
				</div>
			</div>
		</div>
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						<label>单据日期</label><span>:</span> <input id="starttime"
							name="starttime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> <label>～</label> <input
							id="endtime" name="endtime" type="text" editable="false"
							class="easyui-datebox"
							data-options="formatter:dateFormatter,prompt:'请选择时间',buttons:buttons"
							style="height: 25px; width: 110px" /> &nbsp;&nbsp; <a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="getData(0)" data-options="iconCls:'icon-search'"
							style="width: 80px;">查询</a> &nbsp;&nbsp; <a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="merge()" data-options="iconCls:'icon-reload'"
							style="width: 70px;">合并</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="exportExcel()" data-options="iconCls:'icon-reload'"
							style="width: 70px;">导出</a>&nbsp;&nbsp;<a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="getData(5)"
							data-options="iconCls:'icon-search'" style="width: 120px;">查看已合并</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable" singleSelect="false"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="ck" checkbox="true" singleSelect:true></th>
						<th field="fptqm" width="150">提取码</th>
						<th field="fplsh" width="150">单据流水号</th>
						<th field="djztName" width="100">单据状态</th>
						<th field="djlxName" width="100">单据类型</th>
						<th field="kpzdh" width="100">开票机终端号</th>
						<th field="gfsh" width="100">购方税号</th>
						<th field="gfmc" width="100">购方名称</th>
						<th field="gfdzdh" width="100">购方地址电话</th>
						<th field="gfyhzh" width="100">购方银行账号</th>
						<th field="kpr" width="100">开票人</th>
						<th field="skr" width="100">收款人</th>
						<th field="fhr" width="100">复核人</th>
						<th field="jshj" width="100">含税金额</th>
						<th field="hjje" width="100">合计金额</th>
						<th field="hjse" width="100">合计税额</th>
						<th field="bz" width="100">备注</th>
						<th field="sj" width="100">手机号</th>
						<th field="email" width="100">邮箱</th>
						<th field="czsj" width="100" formatter="dateFormatter">操作时间</th>
						<th field="qybh" width="100">企业编号</th>
						<th field="gfsh" width="100">pos编号</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- <div id="addModal" title="商品列表" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			data-options="iconCls:'icon-edit',resizable:true,modal:true">
			<form id="editForm">
				<table id="billdel" singleSelect="false"
					data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:10">
					<thead>
						<tr>
							<th field="fptqm" width="100">提取码</th>
							<th field="fplsh" width="100">单据流水号</th>
							<th field="xmmc" width="100">商品名称</th>
							<th field="spbm" width="100">商品编码</th>
							<th field="ggxh" width="100">商品型号</th>
							<th field="jldw" width="100">单位</th>
							<th field="xmsl" width="100">数量</th>
							<th field="xmdj" width="100">单价（不含税）</th>
							<th field="xmje" width="100">不含税</th>
							<th field="sl" width="100">税率</th>
							<th field="se" width="100">税额</th>
							<th field="hsdj" width="100">含税单价</th>
							<th field="hsje" width="100">含税金额</th>
							<th field="zkhhsje" width="100">折扣含税金额</th>
							<th field="zkje" width="100">折扣金额</th>
							<th field="zkse" width="100">折扣税额</th>
							<th field="zkhhsje" width="100">折扣后含税金额</th>
							<th field="zkhje" width="100">折扣后金额</th>
						</tr>
					</thead>
				</table>
			</form>
		</div>
	</div> -->
</body>
</html>
