package edu.tcu.mi.ihe.iti.ebxml.rim;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="RegistryObject", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegistryPackageType {
	@Getter
	@XmlAttribute(required=true)
	protected String id;

	@Getter
	@XmlAttribute
	protected String home;

	@Getter
	@XmlElement(name="Name", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected NameType name;

	@Getter
	@XmlElement(name="Description", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected DescriptionType description;

	@Getter
	@XmlElement(name="Slot", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<SlotType> slots;

	@Getter
	@XmlElement(name="Classification", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<ClassificationType> classifications;

	@Getter
	@XmlElement(name="ExternalIdentifier", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<ExternalIdentifierType> externalIdentifiers;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}	
}
