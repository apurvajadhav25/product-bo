package com.demo.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.EmailSenderService;
@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailSenderService service;
	
	@GetMapping("/sendemail")
	public String sendmail(@RequestParam("name") String name,
			               @RequestParam("email") String email,
			               @RequestParam("message") String message,
			               @RequestParam("toEmail") String toEmail,
			               @RequestParam("fromEmail") String fromEmail) throws MessagingException{
		String body="Name: "+name+"\n"+
				    "Email: "+email+"\n"+
				    "Message: "+message;
		service.sendSimpleEmail(body, toEmail, fromEmail);
		return "Email sent successfully";
	}	

}
