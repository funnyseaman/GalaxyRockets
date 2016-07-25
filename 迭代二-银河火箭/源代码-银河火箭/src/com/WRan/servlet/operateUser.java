package com.WRan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.WRan.dao.Dao;
import com.WRan.model.Focus;
import com.WRan.model.User;

/**
 * Servlet implementation class operateUser
 */
@WebServlet("/operateUser")
public class operateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String method = request.getParameter("operate");
		System.out.println(method);
		if(method.equals("login")){
			login(request,response);
		}else if(method.equals("register")){
			register(request,response);
		}else if(method.equals("exit")){
			exit(response,session);
		}else if(method.equals("modify")){
			modify(request,response,session);
		}else if(method.equals("password")){
			password(request,response,session);
		}else if(method.equals("focus")){
			focus(request,response,session);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//用户登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode").toLowerCase();
		String savedCode = ((String)request.getSession().getAttribute("check_code")).toLowerCase();
		List<User> users = (List<User>)Dao.select(String.format("where user_name='%s' and user_password='%s'", userName, password), User.class);
		int count = 0;
		if(users.size()!=0){
			User u = users.get(0);
			if(u.getUser_password().equals(password))
				count=1;
		}
	    if (count<1){
	    	out.println("<script language='javascript'>");
			out.println("alert('username is wrong! or password is wrong');");
			out.println("location.href='login.jsp'");
			out.println("</script>");
	    }else{
	    	if(!checkCode.equals(savedCode)){
	    		out.println("<script language='javascript'>");
				out.println("alert('The  checkCode is wrong!');");
				out.println("location.href='login.jsp'");
				out.println("</script>");
	    	}else{
	    		 User user = Dao.select(String.format("where user_name='%s' ", userName), User.class).get(0);
	             HttpSession session = request.getSession();
	             session.setAttribute("user", user); 
	             response.sendRedirect("operateTask?operate=query");
	    	}
	    }
	}
	
	//用户注册
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("name");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode").toLowerCase();
		if(!checkCode.equals(request.getSession().getAttribute("email_check_code").toString().toLowerCase()))
		{
			out.println("<script language='javascript'>");
			out.println("alert('The email checkCode is wrong!');");
			out.println("location.href='register.jsp'");
			out.println("</script>");
		}
		else
		{
			User u = new User(userName,password,userEmail);
			int uid = Dao.insert(u);
			u.setUser_id(uid);
			HttpSession session = request.getSession();
            session.setAttribute("user", u);
            out.println("<script language='javascript'>");
			out.println("alert('successful!');");
			out.println("location.href='operateTask?operate=query'");
			out.println("</script>");
		}
	}
	
	//退出登录
	public void exit(HttpServletResponse response,HttpSession session) throws IOException{		
        session.setAttribute("user", null);
        response.sendRedirect("login.jsp");
	}
	
	//修改资料
	public void modify(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		User u = (User) session.getAttribute("user");
		
		String userSign = request.getParameter("userSign");
		String userName = request.getParameter("userName");
		
		
		String userSex = request.getParameter("userSex");
		if(userSex!=null){
			int sex = Integer.parseInt(userSex);
			u.setUser_sex(sex);
		}	
		String userSchool = request.getParameter("userSchool");
		String userMajor = request.getParameter("userMajor");
		if(userName!=""){
			int count = Dao.count(User.class, String.format("where user_name='%s' and user_id <> '%s'", userName,u.getUser_id()));
			if(count>0){
				out.println("<script language='javascript'>");
				out.println("alert('This userName existed!');");
				out.println("location.href='main/modifyInfo.jsp'");
				out.println("</script>");
			}else{
				u.setUser_name(userName);
			}			
		}
		
		if(userSign!=""){
			u.setUser_sign(userSign);
		}
		if(userSchool!=""){
			u.setUser_school(userSchool);
		}
		if(userMajor!=""){
			u.setUser_major(userMajor);
		}		
		Dao.update(u);			
		out.println("<script language='javascript'>");
		out.println("alert('success!');");
		out.println("location.href='main/personInfo.jsp'");
		out.println("</script>");
		
		
	}
	
	//修改密码
	public void password(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		User u = (User) session.getAttribute("user");
		String pass = request.getParameter("pass");
		String newpass = request.getParameter("newpass");
		if(u.getUser_password().equals(pass)){
			u.setUser_password(newpass);
			Dao.update(u);	
			out.println("<script language='javascript'>");
			out.println("alert('success!');");
			out.println("location.href='login.jsp'");
			out.println("</script>");
		}else{
			out.println("<script language='javascript'>");
			out.println("alert('旧密码不对!');");
			out.println("location.href='main/password.jsp'");
			out.println("</script>");
		}
	}	
	
	//关注用户
	public void focus(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		User u = (User) session.getAttribute("user");//关注人
		String focus_id = request.getParameter("focus_id");
		int focused_user_id = Integer.parseInt(focus_id);
		List<Focus> fs = Dao.select("where user_id = "+u.getUser_id()+" AND focused_user_id = "+focused_user_id, Focus.class);
		System.out.println(fs.size());
		if(fs.size()==0){//未关注,加关注
			Focus f = new Focus();
			f.setFocused_user_id(focused_user_id);
			f.setUser_id(u.getUser_id());
			int fid = Dao.insert(f);
			System.out.println(fid);
		}else{//已关注，取消关注
			Dao.delete(Focus.class, "where user_id = "+u.getUser_id()+" AND focused_user_id = "+focused_user_id);
		}
	}

}
