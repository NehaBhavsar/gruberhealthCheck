/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nehabha
 */
public class MailUtil {

	public static boolean sendMail(String emailAddr, String domainURL) throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.host", "https://gruberprep.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.user", "communications@gruberprep.com");
		props.put("mail.smtp.pwd", "gruberprep123!");

		Session session = Session.getInstance(props, null);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("andy@clairvoyantsoft.com"));
		msg.setSubject("Domain Down Alert");
		msg.setText("Hi Administrator, \n\n  Domain  URL ::: " + domainURL + " is not reachable ");
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddr));
		msg.setRecipient(Message.RecipientType.CC, new InternetAddress("nehabhavsar.er@gmail.com"));
		Transport transport = session.getTransport("smtp");
		transport.connect("https://gruberprep.com", 587, "communications@gruberprep.com", "gruberprep123!");
		System.out.println("Connection successful");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		return true;
	}

}
