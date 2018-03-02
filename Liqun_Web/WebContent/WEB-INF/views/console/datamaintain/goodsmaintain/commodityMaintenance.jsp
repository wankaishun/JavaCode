<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品维护</title>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
<script>
	// 初始化
	var userId = "";
	var condition;
	$(function() {
		dg_userlist = $('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/commodityMaintenance/syscmList',
							method : 'GET'
						});
		
		

		//检索
		$(".btnSearch").click(function(data) {
			dg_userlist.datagrid('load', {
				realName : $("#realName").val(),
				flag : $("#selFlag").val(),
				roleId : $("#selRole").val()
			});
		});
		function Condition(realName,flag){
			this.realName=realName;
			this.flag=flag;
		}
		//新增按钮
		function show(roleId) {
			$.ajax({
				url : "${pageContext.request.contextPath}/api/commodityMaintenance/getCmTree",
				data : {
					roleId : roleId
				},
				type : 'POST',
				dataType : 'Json',
				success : function(data) {
					$('#editpermission').tree({
						data : data.menuItemList
					});
					//绑定表单数据
					if (roleId == 0) {
						clearData();
					} else {
						setSerializeFormToLower(data.sysRole);
					}
				},
				error : function(data, textStatus) {
					console.log(data);
				}
			});

			$('#addModal').dialog('open');
		}
		//添加用户
		$(".btnOk")
				.click(
						function() {
							if (userId != "") {
								$('#password').validatebox({
									required : false
								});
							} else {
								$('#password').validatebox({
									required : true
								});
							}
							if ($("#form_user").form('enableValidation').form(
									'validate')) {
								var postData = $("#form_user").serialize()
										+ "&roleId=" + $("#selRole1").val();
								if (userId == "") {
									var password = $("#password").val();//获取密码
									var Utf8 = CryptoJS.enc.Utf8;
									var Base64 = CryptoJS.enc.Base64;
									var SHA256 = CryptoJS.SHA256;
									var Hex = CryptoJS.enc.Hex;
									var WordArray = CryptoJS.lib.WordArray;
									var publicSalt = 'rbBUYhjC/HmIevbvs2s0g';
									var withsalt = publicSalt + password;
									var passwordSHA = Base64
											.stringify(SHA256(Utf8
													.parse(withsalt)));
									postData += "&password=" + passwordSHA;
								} else {
									postData += "&id=" + userId;
								}

								$
										.ajax(
												{
													type : 'POST',
													url : "${pageContext.request.contextPath}/api/syscmList",
													data : postData,
												})
										.then(
												function success(data) {
													if (data > 0) {
														$.messager
																.alert('操作提示',
																		"保存成功！");
														$('#addModal').dialog(
																"close");
														$(".btnSearch")
																.trigger(
																		"click");
													}
												},
												function error() {
													$.messager.alert('警告',
															"保存失败！");
													return false;
												});
							}
						})
	});
	//新增
	function add() {

		userId = "";
		$("#addModal").show();
		$('#addModal').panel({
			title : '新增系统用户'
		});
		$("#form_user input").each(function() {
			$(this).val("");
		});
		$("#account").removeAttr("readonly");
		$("#employeeNo").removeAttr("readonly");
		$("#pwdtr").show();
		$('#addModal').dialog('open');
	}
	
	//条件查询功能
	function getData() {
		$('#dataTable')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/api/commodityMaintenance/syscmList?realname='
									+ $('#realName').val()
									+ ''
									+ '&flag='
									+ $('#flag').val() + '',
							method : 'GET',
						});
	}
	
	//导出
	function getexport() {
		window.location.href='${pageContext.request.contextPath}/api/commodityMaintenance/exportCm?realname='
			+ $('#realName').val()
			+ ''
			+ '&flag='
			+ $('#flag').val() + ''
	}
	

	//新增功能
	function submitForm() {
		if ($("#ff").form('enableValidation').form('validate')) {
			$
					.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/api/commodityMaintenance/insert",
						data : {
							spbm : $("#spbm").val(),
							spbh : $("#spbh").val(),
							spm : $("#spm").val(),
							spgg : $("#spgg").val(),
							spdw : $("#spdw").val(),
							sl : $("#sl").val(),
							bhsdj : $("#bhsdj").val(),
							hsdj : $("#hsdj").val(),
							yhzc : $("#yhzc").val(),
							yhzclx : $("#yhzclx").val()
						},
					})
		}
		location.reload([true])	//刷新页面
	}

	//修改功能
	function updateForm() {
		if ($("#edit").form('enableValidation').form('validate')) {
			$.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/api/commodityMaintenance/update",
						data : {
							spid : $("#spid1").val(),
							spbm : $("#spbm1").val(),
							spbh : $("#spbh1").val(),
							spm : $("#spm1").val(),
							spgg : $("#spgg1").val(),
							spdw : $("#spdw1").val(),
							sl : $("#sl1").val(),
							bhsdj : $("#bhsdj1").val(),
							hsdj : $("#hsdj1").val(),
							yhzc : $("#yhzc1").val(),
							yhzclx : $("#yhzclx1").val()
						},
					})
					
		}
		location.reload([true])	//刷新页面
	}


	//批量删除功能
	function allDelete() {
		//返回选中多行  
		var selRow = $('#dataTable').datagrid('getSelections')
		if(selRow==""){
			$.messager.alert('操作提示',"请选择需要删除的列！")
			return;
		}
		//判断是否选中行  
		var temID = [];
		//批量获取选中行的评估模板ID  
		for (i = 0; i < selRow.length; i++) {
			temID.push(selRow[i].spid);
		}
		//提交  
		$
				.ajax({
					type : "GET",
					url : '${pageContext.request.contextPath}/api/commodityMaintenance/delete',
					data : {
						spid : temID
					},
				});
		if(selRow!=""){
		location.reload([true])	//刷新页面		
		}
		
	}


	//根据商品编号检索商品信息
	function edit() {
		//返回选中多行  
	
		var selRow = $('#dataTable').datagrid('getSelections')
		if(selRow==""||selRow.length>1){
			$.messager.alert('操作提示',"请正确选择需要修改的列！")
			return;
		}
		//判断是否选中行  
		var temID = '';
		//批量获取选中行的评估模板ID  
		for (i = 0; i < selRow.length; i++) {
			temID = selRow[0].spid;
		}
		
		$('#get').panel({
			title : '编辑商品信息'
		});
		$('#get').dialog('open');
		  $.ajax(
				{
					type : 'GET',
					url : "${pageContext.request.contextPath}/api/commodityMaintenance/edit",
					data : {
						spid : temID
					},
				}).then(function success(data) {
			    $("#edit input").each(function() {
				var name = $(this).attr("name");
				 if (name) {
					$(this).val(data[name]);
				} 
			});
		}) 
	}
	
	//格式化日期
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
			var hours=(date.getHours() + 1);
			var minutes=  (date.getMinutes() + 1);     
			var seconds= (date.getSeconds() + 1);     
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			if (hours<10) {
				hours = "0" + hours
			}
			if (minutes<10) {
				minutes = "0" + minutes
			}
			if (seconds<10) {
				seconds = "0" + seconds
			}
			return year + "/" + month + "/" + day + " " + hours + ":" + minutes + ":" + seconds  ;
		}

	
	//刷新功能
	function refresh() {
		location.reload([true])	//刷新页面			
	}
	
	//点击税收编码
	function failList(){
		$('#revenue').panel({
			title : '税收编码'
		});
		$('#revenue').dialog('open');
		$.ajax({
			url : "${pageContext.request.contextPath}/api/commodityMaintenance/getCmTree",
			type : 'POST',
			dataType : 'Json',
			success : function(data) {
				$('#editpermission').tree({
					data : data
				});

			},
		})	
				//单击树获得树的属性
				$('#editpermission').tree({
					onClick : function(node) {
						getCmInfo(node.id)
					}
		})
	}
	
	//获得单击节点下的所有子节点的id 数组
	function getChildren(bm) {
		var $tree = $('#editpermission');
		var node = $tree.tree('find', bm);
		var childrenNodes = $tree.tree('getChildren', node.target);
		var arr = new Array();//创建空数组
		for (var i = 0; i < childrenNodes.length; i++) {
			var node = childrenNodes[i];
			var bm = node.bm;
			arr.push(bm);
		}
		return arr;
	}
	
	function getCmInfo(bm) {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/commodityMaintenance/getCmInfo",
			type : 'POST',
			data : {
				bm : bm
			},
			dataType : 'Json',
			success : function(data) {
				loadLocal(data)
			},
			error : function(data, textStatus) {
				console.log(data);
			}
		})
	}
	//form表单绑定数据
	function loadLocal(data) {
		$('#ff').form('load', data);
	}
	//禁用
	function operate(id, type, flag) {
		var message = "";
		switch (type) {
		case 1:
			if (flag == "1") {
				message = "确定要禁用吗?";
			} else {
				message = "确定要启用吗?";
			}
			break;
		case 2:
			message = "确定要重置密码吗?";
			break;
		}

		$.messager
				.confirm(
						'操作提示',
						message,
						function(r) {
							if (r) {
								$
										.ajax(
												{
													type : 'POST',
													url : "${pageContext.request.contextPath}/api/sysuser/opeSysUser",
													data : {
														id : id,
														type : type,
														flag : (flag == "1" ? "0"
																: "1")
													},
												}).then(
												function success(data) {
													$.messager.alert('操作提示',
															"操作成功");
													$(".btnSearch").trigger(
															"click");
												},
												function error() {
													$.messager.alert('Warning',
															'操作失败');
													return false;
												});
							}
						})

	}
