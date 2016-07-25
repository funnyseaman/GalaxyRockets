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
<title>个人资料</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/personInfo.css">
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
				<div id="pic">
				<form name="image-upload" method="post" action="../upLoadServlet" id="fileupload" enctype="multipart/form-data">
                     <h2 style="font-size: 18px;">请选择上传的头像：</h2>
                     <a class="a-upload">
	                     <input type="file" id="file" name="filename" accept="image/png,image/jpeg" onchange="previewImage(this);" disabled="disabled"/>
                     </a>
                     <br/>
                     <label>预览</label><br/>
                     <img id="preview" src="../user_img/default-img.png" width="150" height="150" class="img-circle"/><br/><br/>
                     <input class="btn btn-primary pull-right" type="submit" value="上传" id="submit"/>
                 </form>	
				</div>
				<div id="personInfo">
					昵&nbsp;称:<input type="text" name="name" id="name" placeholder="<%=user.getUser_name()%>" disabled="true"><br />
					邮&nbsp;箱:<input type="text" name="userEmail" id="userEmail" placeholder="<%=user.getUser_email()%>" disabled="true"><br />
					学&nbsp;校:<input type="text" name="userName" id="userName" placeholder="<%=user.getUser_school()%>"  disabled="true"><br />
					专&nbsp;业:<input type="text" name="userSchool" id="userSchool" placeholder="<%=user.getUser_major()%>"  disabled="true"></br><br />
					<a href="modifyInfo.jsp"><input type="button" value="更改资料"></a> 
					<a href="password.jsp"><input type="button" value="更改密码"></a> 
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>