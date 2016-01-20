package edu.tcu.mi.ihe.iti.ebxml.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="RegistryErrorType", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegistryErrorType {
	@Getter 
	@XmlAttribute(name = "codeContext")
	protected String codeContext;
	
	@Getter 
	@XmlAttribute(name = "errorCode")
	protected String errorCode;
	
	@Getter 
	@XmlAttribute(name = "location")
	protected String location;
	
	@Getter 
	@XmlAttribute(name = "severity")
	protected String severity;
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
