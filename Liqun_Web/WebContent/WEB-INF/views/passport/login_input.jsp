<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri   ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<script src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/core.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/sha256.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/login/crypto-js/enc-base64.js"></script>
<style type="text/css">
html, body
{
    margin: 0;
    padding: 0;
    background: url('${pageContext.request.contextPath}/resources/images/login/bg6.jpg');
    repeat: no-repeat;
    background-attachment:fixed;
    filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/background.jpg', sizingMethod='scale');
    -ms-filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/background.jpg', sizingMethod='scale');
    background-size: cover;
    -moz-background-size: cover;
    -webkit-background-size: cover;
} 
.content{
	background:url("${pageContext.request.contextPath}/resources/images/login/u1.png") no-repeat top center;
	position: absolute;
    left: 50%;
    top: 40%;
    width: 501px;
    height: 329px;
}
.title{
    font-family: 'Arial Normal', 'Arial';
    font-weight: 590;
    font-style: normal;
    font-size: 18px;
    color: #333333;
    text-align: center;
    line-height: normal;
}
.input_name{
 	margin:0 auto 11px;  
	width:237px;
	height:30px;
	border:1px  rgb(153, 153, 153); 
	
}
.input_captcha{
	margin:0px auto 11px;  
	width:237px;
	height:30px;
	border:1px  rgb(153, 153, 153);
	align:left;
}
.txt{
	position: absolute;
    left: 779px;
    top: 233px;
    width: 300px;
    height: 42px;
    opacity: 0.8;
    font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';
    font-weight: 600;
    font-style: normal;
    font-size: 18px;
}
.btnLogin{
	background:url("${pageContext.request.contextPath}/resources/images/login/u9.png");
	margin:0 auto 11px;  
	width:246px;
	height:34px;
	border:1px  rgb(153, 153, 153);
	border-radius:5px;	
}
.forgetPass{
	margin:0px auto 11px;  
	width:237px;
	height:30px;
	align:left;
}
.showMessage{
	margin:0px auto 11px;  
	width:230px;
	height:6px;
	align:left;
	font-size: 10px;
}

body {
    background-color: rgb(223, 235, 222);
    background-image: url(${pageContext.request.contextPath}/resources/images/login/u9.png);
    background-attachment: fixed;
    background-size: cover;
    position: static;
    left: auto;
    width: 1186px;
    text-align: left;
    margin: 0px;
    background-position: left top;
    background-repeat: no-repeat;
}
</style>

<title>管理员登录</title>
</head>
<body>
	<script type="text/javascript">
			console.log("window != top" , window != top)
			console.log("top.location.href", top.location.href)
		if (window != top) {
			top.location.href=location.href;
		}
	</script>
	<div>
		<div class="content">
			<div>
				<div class="title" align="center" style="font-family:'应用字体 Bold', '应用字体';">
					<p>
						<span>后台登录系统</span>
					</p>
				</div>
				<div id="showMessage" class="showMessage" style="color:red;"></div>
				<div class="input_name">
 					<input  name="username" id="username" placeholder="请输入用户名" value="admin" style="width: 237px;height:25px"/>
 				</div>
 				<div class="input_name">
		     		<input  id="password" type="password" placeholder="请输入密码" value="123456" style="width: 237px;height:25px"/>			 
		    	</div>
		    	 <div class="input_captcha">
		       		<input  id="captcha" type="text" placeholder="验证码" name="captcha" style="width: 159px;height:25px;float: left; " maxlength="5"/>
		       		<div style="float:left">
		       			<span>
		       				<img src="" id="kaptchaImage" onclick="changeVerifyCode()" style="width:80px;height:31px;position:absolute">		      
		       			</span>
		       		</div>     		
		        </div>
		        </br>
		       	<div class="input_name">
					<button id="btnLogin" name="btnLogin" class="btnLogin" >登录</button>
		       	</div>			   
 			</div>				
		</div>
	</div>
	<script type="text/javascript">
	 function changeVerifyCode() {  
         var time=new Date().getTime();
         document.getElementById("kaptchaImage").src="${pageContext.request.contextPath}/kaptcha?d="+time;//为了不让验证码缓存，为了安全起见，需要次次都刷新  
     } 
	
	 $(function(){ 
		 changeVerifyCode();
	 });
	 
	 
	 //登录
	 $(function(){ 
		 
		//回车登录
		$(document).keydown(function (event) {
			 if (event.keyCode == 13) {
			 $("#btnLogin").click();
			 }
		});
		$("#btnLogin").click(function(){
		//获取用户名
		var account = $("#username").val();
		if (account == null || account == "") {
            $("#username").focus();
            return false;
        }
		//获取密码
		var password = $("#password").val();
        if (password == null || password == "") {
            $("#password").focus();
            return false;
        }
		
		//密码明文处理
		var Utf8 = CryptoJS.enc.Utf8;
		var Base64 = CryptoJS.enc.Base64;
		var SHA256 = CryptoJS.SHA256;
		var Hex = CryptoJS.enc.Hex;
		var WordArray = CryptoJS.lib.WordArray;
		var publicSalt = 'rbBUYhjC/HmIevbvs2s0g';
		var withsalt = publicSalt+password;
		var passwordSHA =  Base64.stringify(SHA256(Utf8.parse(withsalt)));
		
		//获取输入的验证码 
		var captcha = $("#captcha").val();
        if (captcha == null || captcha == "") {
            $("#captcha").focus();
            return false;
        }
		
		$.ajax({
				url:'${pageContext.request.contextPath}/passport/login',
				type:'POST',
				dataType:'JSON',
			 	data:{
			 		account:account,
			 		password:passwordSHA,
			 		captcha:captcha
			 	}
		 	}).then(function success(resp){
			 	console.log('success', resp);
			 	if(resp.ok == 1){
			 		window.location.href = "${pageContext.request.contextPath}/console";
			 	}
			}, function error(resp) {
				console.log('error', resp);
				if (resp.responseJSON.errcode=='authentication_error') {	
					$('#showMessage').text(resp.responseJSON.message);
					changeVerifyCode();
				} else {
					$('#showMessage').text("未知错误");
				}
			});
	 	});
	 	
	});
	 
	 //提示信息的表示
	
	</script>
	
</body>
</html>