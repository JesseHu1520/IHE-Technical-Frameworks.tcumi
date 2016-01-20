package edu.tcu.mi.spring.web.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.elasticsearch.common.collect.Lists;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@XmlType(name = "CodeType")
@XmlAccessorType (XmlAccessType.FIELD)
public class CodeTypeType {
	@Getter @Setter
	@XmlAttribute(name="name")
	private String name = "";
	
	@Getter @Setter
	@XmlAttribute(name="classScheme")
	private String classScheme = "";
	
	@Getter
	@XmlElement(name="Code")
	private List<CodeType> codes;

	public CodeTypeType(){
		this.name = "";
		this.classScheme = "";
		this.codes = Lists.newArrayList();
	}
	
	public void addCode(CodeType code){
		this.codes.add(code);
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
