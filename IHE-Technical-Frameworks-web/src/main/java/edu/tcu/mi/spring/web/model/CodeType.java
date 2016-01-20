package edu.tcu.mi.spring.web.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;

import lombok.Getter;

@XmlType(name = "Code")
@XmlAccessorType (XmlAccessType.FIELD)
public class CodeType {
	@Getter
	@XmlAttribute(name="code")
	private String code = "";
	
	@Getter
	@XmlAttribute(name="display")
	private String display = "";
	
	@Getter
	@XmlAttribute(name="codingScheme")
	private String codingScheme = "";
	
	@Getter
	@XmlAttribute(name="ext")
	private String ext = "";
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
