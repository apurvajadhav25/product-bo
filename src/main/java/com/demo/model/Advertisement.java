package com.demo.model;


import java.sql.Clob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	@Lob
    String imagePath;
	Boolean isEnable;
	
	public Advertisement() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Advertisement(int id, String name, String imagePath, Boolean isEnable) {
		super();
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.isEnable = isEnable;
	}





	
	
	
	

}
