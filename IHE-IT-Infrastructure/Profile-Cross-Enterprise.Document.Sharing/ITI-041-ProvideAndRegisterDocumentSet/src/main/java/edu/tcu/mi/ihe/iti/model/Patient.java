package edu.tcu.mi.ihe.iti.model;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class Patient extends BaseModel {
	@Expose @Getter @Setter
	private String id;
	@Expose @Getter @Setter
	private String name; // name pid05
	@Expose @Getter @Setter
	private String birthday; // birthday pid07
	@Expose @Getter @Setter
	private String gender; // gender pid08
	@Expose @Getter @Setter
	private String address; // address pid11
	@Expose @Getter @Setter
	private String phoneNumber; // phoneNumber pid13
}
