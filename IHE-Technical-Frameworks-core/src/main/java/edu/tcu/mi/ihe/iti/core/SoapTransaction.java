package edu.tcu.mi.ihe.iti.core;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.context.MessageContext;

import edu.tcu.mi.ihe.utility.IdGenerator;
import lombok.Getter;
import lombok.Setter;


public abstract class SoapTransaction extends Transaction {
	protected IdGenerator common;
	@Getter @Setter
	protected MessageContext context;
	@Getter @Setter
	protected OMElement request;
	@Getter @Setter
	protected OMElement response;
	
	protected boolean assertEquals(OMElement response, String success){
		if(response.toString().equals(success))
			return true;
		return false;
	}
	

	@Getter 
	protected boolean simple = true;
	@Getter
	protected boolean swa = !true;
	@Getter
	protected boolean async = !true;

	public void setSimple(boolean simple) {
		this.simple = simple;
		if(simple){
			swa = false;
			async = false;
		}
	}
	
	public void setSWA(boolean swa){
		this.swa = swa;
		if(swa){
			simple = false;
			async = false;
		}
	}

	public void setAsync(boolean async) {
		this.async = async;
		if(async){
			simple = false;
			swa = false;
		}
	}
}

