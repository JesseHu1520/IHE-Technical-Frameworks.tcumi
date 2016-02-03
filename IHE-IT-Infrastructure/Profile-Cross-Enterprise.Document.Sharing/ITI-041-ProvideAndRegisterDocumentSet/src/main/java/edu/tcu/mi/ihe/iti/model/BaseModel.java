package edu.tcu.mi.ihe.iti.model;

import java.util.UUID;

import lombok.Getter;

public class BaseModel {
	@Getter
	protected String id;
	
	public BaseModel(){
		id = generateUUID();
	}

	protected String generateUUID() {
		UUID uid = UUID.randomUUID();
		return "urn:uuid:" + uid.toString();
	}
}
