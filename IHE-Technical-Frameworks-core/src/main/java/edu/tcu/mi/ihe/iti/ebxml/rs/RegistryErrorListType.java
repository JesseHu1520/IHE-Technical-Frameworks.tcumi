package edu.tcu.mi.ihe.iti.ebxml.rs;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="RegistryErrorList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegistryErrorListType {

	@Getter 
	@XmlElement(name="RegistryError", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
	protected List<RegistryErrorType> registryErrors;
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
