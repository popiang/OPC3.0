package com.popiang.opc.utilities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/*
 * this class handles emailing excel file to admin email account
 */

@Component
public class EmailSender
{
	//this method emails excel file to admin email account
	public boolean emailFile(HttpServletRequest context, String event, String fileName)
	{
		String host = "mail.mypersonalprojects.net";
		String username = "popiang@mypersonalprojects.net";
		String password = "bnp8749";
		String emailTo = "popiang@hotmail.com";
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(username, password);
				}
			}
		);		
		
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setSubject("Leads Collected From Event " + event);
			message.setText("Leads Collected From Event " + event);
			
			System.out.println("filepath: " + fileName);
			
			MimeBodyPart bodyPart = new MimeBodyPart();
			
			DataSource source = new FileDataSource(fileName);
			bodyPart.setDataHandler( new DataHandler(source) );
			bodyPart.setFileName(fileName);
			
			Multipart multiPart = new MimeMultipart();
			
			multiPart.addBodyPart(bodyPart);
			
			message.setContent(multiPart);
			
			Transport.send(message);
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}

		return true;
	}
}

























