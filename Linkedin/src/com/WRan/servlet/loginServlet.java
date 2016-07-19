package com.WRan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		//String savedCode = ((String)request.getSession().getAttribute("check_code")).toLowerCase();
		int count = Dao.count(User.class, String.format("where user_name='%s' and user_password='%s'", userName, password));
        if (count<1){
            String info = URLEncoder.encode("用户名或密码不正确, 请重新输入!", "UTF-8");
            out.write("用户名或密码不正确, 请重新输入!");
           // response.sendRedirect(String.format("%s?%s=%s&%s=%s", PathConst.PagePath.login, KeyConst.SYSINFO, "error","info",info));
        }else{
        	 User user = Dao.select(String.format("where user_name='%s' ", userName), User.class).get(0);
             HttpSession session = request.getSession();
             session.setAttribute("user", user); 
             response.sendRedirect("getTasks");
             //request.getRequestDispatcher("想跳转的servlet名").forward(request, response);
             //out.write("/index.jsp");
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
