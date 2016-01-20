package edu.tcu.mi.ihe.hl7;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;

public abstract class HL7v2Message extends MessageBuilder {

	protected char Start_Block = '\u000b';
	protected char End_Block = '\u001c';
	protected char Carriage_Return = 13;
	
	@Override
	public OMElement getMessageFromXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
