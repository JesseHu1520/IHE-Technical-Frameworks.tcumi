package edu.tcu.mi.ihe.iti.ebxml.ihe;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.lcm.SubmitObjectsRequestType;
import lombok.Getter;

@XmlRootElement(name = "ProvideAndRegisterDocumentSetRequest", namespace="urn:ihe:iti:xds-b:2007")
@XmlAccessorType (XmlAccessType.FIELD)
public class ProvideAndRegisterDocumentSetRequestType {
	@Getter
	@XmlElement(name="SubmitObjectsRequest", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0")
	protected SubmitObjectsRequestType submitObjectsRequest;

	// [0 ... *]
	@Getter
	@XmlElement(name="Document", namespace="urn:ihe:iti:xds-b:2007")
	protected List<DocumentType> documents;
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
