package edu.tcu.mi.ihe.iti.core;

import java.sql.Timestamp;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public abstract class MessageBuilder {
	public static Logger logger = Logger.getLogger(MessageBuilder.class);

	public abstract OMElement getMessageFromXML();
	public abstract String getMessageFromHL7v2();
	protected abstract boolean validate();

	
	@Expose @Getter @Setter
	private String endpoint;
	
	protected String generateTimeStamp() {
		String value = new Timestamp(System.currentTimeMillis()).toString();
		value = value.replaceAll("\\D+", "").substring(0, 14);
		return value;
	}
}
