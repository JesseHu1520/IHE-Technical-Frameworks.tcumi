package edu.tcu.mi.ihe.constants.atna;

import edu.tcu.mi.ihe.iti.rfc3881.CodedValueType;

public enum ParticipantObjectIDTypeCode {
	StudyInstanceUID("110180"),
	SOPClassUID("110181"),
	NodeID("110182"),
	MedicalRecordNumber("1"),
	PatientNumber("2"),
	EncounterNumber("3"),
	EnrolleeNumber("4"),
	SocialSecurityNumber("5"),
	AccountNumber("6"),
	GuarantorNumber("7"),
	ReportName("8"),
	ReportNunber("9"),
	SearchCriteria("10"),
	UserIdentifier("11"),
	URI("12"),
	PatientDemographicsQuery("ITI-21"),
	SubmissionSetClassificationNode("urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd");
	
	private CodedValueType value;
	ParticipantObjectIDTypeCode(String code){
		this.value = new CodedValueType(RFC3881.ParticipantObjectIDTypeCode, code, CodeType.ObjectIdType);
	}
	
	/**
	 * @return the value
	 */
	public CodedValueType getValue() {
		return value;
	}
}
