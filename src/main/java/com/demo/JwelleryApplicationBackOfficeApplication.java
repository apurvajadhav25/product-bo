package com.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.controller.HomeController;
import com.demo.model.Translation;

@SpringBootApplication
public class JwelleryApplicationBackOfficeApplication {
	
	@Autowired
	HomeController controller;
		
    static Map map = new HashMap<String, Map>();

	public static void main(String[] args) {
		SpringApplication.run(JwelleryApplicationBackOfficeApplication.class, args);
	}
	
	@PostConstruct()
	public void init() {
		map = controller.getTranslation();
		setTransValue(map);
        System.out.println(map);
	}
	
	public Map<String, Map> getTransValue() {
		return map;
	}

	public void setTransValue(Map<String, Map> transValue) {
		map = transValue;
		System.out.println(map);
	}

}
