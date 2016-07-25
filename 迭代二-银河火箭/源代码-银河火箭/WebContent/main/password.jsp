<%@ page import=" com.WRan.dao.Dao" %>
<%@ page import="com.WRan.model.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改个人资料</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/modifyInfo.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.1.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

</head>
<body>
	<div id="container">
    <div class="row" id="head">    
    </div>

    <div class="row" id="main">
      <ul class="nav nav-tabs">
       <li><a href="../operateTask?operate=query">首页</a></li>
       <li><a href="myfocus.jsp">关注动态</a></li>
       <li><a href="mypost.jsp">我的动态</a></li>
       <li><a href="personInfo.jsp">个人资料</a></li>
      </ul>
	      <div id="left">
				<div id="back">
					<form action="../operateUser?operate=password" method="post" id="modifyInfo" onsubmit="return checkInput();">
					旧密码:<input type="password" name="pass" id="pass"></br>
					新密码:<input type="password" name="newpass" id="newpass"></br>
					确认密码:<input type="password" name="confirm" id="confirm"></br>
					<input type="submit" name="submit" value="确定修改"> 
					
					</from>
				</div>
	
		</div>
	</div>
</div>
<script type="text/javascript">
	function checkInput(){
		var pass = $("#pass").val();
		var pass1 = $("#newpass").val();
		var pass2 = $("#confirm").val();
		if(pass1 != pass2){
			alert("两次输入的密码不一样");
			return false;
		}
		if(pass1 ==""){
				alert("新密码不能为空");
				return false;
		}
		if(pass ==""){
			alert("旧密码不能为空");
			return false;
		}
	}
</script>
</body>
</html>