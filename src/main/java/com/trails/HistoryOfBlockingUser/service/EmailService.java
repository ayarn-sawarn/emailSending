package com.trails.HistoryOfBlockingUser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String emailid, String str) {
		SimpleMailMessage msg= new SimpleMailMessage();
		msg.setFrom("ayamshifo@gmail.com");
		msg.setTo(emailid);
		msg.setText(str);
		msg.setSubject("your otp ||");
		
		javaMailSender.send(msg);
		System.out.println("mail send sucessfully");
	}
}
