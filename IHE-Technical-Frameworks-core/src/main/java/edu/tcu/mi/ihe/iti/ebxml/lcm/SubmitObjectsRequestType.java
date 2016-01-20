package edu.tcu.mi.ihe.iti.ebxml.lcm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorType;
import lombok.Getter;


@XmlType(name = "SubmitObjectsRequest", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class SubmitObjectsRequestType {

	@Getter 
	@XmlElement(name="RegistryObjectList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected RegistryObjectListType registryObjectList;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
