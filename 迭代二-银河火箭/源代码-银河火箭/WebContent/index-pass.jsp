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
<title>首页</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index-pass.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>


</head>
<body>
<div id="container">
    <div class="row" id="head">    
    </div>

    <div class="row" id="main">
      <div id="left">       
        <ul>
        	<%
            		List<Task> tasks = (List<Task>)session.getAttribute("tasks");
			    	for(Task task:tasks){
			    		int task_author=task.task_author;
			    		int task_id=task.task_id;
			    		String task_text=task.task_text;
			    		User u = Dao.selectById(task_author, User.class);
			    		String user_name = u.user_name;
            	
            %>
          <li>
            <div class="task">
              <div class="userinfo">
                <div class="userImg">
                  <img src="images/default-img.png" class="img-circle">
                </div>
                <div class="userName">
                 	<%=u.user_name %>
                </div>

                <div class="clear"></div>
              </div>

              <div class="content">
                  <p><%=task.task_text %></p>
              </div>
            </div>
          </li>
           <%
			    	};
	       %>   
        </ul>
        </div>
        <div id="right">
          <img src="images/default-img.png" class="img-circle">
          <a href="register.jsp"><button type="button" class="btn btn-primary register">注&nbsp;&nbsp;&nbsp;&nbsp;册</button></a>
           <a href="login.jsp"><button type="button" class="btn btn-primary login">登&nbsp;&nbsp;&nbsp;&nbsp;录</button></a>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</body>
</html>