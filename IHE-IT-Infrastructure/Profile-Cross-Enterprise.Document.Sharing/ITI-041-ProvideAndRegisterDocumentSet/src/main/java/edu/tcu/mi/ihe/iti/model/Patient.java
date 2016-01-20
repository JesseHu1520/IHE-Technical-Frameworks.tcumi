package edu.tcu.mi.ihe.iti.model;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class Patient extends BaseModel {
	@Expose @Getter @Setter
	private String patientId;
	@Expose @Getter @Setter
	private String patientName; // name pid05
	@Expose @Getter @Setter
	private String patientBirthday; // birthday pid07
	@Expose @Getter @Setter
	private String patientGender; // gender pid08
	@Expose @Getter @Setter
	private String patientAddress; // address pid11
	@Expose @Getter @Setter
	private String patientPhoneNumber; // address pid13
}
