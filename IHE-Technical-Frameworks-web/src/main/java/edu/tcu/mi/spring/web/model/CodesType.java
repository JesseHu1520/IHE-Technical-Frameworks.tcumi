package edu.tcu.mi.spring.web.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.elasticsearch.common.collect.Lists;

import com.google.gson.Gson;

import lombok.Getter;

@XmlRootElement(name = "Codes")
@XmlAccessorType (XmlAccessType.FIELD)
public class CodesType {

	@Getter
	@XmlElement(name="CodeType")
	private List<CodeTypeType> codeTypes;
	
	public CodesType(){
		this.codeTypes = Lists.newArrayList();
	}
	
	public void addCodeType(CodeTypeType codeType){
		this.codeTypes.add(codeType);
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
