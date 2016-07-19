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
<link href="css/index.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>


</head>
<body>
<body>
<div class="container">
   <div class="row" style="height:200px;background:#b7e1da;">
       
  </div>
  
    <div class="row">
        <div class="col-xs-8" id="left">
        <form class="form-horizontal" role="form" action="taskServlet" method="post">   
			  <div class="form-group">
			    <label for="name">文本框</label>
			    <textarea class="form-control" rows="3" name="text"></textarea>
			  </div>

			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-10">
			         <button type="submit" class="btn btn-default">发布</button>
			      </div>
			   </div>
			</form>

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
	                       <h4>
	                      	<%=user_name %>
	                      </h4>
	                      
	                      <!-- 按钮触发模态框 -->
	                      <button class="btn btn-primary btn-lg" data-toggle="modal" 
	                         data-target="#myModal">
	                         <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;关注
	                      </button>
	                    </div>
	
	                    <div class="clear"></div>
	                  </div>
	
	                  <div class="content">
	                      <p><%=task_text %></p>
	                      <div class="comment">
	                        <a  href="" style="text-decoration:none;"><span class="glyphicon glyphicon-thumbs-up"></span></a>
	                        <span class="glyphicon glyphicon-thumbs-down"></span>
	                      </div>
	                  </div>
	                </div>
	
	                <div>
	                  <button type="button" class="btn btn-primary" data-toggle="modal" 
	                         data-target="#myModal"><span class="glyphicon glyphicon-new-window"></span></button>
	                  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-comment"></span></button>
	                  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-heart"></span></button>
	                </div>
	              </li> 
	            
	            <%
			    	};
	            %>          
            </ul>
        </div>
        <div class="col-xs-3" id="right">
            <img src="images/default-img.png" class="img-circle">
            <button type="button" class="btn btn-primary">登录</button>
            <button type="button" class="btn btn-primary">注册</button>
        </div>
    </div>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
                         aria-labelledby="myModalLabel" aria-hidden="true">
                         <div class="modal-dialog">
                            <div class="modal-content">
                               <div class="modal-header">
                                  <button type="button" class="close" 
                                     data-dismiss="modal" aria-hidden="true">
                                        &times;
                                  </button>
                                  <h4 class="modal-title" id="myModalLabel">
                                     模态框（Modal）标题
                                  </h4>
                               </div>
                               <div class="modal-body">
                                   <select class="form-control" name="tianshu">
                                      <option value="0">不参与回访</option>
                                      <option value="1">一天</option>
                                      <option value="3">三天</option>
                                      <option value="7">一星期</option>
                                      <option value="30">一个月</option>
                                      <option value="90">三个月</option>
                                    </select>
                               </div>
                               <div class="modal-footer">
                                  <button type="button" class="btn btn-default" 
                                     data-dismiss="modal">关闭
                                  </button>
                                  <button type="button" class="btn btn-primary">
                                     提交更改
                                  </button>
                               </div>
                            </div><!-- /.modal-content -->
                          </div><!-- /.modal -->
</div>


<script type="text/javascript">
 
$(".glyphicon-thumbs-up").click(function(e){
	var url = "tasksOperate"; 
	$.post(url,{
		task_id:$(e.target).attr('value')
		}
	);  

    if($("#thumb").attr("src")=="../Icons/zancheng.png"){
        $("#thumb").attr("src","../Icons/fandui.png");
    }else{
        $("#thumb").attr("src","../Icons/zancheng.png");
    }
	return false;
})
</script>
</body>
</html>