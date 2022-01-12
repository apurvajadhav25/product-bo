package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.demo.repository.ConfigurationRepository;

@Service
public class EmailSenderService {
	
	 @Autowired
	 private JavaMailSender mailSender;
	 
	 
	 public void sendSimpleEmail(String body, String toEmail, String fromEmail) {
	         //creating message
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(fromEmail);
	        message.setTo(toEmail);
	        message.setText(body);
	        message.setSubject("Welcome");
	      //  message.setCc(cc);
	     //   message.setBcc(bcc);
	     //  sending message 
	        mailSender.send(message);
	        System.out.println("Mail Send...");
	    }
}
