package edu.tcu.mi.ihe.iti.rfc3881;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.RFC3881;

@XmlType(name="ParticipantObjectDetailType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ParticipantObjectDetailType extends TypeValuePairType {
	
	public ParticipantObjectDetailType(){
		super();
	}
	public ParticipantObjectDetailType(String type, String value){
		super(type, value);
	}
	
	@Override
	public OMElement buildRFC3881() {
    	OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.ParticipantObjectDetail.getTag(), namespace);
		if(type != null){
			root.addAttribute("type", type, null);
		}
		if(value != null){
			root.addAttribute("value", value, null);
		}
		return root;
	}


}
