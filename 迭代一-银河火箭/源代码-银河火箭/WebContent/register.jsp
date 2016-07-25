<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<link href="css/login.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<style type="text/css">
	#getCode{
		background-color: #c5e3db;
	}
</style>
</head>
<body>
    <div id="container">
    	<div id="head">   		
    	</div>
    	<div id="main">
    		<form action="registerServlet" method="post" id="login">
    			<input type="text" name="name" id="name" placeholder="用户名"><span id="error"></span><br/>
    			<input type="password" name="password" id="password" placeholder="密码"><br/>
    			<input type="password" name="confirm" id="confirm" placeholder="确认密码"><br/>
    			<input type="text" name="email" id="email" placeholder="邮箱"><input type='button' id="getCode" value='获取验证码'><br/>
    			<input type="text" name="checkCode" id="checkCode" placeholder="验证码"><br/>
    			<input type="submit" value="注册" disabled="true" id="register"><br/>
    			<input type="submit" value="登录" onclick="window.location.href='login.jsp';return false;">
    		</form>
    	</div>
    </div>

    <script type="text/javascript">
    
    	$("#name").blur(function(){
    		$.post("validateServlet",{
    			name:$('#name').val()
			},function(data){
				$("#error").html(data);
			}
		); 
    	})
    
		$().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
	  		$("#login").validate({
	  			onfocusout: function(element) { $(element).valid(); }, 
		    	rules: {
			      	name: "required",
			      	password: "required",
			  		checkCode:"required",
			  		confirm:{
			  	        required: true,
			  	        equalTo: "#password"
			  	      },
			  	    email: {
			  	        required: true,
			  	        email: true
			  	      }
			  	},
		   		messages: {
			      name: {
			        required: "请输入用户名"
			      },
			      password: {
			        required: "请输入密码"
			      },
			      checkCode: {
			        required: "请输入验证码"
			      }, 
			      confirm: {
			          required: "请输入密码",
			          equalTo: "两次密码输入不一致"
			        },
			      email: "请输入一个正确的邮箱",
		   		},
			});
			
	  		$("#getCode").click(function(){ 
	  			$("#getCode").val("已发送");
	  			$("#getCode").attr("disabled", true);
	  			$("#getCode").css("backgroundColor","#e1e5e3");
				var url = "checkEmailServlet";  
				$.post(url,{
						email:$('#email').val()
					},function(data){
						$("#register").attr("disabled",false);
					}
				);  
			});  
	 	});
		
		

    </script>
</body>
</html>