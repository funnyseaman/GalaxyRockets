package com.WRan.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 90;
	private static int HEIGHT = 20;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		HttpSession session = request.getSession();
		ServletOutputStream sos = response.getOutputStream();
		//ÉèÖÃä¯ÀÀÆ÷²»Òª»º´æ´ËÍ¼Æ¬
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//´´½¨ÄÚ´æÍ¼Ïñ²¢»ñµÃÆäÍ¼ÐÎÉÏÏÂÎÄ
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		//²úÉúËæ»úµÄÈÏÖ¤Âë
		char[] rands = generateCheckCode();
		//²úÉúÍ¼Ïñ
		drawBackground(g);
		drawRands(g, rands);
		//½áÊøÍ¼ÏñµÄ»æÖÆ¹ý³Ì£¬Íê³ÉÍ¼Ïñ
		g.dispose();
		//½«Í¼ÏñÊä³öµ½¿Í»§¶Ë
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		//ÏÂÃæµÄÓï¾äÒ²¿ÉÒÔÐ´³É£ºbos.writeTo(sos);
		sos.write(buf);
		bos.close();
		sos.close();
		//½«µ±Ç°ÑéÖ¤Âë´æÈëµ½SessionÖÐ
		session.setAttribute("check_code", new String(rands));
	}
	private char[] generateCheckCode()
	{
		//¶¨ÒåÑéÖ¤ÂëµÄ×Ö·û±í
		String chars = "012345678901234567890123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[6];
		for(int i=0;i<6;i++)
		{
			int rand = (int)(Math.random()*82);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}
	
	private void drawBackground(Graphics g)
	{
		//»­±³¾°
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Ëæ»ú²úÉú120¸ö¸ÉÈÅµã
		for(int i=0;i<120;i++)
		{
			//Ëæ»úÎ»ÖÃ
			int x = (int)(Math.random()*WIDTH);
			int y = (int)(Math.random()*HEIGHT);
			//Ëæ»úÑÕÉ«
			int red = (int)(Math.random()*255);
			int green = (int)(Math.random()*255);
			int blue = (int)(Math.random()*255);
			//»æÖÆµã
			g.setColor(new Color(red,green,blue));
			g.drawOval(x, y, 1, 0);
		}
	}
	
	private void drawRands(Graphics g, char[] rands)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 18));
		g.drawString(""+rands[0], 1, 17);
		g.drawString(""+rands[1], 16, 15);
		g.drawString(""+rands[2], 31, 17);
		g.drawString(""+rands[3], 46, 16);
		g.drawString(""+rands[4], 61, 17);
		g.drawString(""+rands[5], 76, 15);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
