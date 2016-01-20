package edu.tcu.mi.ihe.iti.model;

import lombok.Getter;
import lombok.Setter;

public class Association extends BaseModel {
	@Getter @Setter
	private String sourceObject;
	@Getter @Setter
	private String targetObject;
	@Getter @Setter
	private String association;
	@Getter @Setter
	private String note;
}
