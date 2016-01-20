package edu.tcu.mi.ihe.iti.ebxml.ihe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorListType;
import lombok.Getter;

@XmlType(name = "Document", namespace="urn:ihe:iti:xds-b:2007")
@XmlAccessorType (XmlAccessType.FIELD)
public class DocumentType {
	@Getter 
	@XmlAttribute(required=true)
	protected String id;

	@Getter 
	@XmlValue
	protected String value;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
