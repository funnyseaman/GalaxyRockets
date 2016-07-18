package galaxyrocket;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String checkCode = request.getParameter("checkCode");
		String userPwd = request.getParameter("userPwd");
		String againPwd = request.getParameter("againPwd");
		
		ConnectMysql conMysql = new ConnectMysql();
		String selectSql = "select count(*) from student where userName='"+userName+"'";
		
		//判断用户名是否冲突
		if(conMysql.inputSelectCount(selectSql).equals("1"))
		{
			//弹出消息提示框
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('This userName existed!');");
			out.println("location.href='Register.jsp'");
			out.println("</script>");
		}
		//判断两次密码是否一致
		else if(!userPwd.equals(againPwd))
		{
			//弹出消息提示框
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('These two password are wrong!');");
			out.println("location.href='Register.jsp'");
			out.println("</script>");
		}
		//判断邮箱验证码是否正确
		else if(!checkCode.equals(request.getSession().getAttribute("email_check_code").toString().toLowerCase()))
		{
			//弹出消息提示框
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('The email checkCode is wrong!');");
			out.println("location.href='Register.jsp'");
			out.println("</script>");
		}
		else
		{
			String updateSql = "insert into user values ('"+userName+"','"+userPwd+"','"+userEmail+"')";
			conMysql.inputUpdate(updateSql);
			//弹出消息提示框
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('Register successed!');");
			out.println("location.href='Login.html'");
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
