package com.WRan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.WRan.dao.Dao;
import com.WRan.model.User;

/**
 * Servlet implementation class upLoadServlet
 */
@WebServlet("/upLoadServlet")
public class upLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
			String baseURL = getServletContext().getRealPath("/")+"user_img/";
			System.out.println(baseURL);
		 	DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload fileUpload = new ServletFileUpload(factory);
	        OutputStream outputStream = null;
	        InputStream inputStream = null;
	        String fname = request.getParameter("filename");
	        
	        System.out.println("----"+fname);
	        System.out.println("fanme:"+fname);
	        if(fname==null){
	        	response.sendRedirect(request.getHeader("Referer")+"?error=����Ҫ�ϴ���ͼƬ");
	        }else{
	        	HttpSession session = request.getSession();
	        	User user = (User) session.getAttribute("user");
	        	try {
	        		List items = fileUpload.parseRequest(request);
	        		byte[] bs = new byte[1024];
	        		for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
	        			FileItem name = (FileItem) iterator.next();
	        			if (!name.isFormField()) {
	                    String fileName = name.getName();
	                    System.out.println("fileName"+fileName);
	                    if(fileName==""){
	                        System.out.println("NULL!!!");
	                        return;
	                    }
	                    String lastFileName = baseURL + fname
	                            + fileName.substring(fileName.lastIndexOf("."), fileName.length());
	                    user.setUser_head((fname + fileName.substring(fileName.lastIndexOf("."), fileName.length())));
	                    Dao.update(user);
	                    outputStream = new FileOutputStream(new File(lastFileName));
	                    inputStream = name.getInputStream();
	                    int length = 0;
	                    while (null != inputStream && (length = inputStream.read(bs)) != -1) {
	                        outputStream.write(bs);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            outputStream.flush();
	            outputStream.close();
	            response.sendRedirect(request.getHeader("Referer"));
	        }
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
