package edu.tcu.mi.ihe.iti.rfc3881;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@XmlTransient
public abstract class RFC3881Message extends MessageBuilder {
	@XmlTransient
	protected AxiomUtil axiom = null;
	public RFC3881Message(){
		axiom = AxiomUtil.getInstance();
	}
	public abstract OMElement buildRFC3881();
	
	@Override
	public OMElement getMessageFromXML() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
