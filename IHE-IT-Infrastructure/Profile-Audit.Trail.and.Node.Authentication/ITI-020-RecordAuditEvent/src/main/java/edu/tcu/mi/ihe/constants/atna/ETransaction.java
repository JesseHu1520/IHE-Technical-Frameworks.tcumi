package edu.tcu.mi.ihe.constants.atna;

import edu.tcu.mi.ihe.constants.atna.CodeType;
import edu.tcu.mi.ihe.constants.atna.EventActionCode;
import edu.tcu.mi.ihe.constants.atna.RFC3881;
import edu.tcu.mi.ihe.iti.rfc3881.CodedValueType;

public enum ETransaction {

	
	ITI_18_110112(new CodedValueType(RFC3881.EventID, "110112", CodeType.EventId), EventActionCode.Execute, new CodedValueType(RFC3881.EventTypeCode, "ITI-18", CodeType.EventType)),
	
	ITI_41_110106(new CodedValueType(RFC3881.EventID, "110106", CodeType.EventId), EventActionCode.Read, new CodedValueType(RFC3881.EventTypeCode, "ITI-41", CodeType.EventType)),
	ITI_41_110107(new CodedValueType(RFC3881.EventID, "110107", CodeType.EventId), EventActionCode.Create, new CodedValueType(RFC3881.EventTypeCode, "ITI-41", CodeType.EventType)),
	ITI_43_110107(new CodedValueType(RFC3881.EventID, "110107", CodeType.EventId), EventActionCode.Create, new CodedValueType(RFC3881.EventTypeCode, "ITI-43", CodeType.EventType));
	
	
	private CodedValueType eventID;
	private EventActionCode eventActionCode;
	private CodedValueType eventTypeCode;
	ETransaction(CodedValueType eventID, EventActionCode eventActionCode, CodedValueType eventTypeCode){
		this.eventID = eventID;
		this.eventActionCode = eventActionCode;
		this.eventTypeCode = eventTypeCode;
	}
	public CodedValueType getEventID() {
		return eventID;
	}
	public EventActionCode getEventActionCode() {
		return eventActionCode;
	}
	public CodedValueType getEventTypeCode() {
		return eventTypeCode;
	}
	
}
