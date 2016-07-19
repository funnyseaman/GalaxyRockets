<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link href="css/login.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
    <div id="container">
    	<div id="head">   		
    	</div>
    	<div id="main">
    		<form action="loginServlet" method="post" id="login">
    			<input type="text" name="name" id="name" placeholder="用户名"><br/>
    			<input type="password" name="password" id="password" placeholder="密码"><br/>
    			<input type="text" name="checkCode" id="checkCode" placeholder="验证码">
    			<div class="checkImg">
    				<img src="">	
    			</div><br/>
    			<input type="submit" value="登录"><br/>
    		</form>
    	</div>
    </div>

    <script type="text/javascript">
		$().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
	  		$("#login").validate({
	  			onfocusout: function(element) { $(element).valid(); }, 
		    	rules: {
			      	name: "required",
			      	password: "required",
			  		checkCode:"required",
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
			      }
		   		},
			});
	 	});
    </script>
</body>
</html>