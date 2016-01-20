
package edu.tcu.mi.ihe.iti.ebxml.rim;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;


@XmlType(name="RegistryObjectList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class RegistryObjectListType {

	public RegistryObjectListType(){
		extrinsicObjects = new ArrayList<ExtrinsicObjectType>();
	}
	
	@Getter
	@XmlElement(name="ExtrinsicObject", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<ExtrinsicObjectType> extrinsicObjects;

	@Getter
	@XmlElement(name="RegistryPackage", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<RegistryPackageType> registryPackages;
	

	// [0 ... *]
	@Getter
	@XmlElement(name="ObjectRef", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<ObjectRefType> objectRefs;

	@Getter
	@XmlElement(name="Association", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<AssociationType> associations;

	@Getter
	@XmlElement(name="Classification")
	protected List<ClassificationType> classifications;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

}
