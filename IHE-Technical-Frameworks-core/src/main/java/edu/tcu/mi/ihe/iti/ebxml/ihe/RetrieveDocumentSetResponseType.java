package edu.tcu.mi.ihe.iti.ebxml.ihe;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "RetrieveDocumentSetResponse", namespace="urn:ihe:iti:xds-b:2007")
@XmlAccessorType (XmlAccessType.FIELD)
public class RetrieveDocumentSetResponseType {

	@Getter @Setter
	@XmlElement(name="RegistryResponse", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0")
	protected RegistryResponseType registryResponseType;
	
	@Getter @Setter
	@XmlElement(name="DocumentResponse", namespace="urn:ihe:iti:xds-b:2007")
	protected List<DocumentResponseType> documentResponses ;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
