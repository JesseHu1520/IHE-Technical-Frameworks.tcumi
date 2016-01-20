package edu.tcu.mi.ihe.iti.core;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;


public abstract class SocketTransaction extends Transaction {

	@Deprecated
	@Override
	public String webservice(OMElement request, String endpoint, NonBlockCallBack callback) {
		// TODO Auto-generated method stub
		return null;
	}
}