</script>
</head>
<body class="easyui-layout">
	<!-- 条件查询 商品名称、商品编码-->
	<div
		class="iframediv panel-body panel-body-noheader panel-body-noborder">
		<!-- button按钮 -->
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()"
			data-options="iconCls:'icon-add'">新增</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="allDelete()"
			data-options="iconCls:'icon-remove'">删除</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()"
			data-options="iconCls:'icon-edit'" >修改</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="refresh()"
			data-options="iconCls:'icon-add'">刷新</a>&nbsp;&nbsp;&nbsp;
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add'">导入</a>&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getexport()"
			data-options="iconCls:'icon-add'">导出</a>&nbsp;&nbsp;&nbsp; 
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add'">自定义列</a>
		<div class="toolsdiv" style="margin-top: 5px; margin-bottom: 5px;">
			<div data-options="region:'center',border:false">
				<div id="wu-toolbar">
					<div class="wu-toolbar-search">
						<label>商品名称：</label> <input id="realName" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;&nbsp; <label>商品编码：</label> <input id="flag"
							class="easyui-textbox"> &nbsp;&nbsp; <a href="#"
							class="easyui-linkbutton btnSearch" iconCls="icon-search"
							onclick="getData()">开始检索</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 数据列表区域 -->
		<div class="contentdiv" style="min-height: 500px;">
			<table id="dataTable"
				data-options="rownumbers:false,singleSelect:false,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="spid" width="60">ID</th>
						<th field="spbm" width="100">企业内部编码</th>
						<th field="spbh" width="100">税收分类编码</th>
						<th field="spm" width="80">商品名称</th>
						<th field="spgg" width="80">规格型号</th>
						<th field="spdw" width="80">计量单位</th>
						<th field="bhsdj" width="80">不含税单价</th>
						<th field="hsdj" width="80">含税单价</th>
						<th field="sl" width="80">税率</th>
						<th field="yhzc" width="80">优惠政策</th>
						<th field="yhzclx" width="80">优惠政策类型</th>
						<th field="spzt" width="80">商品状态</th>
						<th field="zhgxsj" width="130" formatter="dateFormatter">最后更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<!-- 新增商品信息 -->
	<div id="addModal" title="添加商品" style="width: 400px; height: 500px"
		class="easyui-dialog" closed="true" modal="true">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td colspan="2" align="center">请填写商品信息</td>
					</tr>
					<tr>
						<td>商品编码:</td>
						<td><input class="easyui-textbox" type="text" name="spbm"
							id="spbm" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>税收分类编码:</td>
						<td><input class="easyui-textbox" type="text" name="spbh"
							id="spbh" data-options="required:true"></input>
							<input type="button" onclick="failList()" value="税收分类编码" style="width: 85px"></td>
					</tr>
					<tr>
						<td>商品名称:</td>
						<td><input class="easyui-textbox" type="text" name="spm"
							id="spm" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>规格型号:</td>
						<td><input class="easyui-textbox" type="text" name="spgg"
							id="spgg" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>计量单位:</td>
						<td><input class="easyui-textbox" type="text" name="spdw"
							id="spdw" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>税率:</td>
						<td><select class="easyui-combobox" name="sl" id="sl"style="width: 50px">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
						</select></td>
					</tr>
					<tr>
						<td>不含税单价:</td>
						<td><input class="easyui-textbox" type="text" name="bhsdj"
							id="bhsdj" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>含税单价:</td>
						<td><input class="easyui-textbox" type="text" name="hsdj"
							id="hsdj" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>优惠政策:</td>
						<td><select class="easyui-combobox" name="yhzc" id="yhzc" style="width: 45px">
								<option value="1">是</option>
								<option value="0">否</option></select></td>
					</tr>
					<tr>
						<td>优惠政策类型:</td>
						<td><select class="easyui-combobox" name="yhzclx" id="yhzclx" style="width: 55px">
								<option value="1">免税</option></select></td>
					</tr>
					<!-- <tr>
						<td>是否可换开:</td>
						<td><select class="easyui-combobox" name="language"><option
									value="ar">Arabic</option></select></td>
					</tr> -->
					<tr>
						<td></td>
						<td><a class="easyui-linkbutton btnSave"
							data-options="iconCls:'icon-ok'" href="javascript:void(0)"
							style="width: 80px; float: right" onclick="submitForm()">提交</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 修改商品信息 -->
	<div id="get" title="修改商品" style="width: 400px; height: 500px"
		class="easyui-dialog" closed="true" modal="true">
		<div style="padding: 10px 60px 20px 60px">
			<form id="edit">
			<!-- 隐藏框之商品编号 -->
			<input type="hidden" name="spid" id="spid1">
			<!-- 修改商品信息 -->
				<table cellpadding="5">
					<tr>
						<td colspan="2" align="center">请填写商品信息</td>
					</tr>
					<tr>
						<td>商品编码:</td>
						<td><input class="easyui-validatebox" type="text" name="spbm"
							id="spbm1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>税收分类编码:</td>
						<td><input class="easyui-validatebox" type="text" name="spbh"
							id="spbh1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>商品名称:</td>
						<td><input class="easyui-validatebox" type="text" name="spm" value="12"
							id="spm1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>规格型号:</td>
						<td><input class="easyui-validatebox" type="text" name="spgg" value="12"
							id="spgg1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>计量单位:</td>
						<td><input class="easyui-validatebox" type="text" name="spdw" value="12"
							id="spdw1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>税率:</td>
						<td><select class="easyui-validatebox" name="sl" id="sl1" style="width: 50px">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
						</select></td>
					</tr>
					<tr>
						<td>不含税单价:</td>
						<td><input class="easyui-validatebox" type="text" name="bhsdj"
							id="bhsdj1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>含税单价:</td>
						<td><input class="easyui-validatebox" type="text" name="hsdj"
							id="hsdj1" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>优惠政策:</td>
						<td><select class="easyui-validatebox" name="yhzc" id="yhzc1" style="width: 50px"><option
									value="1">是</option>
								<option value="0">否</option></select></td>
					</tr>
					<tr>
						<td>优惠政策类型:</td>
						<td><select class="easyui-validatebox" name="yhzclx" id="yhzclx1" style="width: 55px"><option
									value="1">免税</option></select></td>
					</tr>
					<!--<tr>
						<td>是否可换开:</td>
						<td><select class="easyui-easyui-validatebox" name="language"><option
									value="ar">Arabic</option></select></td>
					</tr>-->
					<tr>
						<td><a class="easyui-linkbutton btnSave"
							data-options="iconCls:'icon-ok'" href="javascript:void(0)"
							style="width: 80px; float: right" onclick="updateForm()">提交</a></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 点击税收编码 -->
	<div id="revenue" title="税收编码" style=" height: 450px"
		class="easyui-dialog" closed="true" modal="true">
		<!-- 左侧树形菜单 -->
		<div data-options="region:'west',split:true"
			style="width: 300px;;float: left;">
			<div
				class="iframediv panel-body panel-body-noheader panel-body-noborder">
				<ul id="editpermission" class="easyui-tree"
					data-options="animate:true,lines:true"></ul>
			</div>
			</div>		
			</div>
		<!-- 右侧检索列表 -->
		<!-- <div style="float: right;">
			<table id="getTable"
				data-options="rownumbers:false,singleSelect:false,autoRowHeight:true,pagination:true,pageSize:20">
				<thead>
					<tr>
						<th field="bm" width="60">编码</th>
						<th field="hbbm" width="100">合并编码</th>
						<th field="mc" width="100">名称</th>
						<th field="sm" width="80">说明</th>
						<th field="sl" width="80">税率</th>
						<th field="zzstsgl" width="80">增值税特殊管理</th>
						<th field="zzszcyj" width="80">增值税政策依据</th>
						<th field="zzstsnrdm" width="80">增值税特殊内容代码</th>
						<th field="xfstsgl" width="80">消费税特殊管理</th>
						<th field="xfszcyj" width="80">消费税政策依据</th>
						<th field="xfstsnrdm" width="80">消费税特殊内容代码</th>
						<th field="gjz" width="80">关键字</th>
						<th field="tjjbm" width="80">统计局编码</th>
						<th field="hzx" width="80">汇总项</th>
					</tr>
				</thead>
			</table>
		</div> -->
	
</body>
</html>
