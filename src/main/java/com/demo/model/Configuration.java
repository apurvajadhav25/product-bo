package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	int id;
	String key;
	String value;
	String language;
	
	public Configuration() {
		
	}
	
	public Configuration(int id, String key, String value, String language) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.language = language;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
