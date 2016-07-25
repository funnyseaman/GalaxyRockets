package com.WRan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.WRan.dao.Dao;
import com.WRan.model.Task;
import com.WRan.model.User;
import com.WRan.model.UserFavoriteTask;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * Servlet implementation class operateTask
 */
@WebServlet("/operateTask")
public class operateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operateTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String method = request.getParameter("operate");
		
		if(method.equals("publish")){
			publish(request,response,u,session);
		}else if(method.equals("collect")){
			collect(request,response,u);
		}else if(method.equals("query")){
			query(request,response,session);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("comment")){
			comment(request,response,u,session);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//发布帖子（包括转发）
	public void publish(HttpServletRequest request, HttpServletResponse response,User u,HttpSession session) throws IOException{
		
		String text = request.getParameter("text");//发帖内容
		Timestamp taskDate = new Timestamp(System.currentTimeMillis());//发帖时间
		Task t = new Task();
		t.setTask_author(u.getUser_id());
		t.setTask_text(text);
		t.setTask_date(taskDate);
		t.setTree_level(1);
	
		String task_id = request.getParameter("task_id");		
		if(task_id!=null){		//转发内容
			int taskId = Integer.parseInt(task_id);
			t.setParent_task_id(taskId);
		}		
		try {
			int tid = Dao.insert(t);
			query(request,response,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("----------------haha");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('illegl!');");
			out.println("location.href='index.jsp'");
			out.println("</script>");
		}
	}
	
	//评论帖子
	public void comment(HttpServletRequest request, HttpServletResponse response,User u,HttpSession session){
		String task_text = request.getParameter("text");
		String task_id = request.getParameter("task_id");
		session.setAttribute("task_id", task_id);
		Timestamp taskDate = new Timestamp(System.currentTimeMillis());//评论时间
		int tid=Integer.parseInt(task_id);
		Task t = new Task();
		t.setParent_task_id(tid);
		t.setTask_text(task_text);
		t.setTree_level(2);
		t.setTask_date(taskDate);
		t.setTask_author(u.user_id);		
		int taskId = Dao.insert(t);
	}
	
	//收藏帖子
	public void collect(HttpServletRequest request, HttpServletResponse response,User u){
		String task_id = request.getParameter("task_id");
		int tid = Integer.parseInt(task_id);
		List<UserFavoriteTask> ufts = Dao.select("where task_id = "+tid+" AND user_id = "+u.getUser_id(), UserFavoriteTask.class);
		if(ufts.size()==0){//未收藏,加收藏
			UserFavoriteTask uft = new UserFavoriteTask();
			uft.setTask_id(tid);
			uft.setUser_id(u.getUser_id());
			int uft_id = Dao.insert(uft);
		}else{//已收藏，取消收藏
			Dao.delete(UserFavoriteTask.class, "where user_id = "+u.getUser_id()+" AND task_id = "+tid);
		}
	}
	
	//查询帖子
	public void query(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		List<Task> tasks = Dao.select("where tree_level = 1 ORDER BY task_date DESC", Task.class);
        session.setAttribute("tasks", tasks);
        User u = (User)session.getAttribute("user");
        if(u!=null)
        	response.sendRedirect("main/index.jsp");
        else
        	response.sendRedirect("index-pass.jsp");
	}
	
	//删除帖子
	public void delete(HttpServletRequest request, HttpServletResponse response){
		String task_id = request.getParameter("task_id");
		int tid=Integer.parseInt(task_id);
		Dao.delete(Task.class, "where task_id = "+tid);
	}

}
