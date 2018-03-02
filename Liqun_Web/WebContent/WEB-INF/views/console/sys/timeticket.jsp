<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>定时任务列表</title>

<script>
var url = "${pageContext.request.contextPath}";
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
		$("#cron1").click(function(){
			$('#addModal').dialog({
				title : '新增定时任务',
				closed: true,
			    cache: false,
			    modal: true
			});
			$('#addModal').dialog('open');
		})
		$("#getcron").click(function(){
			$("#cron1").val($("#cron").val())
			$('#addModal').dialog('close')
		})
		
		//添加or编辑定时任务
		$(".btnOkAdd").click(function() {
			var state=$("#addTrigger").attr("state");
			if(state==1){//添加
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath }/quartz/add",
					data : {
						cron : $("#cron").val(),
						clazz : $("#clazz").val(),
						jobName : $("#jobName").val(),
						jobGroupName : $("#jobGroupName").val(),
						triggerName : $("#triggerName").val(),
						triggerGroupName : $("#triggerGroupName").val() 
					}
				}).then(
						function success(data) {
							$('#addTrigger').dialog('close')
							$.messager.alert('提示',"添加成功！");
							getData();
							}, function error() {
							$('#addTrigger').dialog('close')
							$.messager.alert('警告',"添加失败！");
							getData();
						}
					)
			}else{
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath }/quartz/edit",
					data : {
						cron : $("#cron").val(),
						clazz : $("#clazz").val(),
						jobName : $("#jobName").val(),
						jobGroupName : $("#jobGroupName").val(),
						triggerName : $("#triggerName").val(),
						triggerGroupName : $("#triggerGroupName").val(),
						oldjobName:$("#oldjobName").val(),
						oldjobGroupName:$("#oldjobGroupName").val(),
						oldtriggerName:$("#oldtriggerName").val(),
						oldtriggerGroupName:$("#oldtriggerGroupName").val(),
					}
				}).then(
						function success(data) {
							$('#addTrigger').dialog('close')
							$.messager.alert('提示',"添加成功！");
							getData();
							}, function error() {
							$('#addTrigger').dialog('close')
							$.messager.alert('警告',"添加失败！");
							getData();
						}
					)
			}
		})
	});
	
 	  
 	function edit(jobName,jobGroup) {
		$.ajax(
				{
				type : 'GET',
				url : "${pageContext.request.contextPath}/quartz/toEdit",
				data : {
					jobName : jobName,
					jobGroup : jobGroup
				},
				}).then(function success(data) {
					$("#formModal input").each(function() {
						var name = $(this).attr("name");
						if (name) {
							$(this).prev().val(data[name]);//给前一个标签赋值
							$(this).val(data[name]);
						}
					}); 
				}, function error() {
					$.messager.alert('警告',"读取任务信息出错！");
					return false;
				});
		$('#addTrigger').attr("state",2)
		$('#addTrigger').dialog({
			title : '编辑定时任务',
			closed: true,
		    cache: false,
		    modal: true
		});
		$('#addTrigger').dialog('open');
	}
	function add() {
		$('#addTrigger').attr("state",1)//添加状态为1
		$('#addTrigger').dialog({
			title : '新增定时任务',
			closed: true,
		    cache: false,
		    modal: true
		});
		$("#formModal input").each(function() {
			$(this).val("");
		});
		$('#addTrigger').dialog('open');
	}
  //暂停任务
  	function pauseJob(jobName,jobGroupName){
  		$.post(url + "/quartz/pauseJob",{"jobName":jobName,"jobGroupName":jobGroupName},function(data){
  			if(data.status = 'success'){
  				getData();
  			}else{
  				alert("操作失败，请刷新重新！");
  			}
  		});
  	}
  
  //恢复任务
  	function resumeJob(jobName,jobGroupName){
  		$.post(url + "/quartz/resumeJob",{"jobName":jobName,"jobGroupName":jobGroupName},function(data){
  			if(data.status = 'success'){
  				getData();
  			}else{
  				alert("操作失败，请刷新重新！");
  			}
  		});
  	}
  	//删除
  	function deleteJob(jobName,jobGroupName,triggerName,triggerGroupName){
  		$.post(url + "/quartz/deleteJob",{"jobName":jobName,"jobGroupName":jobGroupName,"triggerName":triggerName,"triggerGroupName":triggerGroupName},
  				function(data){
  			if(data.status = 'success'){
  				getData();
  			}else{
  				alert("操作失败，请刷新重新！");
  			}
  		});
  	}
	function formatFlag(val, row, index) {
		
		if(val == 'NORMAL'){
			return '正常运行'
		}
		if(val == 'COMPLETE'){
			return '完成状态'
		}
		if(val == 'ERROR'){
			return '错误状态'
		}
		if(val == 'BLOCKED'){
			return '锁定状态'
		}
		if(val == 'NONE'){
			return '未知'
		}
		if(val == 'PAUSED'){
			return '暂停状态'
		}
	}
	function dateFormatter (value) { 
	    var date = new Date(value);
	    var year = date.getFullYear().toString();
	    var month = (date.getMonth() + 1);
	    var day = date.getDate().toString();
	    var hour = date.getHours().toString();
	    var minutes = date.getMinutes().toString();
	    var seconds = date.getSeconds().toString();
	    if (month < 10) {
	        month = "0" + month;
	    }
	    if (day < 10) {
	        day = "0" + day;
	    }
	    if (hour < 10) {
	        hour = "0" + hour;
	    }
	    if (minutes < 10) {
	        minutes = "0" + minutes;
	    }
	    if (seconds < 10) {
	        seconds = "0" + seconds;
	    }
	    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
	}
 
	function formatOper(val, row, index) {   
		var opeHtml='<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="edit(\''+row.jobName.trim()+ '\',\''+row.jobGroup.trim()+ '\')">编辑</a>&nbsp;&nbsp;';
			opeHtml+='<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="pauseJob(\''+ row.jobName.trim()+ '\',\''+row.jobGroup.trim()+ '\')">暂停</a>&nbsp;&nbsp;';
			opeHtml+='<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="resumeJob(\''+ row.jobName.trim()+ '\',\''+row.jobGroup.trim()+ '\')">恢复</a>&nbsp;&nbsp;';		
			opeHtml+='<a href="#" style="color:blue;" iconCls="icon-cancel" onclick="deleteJob(\''+ row.jobName.trim()+ '\',\''+row.jobGroup.trim()+'\',\''+row.triggerName.trim()+'\',\''+row.triggerGroupName.trim()+ '\')">删除</a>';
			return opeHtml;
	}

	//	获取数据
	function getData() {
		$('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/listJob',
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
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="add()" data-options="iconCls:'icon-add'">新增</a>
				</div>
			</div>
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:true,singleSelect:true,autoRowHeight:true,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="jobGroup" width="100">任务组名称</th>
						<th field="jobName" width="130">定时任务名称</th>
						<th field="cronExpr" width="150">时间表达式</th>
						<th field="previousFireTime" width="150" formatter="dateFormatter">上次运行时间</th>
						<th field="nextFireTime" width="150" formatter="dateFormatter">下次运行时间</th>
						<th
							data-options="field:'jobStatus',width:100,align:'center',formatter:formatFlag">任务状态</th>
						<th field="startTime" width="150" formatter="dateFormatter">开始时间</th>
						<th field="endTime" width="150" formatter="dateFormatter">结束时间</th>
						<th field="jobClass" width="200">任务类名</th>
						<th
							data-options="field:'id',width:200,align:'center',formatter:formatOper">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="addTrigger" class="easyui-dialog" title="新增定时任务" closed="true" modal="true" 
		style="width: 380px; height: 400px; padding: 5px; align: center;">
		<form  style="padding: 10px 20px 10px 40px;" id="formModal">
			<table cellpadding="8">
				<tr>
					<td>cron时间表达式:</td>
					<td><input class="easyui-validatebox" type="text"
						name="cron" id="cron1" data-options="required:true,width:180,validType:'length[1,20]'"
						missingMessage="请填写cron表达式" maxlength="20"></input></td>
				</tr>
				<tr>
					<td>任务类名:</td>
					<td><input class="easyui-validatebox" type="text"
						name="clazz" id="clazz"
						data-options="required:true,width:180,validType:'length[1,30]'" missingMessage="请输入任务类名" maxlength="30"></input></td>
				</tr>
				<tr>
					<td>定时任务名称:</td>
					<td>
					<input id="oldjobName" type="hidden"/>
					<input class="easyui-validatebox" type="text"
						name="jobName" id="jobName"
						data-options="required:true,width:180,validType:'length[1,30]'" missingMessage="请输入定时任务名称" maxlength="30"></input></td>
				</tr>
				<tr>
					<td>任务组名称:</td>
					<td>
					<input id="oldjobGroupName" type="hidden"/>
					<input class="easyui-validatebox" type="text"
						name="jobGroupName" id="jobGroupName"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入任务组名称" maxlength="20"></input></td>
				</tr>
				<tr>
					 <td>触发器名称:</td>
					<td>
					<input id="oldtriggerName" type="hidden"/>
					<input class="easyui-validatebox" type="text"
						name="triggerName"  id="triggerName"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入触发器名称" maxlength="20"></input></td>
				</tr>
			 	<tr>
					 <td>触发器组名称:</td>
					<td>
					<input id="oldtriggerGroupName" type="hidden" value="trigger"/>
					<input class="easyui-validatebox" type="text"
						name="triggerGroupName" id="triggerGroupName"
						data-options="required:true,width:180,validType:'length[1,20]'" missingMessage="请输入触发器组名称" maxlength="20"></input></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton btnOkAdd" icon="icon-ok">确定</a>
							<a href="#" class="easyui-linkbutton btnCancel"
								onclick="$('#addTrigger').dialog('close')" icon="icon-cancel">取消</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addModal" class="easyui-dialog" title="新增系统用户" closed="true"
		style="width: 400px; height: 400px; border: 1px rgb(202, 196, 196) solid; border-radius: 5px;">
		<div style="height: 250px;">
			<div class="easyui-tabs" data-options="fit:true,border:false">
				<div title="秒">
					<div class="line">
						<input type="radio" checked="checked" name="second"
							onclick="everyTime(this)"> 每秒 允许的通配符[, - * /]
					</div>
					<div class="line">
						<input type="radio" name="second" onclick="cycle(this)">
						周期从 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:58" value="1" id="secondStart_0">
						- <input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:59" value="2" id="secondEnd_0"> 秒
					</div>
					<div class="line">
						<input type="radio" name="second" onclick="startOn(this)">
						从 <input class="numberspinner" style="width: 60px;"
							data-options="min:0,max:59" value="0" id="secondStart_1">
						秒开始,每 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:59" value="1" id="secondEnd_1">
						秒执行一次
					</div>
					<div class="line">
						<input type="radio" name="second" id="sencond_appoint"> 指定
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="0">00 <input type="checkbox"
							value="1">01 <input type="checkbox" value="2">02
						<input type="checkbox" value="3">03 <input type="checkbox"
							value="4">04 <input type="checkbox" value="5">05
						<input type="checkbox" value="6">06 <input type="checkbox"
							value="7">07 <input type="checkbox" value="8">08
						<input type="checkbox" value="9">09
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="10">10 <input
							type="checkbox" value="11">11 <input type="checkbox"
							value="12">12 <input type="checkbox" value="13">13
						<input type="checkbox" value="14">14 <input
							type="checkbox" value="15">15 <input type="checkbox"
							value="16">16 <input type="checkbox" value="17">17
						<input type="checkbox" value="18">18 <input
							type="checkbox" value="19">19
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="20">20 <input
							type="checkbox" value="21">21 <input type="checkbox"
							value="22">22 <input type="checkbox" value="23">23
						<input type="checkbox" value="24">24 <input
							type="checkbox" value="25">25 <input type="checkbox"
							value="26">26 <input type="checkbox" value="27">27
						<input type="checkbox" value="28">28 <input
							type="checkbox" value="29">29
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="30">30 <input
							type="checkbox" value="31">31 <input type="checkbox"
							value="32">32 <input type="checkbox" value="33">33
						<input type="checkbox" value="34">34 <input
							type="checkbox" value="35">35 <input type="checkbox"
							value="36">36 <input type="checkbox" value="37">37
						<input type="checkbox" value="38">38 <input
							type="checkbox" value="39">39
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="40">40 <input
							type="checkbox" value="41">41 <input type="checkbox"
							value="42">42 <input type="checkbox" value="43">43
						<input type="checkbox" value="44">44 <input
							type="checkbox" value="45">45 <input type="checkbox"
							value="46">46 <input type="checkbox" value="47">47
						<input type="checkbox" value="48">48 <input
							type="checkbox" value="49">49
					</div>
					<div class="imp secondList">
						<input type="checkbox" value="50">50 <input
							type="checkbox" value="51">51 <input type="checkbox"
							value="52">52 <input type="checkbox" value="53">53
						<input type="checkbox" value="54">54 <input
							type="checkbox" value="55">55 <input type="checkbox"
							value="56">56 <input type="checkbox" value="57">57
						<input type="checkbox" value="58">58 <input
							type="checkbox" value="59">59
					</div>
				</div>
				<div title="分钟">
					<div class="line">
						<input type="radio" checked="checked" name="min"
							onclick="everyTime(this)"> 分钟 允许的通配符[, - * /]
					</div>
					<div class="line">
						<input type="radio" name="min" onclick="cycle(this)"> 周期从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:58" value="1" id="minStart_0"> -
						<input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:59" value="2" id="minEnd_0"> 分钟
					</div>
					<div class="line">
						<input type="radio" name="min" onclick="startOn(this)"> 从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:0,max:59" value="0" id="minStart_1">
						分钟开始,每 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:59" value="1" id="minEnd_1">
						分钟执行一次
					</div>
					<div class="line">
						<input type="radio" name="min" id="min_appoint"> 指定
					</div>
					<div class="imp minList">
						<input type="checkbox" value="0">00 <input type="checkbox"
							value="1">01 <input type="checkbox" value="2">02
						<input type="checkbox" value="3">03 <input type="checkbox"
							value="4">04 <input type="checkbox" value="5">05
						<input type="checkbox" value="6">06 <input type="checkbox"
							value="7">07 <input type="checkbox" value="8">08
						<input type="checkbox" value="9">09
					</div>
					<div class="imp minList">
						<input type="checkbox" value="10">10 <input
							type="checkbox" value="11">11 <input type="checkbox"
							value="12">12 <input type="checkbox" value="13">13
						<input type="checkbox" value="14">14 <input
							type="checkbox" value="15">15 <input type="checkbox"
							value="16">16 <input type="checkbox" value="17">17
						<input type="checkbox" value="18">18 <input
							type="checkbox" value="19">19
					</div>
					<div class="imp minList">
						<input type="checkbox" value="20">20 <input
							type="checkbox" value="21">21 <input type="checkbox"
							value="22">22 <input type="checkbox" value="23">23
						<input type="checkbox" value="24">24 <input
							type="checkbox" value="25">25 <input type="checkbox"
							value="26">26 <input type="checkbox" value="27">27
						<input type="checkbox" value="28">28 <input
							type="checkbox" value="29">29
					</div>
					<div class="imp minList">
						<input type="checkbox" value="30">30 <input
							type="checkbox" value="31">31 <input type="checkbox"
							value="32">32 <input type="checkbox" value="33">33
						<input type="checkbox" value="34">34 <input
							type="checkbox" value="35">35 <input type="checkbox"
							value="36">36 <input type="checkbox" value="37">37
						<input type="checkbox" value="38">38 <input
							type="checkbox" value="39">39
					</div>
					<div class="imp minList">
						<input type="checkbox" value="40">40 <input
							type="checkbox" value="41">41 <input type="checkbox"
							value="42">42 <input type="checkbox" value="43">43
						<input type="checkbox" value="44">44 <input
							type="checkbox" value="45">45 <input type="checkbox"
							value="46">46 <input type="checkbox" value="47">47
						<input type="checkbox" value="48">48 <input
							type="checkbox" value="49">49
					</div>
					<div class="imp minList">
						<input type="checkbox" value="50">50 <input
							type="checkbox" value="51">51 <input type="checkbox"
							value="52">52 <input type="checkbox" value="53">53
						<input type="checkbox" value="54">54 <input
							type="checkbox" value="55">55 <input type="checkbox"
							value="56">56 <input type="checkbox" value="57">57
						<input type="checkbox" value="58">58 <input
							type="checkbox" value="59">59
					</div>
				</div>
				<div title="小时">
					<div class="line">
						<input type="radio" checked="checked" name="hour"
							onclick="everyTime(this)"> 小时 允许的通配符[, - * /]
					</div>
					<div class="line">
						<input type="radio" name="hour" onclick="cycle(this)"> 周期从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:0,max:23" value="0" id="hourStart_0"> -
						<input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:23" value="2" id="hourEnd_1"> 小时
					</div>
					<div class="line">
						<input type="radio" name="hour" onclick="startOn(this)"> 从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:0,max:23" value="0" id="hourStart_1">
						小时开始,每 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:23" value="1" id="hourEnd_1">
						小时执行一次
					</div>
					<div class="line">
						<input type="radio" name="hour" id="hour_appoint"> 指定
					</div>
					<div class="imp hourList">
						AM: <input type="checkbox" value="0">00 <input
							type="checkbox" value="1">01 <input type="checkbox"
							value="2">02 <input type="checkbox" value="3">03
						<input type="checkbox" value="4">04 <input type="checkbox"
							value="5">05 <input type="checkbox" value="6">06
						<input type="checkbox" value="7">07 <input type="checkbox"
							value="8">08 <input type="checkbox" value="9">09
						<input type="checkbox" value="10">10 <input
							type="checkbox" value="11">11
					</div>
					<div class="imp hourList">
						PM: <input type="checkbox" value="12">12 <input
							type="checkbox" value="13">13 <input type="checkbox"
							value="14">14 <input type="checkbox" value="15">15
						<input type="checkbox" value="16">16 <input
							type="checkbox" value="17">17 <input type="checkbox"
							value="18">18 <input type="checkbox" value="19">19
						<input type="checkbox" value="20">20 <input
							type="checkbox" value="21">21 <input type="checkbox"
							value="22">22 <input type="checkbox" value="23">23
					</div>
				</div>
				<div title="日">
					<div class="line">
						<input type="radio" checked="checked" name="day"
							onclick="everyTime(this)"> 日 允许的通配符[, - * / L W]
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="unAppoint(this)">
						不指定
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="cycle(this)"> 周期从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:31" value="1" id="dayStart_0"> -
						<input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:31" value="2" id="dayEnd_0"> 日
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="startOn(this)"> 从
						<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:31" value="1" id="dayStart_1">
						日开始,每 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:31" value="1" id="dayEnd_1">
						天执行一次
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="workDay(this)"> 每月
						<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:31" value="1" id="dayStart_2">
						号最近的那个工作日
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="lastDay(this)">
						本月最后一天
					</div>
					<div class="line">
						<input type="radio" name="day" id="day_appoint"> 指定
					</div>
					<div class="imp dayList">
						<input type="checkbox" value="1">1 <input type="checkbox"
							value="2">2 <input type="checkbox" value="3">3 <input
							type="checkbox" value="4">4 <input type="checkbox"
							value="5">5 <input type="checkbox" value="6">6 <input
							type="checkbox" value="7">7 <input type="checkbox"
							value="8">8 <input type="checkbox" value="9">9 <input
							type="checkbox" value="10">10 <input type="checkbox"
							value="11">11 <input type="checkbox" value="12">12
						<input type="checkbox" value="13">13 <input
							type="checkbox" value="14">14 <input type="checkbox"
							value="15">15 <input type="checkbox" value="16">16
					</div>
					<div class="imp dayList">
						<input type="checkbox" value="17">17 <input
							type="checkbox" value="18">18 <input type="checkbox"
							value="19">19 <input type="checkbox" value="20">20
						<input type="checkbox" value="21">21 <input
							type="checkbox" value="22">22 <input type="checkbox"
							value="23">23 <input type="checkbox" value="24">24
						<input type="checkbox" value="25">25 <input
							type="checkbox" value="26">26 <input type="checkbox"
							value="27">27 <input type="checkbox" value="28">28
						<input type="checkbox" value="29">29 <input
							type="checkbox" value="30">30 <input type="checkbox"
							value="31">31
					</div>
				</div>
				<div title="月">
					<div class="line">
						<input type="radio" checked="checked" name="mouth"
							onclick="everyTime(this)"> 月 允许的通配符[, - * /]
					</div>
					<div class="line">
						<input type="radio" name="mouth" onclick="unAppoint(this)">
						不指定
					</div>
					<div class="line">
						<input type="radio" name="mouth" onclick="cycle(this)">
						周期从 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:12" value="1" id="mouthStart_0">
						- <input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:12" value="2" id="mouthEnd_0"> 月
					</div>
					<div class="line">
						<input type="radio" name="mouth" onclick="startOn(this)">
						从 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:12" value="1" id="mouthStart_1">
						日开始,每 <input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:12" value="1" id="mouthEnd_1">
						月执行一次
					</div>
					<div class="line">
						<input type="radio" name="mouth" id="mouth_appoint"> 指定
					</div>
					<div class="imp mouthList">
						<input type="checkbox" value="1">1 <input type="checkbox"
							value="2">2 <input type="checkbox" value="3">3 <input
							type="checkbox" value="4">4 <input type="checkbox"
							value="5">5 <input type="checkbox" value="6">6 <input
							type="checkbox" value="7">7 <input type="checkbox"
							value="8">8 <input type="checkbox" value="9">9 <input
							type="checkbox" value="10">10 <input type="checkbox"
							value="11">11 <input type="checkbox" value="12">12
					</div>
				</div>
				<div title="周">
					<div class="line">
						<input type="radio" checked="checked" name="week"
							onclick="everyTime(this)"> 周 允许的通配符[, - * / L #]
					</div>
					<div class="line">
						<input type="radio" name="week" onclick="unAppoint(this)">
						不指定
					</div>
					<div class="line">
						<input type="radio" name="week" onclick="startOn(this)">
						周期 从星期<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:7" id="weekStart_0" value="1"> -
						<input class="numberspinner" style="width: 60px;"
							data-options="min:2,max:7" value="2" id="weekEnd_0">
					</div>
					<div class="line">
						<input type="radio" name="week" onclick="weekOfDay(this)">
						第<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:4" value="1" id="weekStart_1"> 周
						的星期<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:7" id="weekEnd_1" value="1">
					</div>
					<div class="line">
						<input type="radio" name="week" onclick="lastWeek(this)">
						本月最后一个星期<input class="numberspinner" style="width: 60px;"
							data-options="min:1,max:7" id="weekStart_2" value="1">
					</div>
					<div class="line">
						<input type="radio" name="week" id="week_appoint"> 指定
					</div>
					<div class="imp weekList">
						<input type="checkbox" value="1">1 <input type="checkbox"
							value="2">2 <input type="checkbox" value="3">3 <input
							type="checkbox" value="4">4 <input type="checkbox"
							value="5">5 <input type="checkbox" value="6">6 <input
							type="checkbox" value="7">7
					</div>
				</div>
				<div title="年">
					<div class="line">
						<input type="radio" checked="checked" name="year"
							onclick="unAppoint(this)"> 不指定 允许的通配符[, - * /] 非必填
					</div>
					<div class="line">
						<input type="radio" name="year" onclick="everyTime(this)">
						每年
					</div>
					<div class="line">
						<input type="radio" name="year" onclick="cycle(this)">周期 从
						<input class="numberspinner" style="width: 90px;"
							data-options="min:2013,max:3000" id="yearStart_0" value="2013">
						- <input class="numberspinner" style="width: 90px;"
							data-options="min:2014,max:3000" id="yearEnd_0" value="2014">
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 10px">
			<table>
				<tbody>
					<tr>
						<td><input type="text" name="v_second" class="col" value="*"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_min" class="col" value="*"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_hour" class="col" value="*"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_day" class="col" value="*"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_mouth" class="col" value="*"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_week" class="col" value="?"
							style="display: none" readonly="readonly" /></td>
						<td><input type="text" name="v_year" class="col"
							style="display: none" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>Cron 表达式:</td>
						<td colspan="6"><input type="text" name="cron"
							style="width: 100%;" value="* * * * * ?" id="cron" /></td>
					</tr>
				</tbody>
			</table>
			<div style="padding: 10px; text-align: center;">
							<a href="#" class="easyui-linkbutton" id="getcron" icon="icon-ok">确定</a>
						</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/cron/jquery1.easyui.min.js"
		type="text/javascript"></script>
</body>
</html>
