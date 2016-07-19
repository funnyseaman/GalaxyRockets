package com.WRan.servlet;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class taskServlet
 */
@WebServlet("/taskServlet")
public class taskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");//发帖人
		String text = request.getParameter("text");//发帖内容
		Timestamp taskDate = new Timestamp(System.currentTimeMillis());//发帖时间
		Task t = new Task();
		t.setTask_author(u.getUser_id());
		t.setTask_text(text);
		t.setTask_date(taskDate);
		t.setTree_level(1);
		int tid = Dao.insert(t);
		System.out.println(tid);
		System.out.println(text);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
