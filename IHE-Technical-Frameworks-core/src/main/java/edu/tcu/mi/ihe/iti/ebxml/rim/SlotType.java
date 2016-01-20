package edu.tcu.mi.ihe.iti.ebxml.rim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="Slot", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class SlotType {

	@Getter
	@XmlAttribute
	protected String name;
	
	@Getter
	@XmlElement(name="ValueList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected ValueListType valueList;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
