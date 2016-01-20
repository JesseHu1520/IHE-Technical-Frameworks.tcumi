package edu.tcu.mi.ihe.iti.ebxml.rim;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name="ValueList", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
@XmlAccessorType (XmlAccessType.FIELD)
public class ValueListType {
	@Getter
	@XmlElement(name="Value", namespace="urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0")
	protected List<ValueType> values;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
