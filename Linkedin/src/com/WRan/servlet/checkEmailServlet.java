package com.WRan.servlet;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.reflections.Reflections;

import com.WRan.util.IMailService;

/**
 * Servlet implementation class checkEmailServlet
 */
@WebServlet("/checkEmailServlet")
public class checkEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email_need_verify = request.getParameter("email");
		System.out.println("----email------"+email_need_verify);
		HttpSession session = request.getSession();
		try 
		{
			Reflections reflections = new Reflections("com.WRan.util");
			Set<Class<? extends IMailService>> impls = reflections.getSubTypesOf(IMailService.class);
			Class<? extends IMailService> homework = impls.iterator().next();
			IMailService service = homework.newInstance();
			service.connect();
			int random = new Random().nextInt(900000)+100000;
			session.setAttribute("email_check_code", random);
			service.send(email_need_verify, "来自\"宠物店\"的验证文件", "验证码："+Integer.toString(random));
		} catch (Exception e) {
			// TODO: handle exception
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
