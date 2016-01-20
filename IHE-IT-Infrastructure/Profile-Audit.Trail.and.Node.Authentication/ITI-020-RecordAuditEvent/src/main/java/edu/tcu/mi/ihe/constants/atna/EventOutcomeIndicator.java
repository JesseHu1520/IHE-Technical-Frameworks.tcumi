package edu.tcu.mi.ihe.constants.atna;

public enum EventOutcomeIndicator {
	Success("0"),
	MinorFailure("4"),
	SeriousFaailure("8"),
	MajorFailure("12");
	private String value;
	
	EventOutcomeIndicator(String v){
		value = v ;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
