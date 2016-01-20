package edu.tcu.mi.ihe.constants.atna;

public enum NetworkAccessPointTypeCode {

	MachineName("1"),
	IPAddress("2"),
	TelephoneNumber("3");
	
	private String value;
	
	NetworkAccessPointTypeCode(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
