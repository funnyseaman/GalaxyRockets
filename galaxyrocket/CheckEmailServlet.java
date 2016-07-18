package galaxyrocket;


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

import emailverify.IMailService;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email_need_verify = request.getParameter("userEmail");
		HttpSession session = request.getSession();
		try 
		{
			Reflections reflections = new Reflections("emailverify");
			Set<Class<? extends IMailService>> impls = reflections.getSubTypesOf(IMailService.class);
			Class<? extends IMailService> homework = impls.iterator().next();
			IMailService service = homework.newInstance();
			service.connect();
			int random = new Random().nextInt(900000)+100000;
			session.setAttribute("email_check_code", random);
			service.send(email_need_verify, "����\"�����\"����֤�ļ�", "��֤�룺"+Integer.toString(random));
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
