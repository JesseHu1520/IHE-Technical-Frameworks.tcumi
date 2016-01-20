package edu.tcu.mi.ihe.iti.ebxml.ihe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name = "DocumentResponse", namespace="urn:ihe:iti:xds-b:2007")
@XmlAccessorType (XmlAccessType.FIELD)
public class DocumentResponseType {
	@Getter 
	@XmlElement(name="RepositoryUniqueId", namespace="urn:ihe:iti:xds-b:2007")
	protected RepositoryUniqueIdType repositoryUniqueId;

	@Getter 
	@XmlElement(name="DocumentUniqueId", namespace="urn:ihe:iti:xds-b:2007")
	protected DocumentUniqueIdType documentUniqueId;

	@Getter 
	@XmlElement(name="mimeType", namespace="urn:ihe:iti:xds-b:2007")
	protected MimeTypeType mimeType;

	@Getter 
	@XmlElement(name="Document", namespace="urn:ihe:iti:xds-b:2007")
	protected DocumentType document;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
