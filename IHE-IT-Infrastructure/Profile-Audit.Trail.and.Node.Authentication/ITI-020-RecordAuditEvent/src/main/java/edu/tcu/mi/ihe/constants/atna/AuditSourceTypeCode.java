package edu.tcu.mi.ihe.constants.atna;

public enum AuditSourceTypeCode {

	One("1"),
	Two("2"),
	Three("3");
	
	
	private String value;
	
	AuditSourceTypeCode(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
