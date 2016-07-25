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
<%
	User user = (User)session.getAttribute("user");
%>
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
					<form action="../operateUser?operate=modify" method="post" id="modifyInfo">
					用户名:<input type="text" name="userName" value="<%=user.getUser_name()%>"><br/>
					签&nbsp;名:<input type="text" name="userSign" value="<%=user.getUser_sign()%>"><br/>
					性&nbsp;别:<input type="radio" name="userSex" value="0" id="man"/>男&nbsp;<input type="radio" name="userSex" value="1" id="woman" />女&nbsp;<input type="radio" id="none" value="2" name="userSex" />保密</br>
					学&nbsp;校:<input type="text" name="userSchool" value="<%=user.getUser_school()%>"><br/>
					专&nbsp;业:<input type="text" name="userMajor" value="<%=user.getUser_major()%>"><br/>
					<input type="submit" name="submit" value="确定修改"> 
					
					</from>
				</div>
	
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		var sex = '<%=user.getUser_sex()%>';
		var man = $("#man").val();
		var woman = $("#woman").val();
		if(sex==man){
			$("#man").attr("checked","checked");
		}else if(sex==woman){
			$("#woman").attr("checked","checked");
		}else{
			$("#none").attr("checked","checked");
		}
	})

</script>
</html>