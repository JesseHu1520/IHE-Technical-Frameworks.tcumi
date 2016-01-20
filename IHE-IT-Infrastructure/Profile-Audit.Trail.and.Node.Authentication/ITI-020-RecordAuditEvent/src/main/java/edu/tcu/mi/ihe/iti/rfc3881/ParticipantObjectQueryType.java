package edu.tcu.mi.ihe.iti.rfc3881;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.RFC3881;


@XmlType(name="ParticipantObjectQueryType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ParticipantObjectQueryType extends RFC3881Message {
	@XmlValue
	protected String value;  // base64Binary
	
	
	public ParticipantObjectQueryType(){
		
	}
	
	public ParticipantObjectQueryType(String value){
		this.value = value;
	}
	
	@Override
	public OMElement buildRFC3881() {
    	OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.ParticipantObjectQuery.getTag(), namespace);
		if(value != null){
			root.setText(value);
		}
		return root;
	}
}
