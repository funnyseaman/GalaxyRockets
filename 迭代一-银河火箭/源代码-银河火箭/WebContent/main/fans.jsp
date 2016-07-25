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
<title>粉丝</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/fans.css" rel="stylesheet">
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
        <ul>
        <%
        	String sql="where user_id in(select user_id from Focus where focused_user_id = "+user.getUser_id()+") ";
        	List<User> users = (List<User>)Dao.select(sql, User.class);
        	for(User u:users){
        		String name = u.getUser_name();
        		String sign = u.getUser_sign();
        		int task_author = u.getUser_id();
        		List<Focus> fs = Dao.select("where user_id = "+user.getUser_id()+" AND focused_user_id = "+task_author, Focus.class);
	    		String buttonText;
	    		if(fs.size()!=0){
	    			buttonText = "取消关注";
	    		}else{
	    			buttonText = "关注";
	    		};
	    		
	    		
        %>
          <li>
            <div class="task">
              <div class="userinfo">
                <div class="userImg">
                  <img src="../images/default-img.png" class="img-circle">
                </div>
                <div class="userName">
                  <h4><%=name %></h4>
                  <h4><%=sign %></h4>
                  <!-- 按钮触发模态框 -->
                  
                </div>
                <button class="btn btn-primary btn-lg focus" data-toggle="modal" 
                   data-target="#myModal" value="<%=task_author %>">
                   <%=buttonText %>
                </button>
                <div class="clear"></div>
              </div>
            </div>
          </li>
		   <%
			    	};
	       %>  
        </ul>
        </div>
         <div id="right">
          <div class="user">
            <img src="../images/default-img.png" class="img-circle">
            <div class="name">
              <p><strong>用户名：</strong><span><%=user.user_name %></span></p>
              <p><strong>个性签名：</strong><span><%=user.user_sign %></span></p>
            </div>
            <div class="clear"></div>
          <div class="button">
            <a href="fans.jsp"><button type="button" class="btn btn-primary">粉丝</button></a>
            <a href="idol.jsp"><button type="button" class="btn btn-primary">关注</button></a>
            <a href="collect.jsp"><button type="button" class="btn btn-primary">收藏</button></a>
          </div>
          <a href="../operateUser?operate?exit"><button type="button" class="btn btn-primary">退出当前账号</button></a>
      </div>
    <div class="clear"></div>
<script>

   $(".focus").click(function(e){
	   var $text = $(e.target).html();
	   $.post("../operateUser?operate=focus",{focus_id:$(e.target).attr('value')},function(data){
		   if($text=='关注'){
			   $(e.target).html('取消关注');
		   }else{
			   $(e.target).html('关注');
		   }
		   window.location.reload(); 
		    
	   })
   })
   
</script>
</body>
</html>