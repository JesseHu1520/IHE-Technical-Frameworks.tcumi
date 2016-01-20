package edu.tcu.mi.ihe.constants.atna;

public enum ParticipantObjectTypeCode {
	/**
	 * Person(1)
	 */
	Person(1),
	
	/**
	 * SystemObject(2)
	 */
	SystemObject(2),
	
	/**
	 * Organization(3)
	 */
	Organization(3),
	
	/**
	 * Other(4)
	 */
	Other(4);

	private int value;
	ParticipantObjectTypeCode(int value){
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
