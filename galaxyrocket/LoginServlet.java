package galaxyrocket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		String checkCode = request.getParameter("checkCode");
		String savedCode = ((String)request.getSession().getAttribute("check_code")).toLowerCase();
		
		String sql = "select count(*) from student where user_name='"+userName+"' and user_password='"+userPwd+"'";
		ConnectMysql conMysql = new ConnectMysql();
		//�ж���֤���Ƿ���ȷ
		if(!checkCode.equals(savedCode))
		{
			//��֤�����
		}
		//�ж��˻������Ƿ�ƥ��
		else if(conMysql.inputSelectCount(sql).equals("0"))
		{
			//�û������������
		}
		//�ɹ���½��������Ϣ
		else
		{
//			User user = new User();
//			user.setUsername(username);
//			user.setPassword(password);
//			request.getSession().setAttribute("user", user);
//			response.sendRedirect("   ");
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
