package com.WRan.util;


import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailVerify implements IMailService
{
	private Properties prop = new Properties();
	private Session session;
	private Transport trans;
	private Store store;
	private String smtpHost = "smtp.163.com";
	private String pop3Host = "pop.163.com";
	private String m_email = "galaxyrocket@163.com";
	private int oldUnreadMail;
	private int newUnreadMail;
	private String body;
	
	@Override
	public void connect() throws MessagingException {
		
		prop.put("mail.smtp.host", smtpHost);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.store.protocol", "pop3");
		session = Session.getDefaultInstance(prop);
		trans = session.getTransport("smtp");
		trans.connect(smtpHost, m_email, "summer2016");
		store = session.getStore("pop3");
		store.connect(pop3Host, m_email, "summer2016");
		
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		oldUnreadMail = folder.getUnreadMessageCount();
	}

	@Override
	public void send(String recipient, String subject, Object content) throws MessagingException {
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(m_email));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);
		message.setContent(content, "text/html;charset=UTF-8");
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}

	@Override
	public boolean listen() throws MessagingException {
		
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		newUnreadMail = folder.getUnreadMessageCount();
		if(newUnreadMail > oldUnreadMail)
		{
			return true;
		}
		return false;
	}

	@Override
	public String getReplyMessageContent(String sender, String subject) throws MessagingException, IOException {

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		Message message[] = folder.getMessages();
		for(int i=0;i<message.length;i++)
		{
			if(message[i].getSubject().equals(subject))
			{
				body = message[i].getContent().toString();
				return body;
			}
		}
		return null;
	}
	

}

