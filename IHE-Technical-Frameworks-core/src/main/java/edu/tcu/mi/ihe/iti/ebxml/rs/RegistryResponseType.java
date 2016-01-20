package edu.tcu.mi.ihe.iti.ebxml.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;

@XmlRootElement(name = "RegistryResponse", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegistryResponseType {

	@Getter 
	@XmlAttribute(name = "status")
	protected String status;
	
	@Getter 
	@XmlElement(name = "RegistryErrorList")
	protected RegistryErrorListType registryErrorList;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
	public static void main(String[] arg) {
		AxiomUtil axiom = AxiomUtil.getInstance();
		RegistryResponseType res = axiom.fromXML("<rs:RegistryResponse xmlns:rs=\"urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0\" status=\"urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success\"/>", RegistryResponseType.class);
		System.out.println(res.getStatus());
	}
}
