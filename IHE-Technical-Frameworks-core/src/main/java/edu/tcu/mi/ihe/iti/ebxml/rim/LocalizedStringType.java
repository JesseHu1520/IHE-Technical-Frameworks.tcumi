package edu.tcu.mi.ihe.iti.ebxml.rim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="LocalizedString", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class LocalizedStringType {
	@Getter
	@XmlAttribute(name="value")
	protected String value;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}	
	
}
