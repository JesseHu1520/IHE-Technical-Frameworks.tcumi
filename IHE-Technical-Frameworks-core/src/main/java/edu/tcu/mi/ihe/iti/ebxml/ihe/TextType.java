package edu.tcu.mi.ihe.iti.ebxml.ihe;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import lombok.Getter;
import lombok.Setter;

@XmlTransient
public class TextType {

	@Getter @Setter
	@XmlValue
	protected String value;

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}	
	
}
