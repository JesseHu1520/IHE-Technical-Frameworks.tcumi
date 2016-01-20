package edu.tcu.mi.ihe.constants.atna;

public enum EventActionCode {
	Create("C"),
	Read("R"),
	Update("U"),
	Delete("D"),
	Execute("E");
	
	private String value;
	
	EventActionCode(String v){
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
