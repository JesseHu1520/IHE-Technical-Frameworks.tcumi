package edu.tcu.mi.ihe.iti.ebxml.rim;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name="Classification", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class ClassificationType {
	@Getter
	@XmlAttribute
	protected String id;

	@Getter
	@XmlAttribute(name="classificationScheme")
	protected String classificationScheme;

	@Getter
	@XmlAttribute(required=true)
	protected String classifiedObject;

	@Getter
	@XmlAttribute(name="nodeRepresentation")
	protected String nodeRepresentation;

	@Getter
	@XmlElement(name="Slot", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<SlotType> slots;

	@Getter
	@XmlElement(name="name", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected NameType name;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
	
}
