package edu.tcu.mi.ihe.iti.model;

import java.util.UUID;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class BaseModel {
	@Getter
	protected String id;
	
	@Expose @Getter @Setter
	private String endpoint;
	
	public BaseModel(){
		id = generateUUID();
	}

	protected String generateUUID() {
		UUID uid = UUID.randomUUID();
		return "urn:uuid:" + uid.toString();
	}
}
