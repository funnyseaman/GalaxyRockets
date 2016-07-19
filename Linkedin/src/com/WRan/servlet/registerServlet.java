package com.WRan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.WRan.dao.Dao;
import com.WRan.model.User;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("name");
		String userEmail = request.getParameter("email");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		int count = Dao.count(User.class, String.format("where user_name='%s' ", userName));
		if(count>0)
		{
			out.println("<script language='javascript'>");
			out.println("alert('This userName existed!');");
			out.println("location.href='register.jsp'");
			out.println("</script>");
		}
		
		else if(!checkCode.equals(request.getSession().getAttribute("email_check_code").toString().toLowerCase()))
		{
			out.println("<script language='javascript'>");
			out.println("alert('The email checkCode is wrong!');");
			out.println("location.href='register.jsp'");
			out.println("</script>");
		}
		else
		{
			User u = new User(userName,password,userEmail);
			Dao.insert(u);
			HttpSession session = request.getSession();
            session.setAttribute("user", u);
            out.println("<script language='javascript'>");
			out.println("alert('successful!');");
			out.println("location.href='index.jsp'");
			out.println("</script>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
