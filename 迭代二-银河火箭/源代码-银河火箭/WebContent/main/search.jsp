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
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/index.css" rel="stylesheet">
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
        <form id="search" action="search.jsp" method="post" onsubmit="return checkInput1();">
          <span class="glyphicon glyphicon-search"></span>
          <input type="text" class="searchInput" name="key">
          <input type="submit" value="确认">
        </form>
        <form class="form-horizontal article" role="form" action="#">   
          <div class="userImg">
                  <img src="../images/default-img.png" class="img-circle" width="100" height="100">
                  <img src="../images/default-img.png" class="img-circle" width="100" height="100">
                  <img src="../images/default-img.png" class="img-circle" width="100" height="100">
            </div>
        </form>
        
        <ul>
        	<%
        			String key = request.getParameter("key");
            		List<Task> tasks = Dao.select("where tree_level and task_text like'%" + key + "%'", Task.class);
			    	for(Task task:tasks){
			    		int task_author=task.task_author;
			    		int task_id=task.task_id;
			    		String task_text=task.task_text;
			    		User u = Dao.selectById(task_author, User.class);
			    		String user_name = u.user_name;
			    		int count = Dao.count(Task.class, "where parent_task_id = "+task_id+" AND tree_level = 2");
			    		if(task.getParent_task_id()!=null){  //即为转发贴
			    			Task t = Dao.selectById(task.getParent_task_id(), Task.class);
			    			User u1 = Dao.selectById(t.getTask_author(), User.class);
			    			task_text=task_text+"转发"+u1.getUser_name()+t.getTask_text();
			    		}
			    		
			    		List<Focus> fs = Dao.select("where user_id = "+user.getUser_id()+" AND focused_user_id = "+u.getUser_id(), Focus.class);
			    		String buttonText;
			    		if(fs.size()!=0){
			    			buttonText = "取消关注";
			    		}else{
			    			buttonText = "关注";
			    		};
			    		
			    		List<UserFavoriteTask> ufts = Dao.select("where user_id = "+user.getUser_id()+" AND task_id = "+task_id, UserFavoriteTask.class);
			    		String collect;
			    		if(ufts.size()!=0){
			    			collect = "取消收藏";
			    		}else{
			    			collect = "收&nbsp;藏";
			    		};
            	
            %>
          <li>
            <div class="task">
              <div class="userinfo">
                <div class="userImg">
                  <img src="../images/default-img.png" class="img-circle">
                </div>
                <div class="userName">
                  <h4><%=user_name %></h4>
                  <!-- 按钮触发模态框 -->
                  <button class="btn btn-primary btn-lg focus" data-toggle="modal" value="<%=task_author %>"><%=buttonText %></button>
                </div>

                <div class="clear"></div>
              </div>

              <div class="content">
                  <a href="detail.jsp?task_id=<%=task_id%>"><p><%=task_text %></p></a>
                  <div class="comment">
                   <!--<a  href="" style="text-decoration:none;"><span class="glyphicon glyphicon-thumbs-up" value="<%=task_id %>"></span></a>
                    <span class="glyphicon glyphicon-thumbs-down"></span>  -->
                  </div>
              </div>
            </div>

           <div class="button">
              <button type="button" class="btn btn-primary transpond" data-toggle="modal" value="<%=task_id %>">转&nbsp;&nbsp;发</button>
              <button type="button" class="btn btn-primary edit" value="<%=task_id %>" data-toggle="modal">评&nbsp;论(<%=count %>)</button>
              <button type="button" class="btn btn-primary heart" value="<%=task_id %>"><%=collect %></button>
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
          <a href="../operateUser?operate=exit"><button type="button" class="btn btn-primary">退出当前账号</button></a>
      </div>
    <div class="clear"></div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="comment" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <form role="form">
           <div class="modal-body">
              
               <div class="form-group">
                  <label for="uComment"></label>
                  <textarea class="form-control" id="uComment"></textarea>
               </div>    
	           </div>
	           <div class="modal-footer">
	              <button type="button" class="btn btn-default" 
	                 data-dismiss="modal">关闭
	              </button>
	              <button type="button" class="btn btn-primary" id="publish">
	                 	评论
	              </button>
	           </div>
         </form>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<script>
	function checkInput1(){
		var text = $(".searchInput").val();
		if(text ==""){
			alert("输入不能为空！");
			return false;
		}
	}

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
   
    $(".heart").click(function(e){
	   var $text = $(e.target).html();
	   $.post("../operateTask?operate=collect",{task_id:$(e.target).attr('value')},function(data){
		   if($text=='收&nbsp;藏'){
			   $(e.target).html('取消收藏');
		   }else{
			   $(e.target).html('收&nbsp;藏');
		   }
		    
	   })
   })

   $(".transpond").click(function(e){
	   $('#comment').val($(e.target).attr('value'));
	   $('#comment').attr("type","transpond")
	   $('#comment').modal('show');
   });
   
   $(".edit").click(function(e){
	   $('#comment').val($(e.target).attr('value'));
	   $('#comment').modal('show');
   });
   $("#publish").click(function(){
      	var text = $('#uComment').val();
  		$('#comment').modal('hide');
       var $type = $('#comment').attr("type");
       var url,forward;
       if($type=="transpond"){
       	url = "../operateTask?operate=publish";
       	forward = "index.jsp";
       }else{
       	url = "../operateTask?operate=comment";
       	forward = "detail.jsp";
       };
       if(text == ""){
       	alert("输入不能为空!");
       }else{
       	$.post(url,{text:text,task_id:$('#comment').val()},
                   function(data){
                 	window.location.href=forward+"?task_id=<%=session.getAttribute("task_id")%>";                
             })
       }
       
 });
  
</script>
</body>
</html>