package edu.tcu.mi.ihe.iti.ebxml.rim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name="Description", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class DescriptionType {

	@Getter
	@XmlElement(name="LocalizedString", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected LocalizedStringType localizedString;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
