package edu.tcu.mi.ihe.iti.ebxml.query;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorType;
import lombok.Getter;

@XmlRootElement(name = "AdhocQueryResponse", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class AdhocQueryResponseType {
	@Getter 
	@XmlAttribute(required=true)
	protected String status;
	
	@Getter 
	@XmlElement(name="RegistryObjectList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected RegistryObjectListType registryObjectList;

	@Getter 
	@XmlElement(name="RegistryErrorList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
	protected RegistryErrorListType registryErrorList;
	

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}	
}
