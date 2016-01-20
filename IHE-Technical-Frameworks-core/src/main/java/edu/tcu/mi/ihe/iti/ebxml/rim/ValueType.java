package edu.tcu.mi.ihe.iti.ebxml.rim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name="Value", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class ValueType {

	@Getter
	@XmlValue
	protected String text;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
