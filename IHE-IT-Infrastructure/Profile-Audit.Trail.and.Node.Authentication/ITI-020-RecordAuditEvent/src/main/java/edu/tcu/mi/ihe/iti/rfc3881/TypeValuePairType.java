package edu.tcu.mi.ihe.iti.rfc3881;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.binary.Base64;

@XmlType(name="TypeValuePairType")
@XmlAccessorType (XmlAccessType.FIELD)
public class TypeValuePairType extends RFC3881Message{
	@XmlAttribute(name = "type")
	protected String type;
	@XmlAttribute(name = "value")
	protected String value; // base64binary
	
	
	public TypeValuePairType(){
		
	}
	
	/**
	 * @param type
	 * @param value 原本的值, 經過此類別會自動 encode 為 base64
	 */
	public TypeValuePairType(String type, String value){
		this.type = type;
		byte[] b = Base64.encodeBase64(value.getBytes());
		this.value = new String(b);
	}
	
	@Override
	public OMElement buildRFC3881() {
		return null;
	}

}
