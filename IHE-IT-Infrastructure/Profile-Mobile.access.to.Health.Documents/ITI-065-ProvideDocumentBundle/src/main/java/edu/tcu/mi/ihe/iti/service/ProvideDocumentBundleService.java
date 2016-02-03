package edu.tcu.mi.ihe.iti.service;

import org.apache.axiom.om.OMElement;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.core.RestTransaction;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;

@Component
@PropertySource(value="classpath:ihexds.properties")
public class ProvideDocumentBundleService extends RestTransaction {

	@Override
	public void auditLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String webservice(MessageBuilder builder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String webservice(OMElement request, String endpoint, NonBlockCallBack callback) {
		// TODO Auto-generated method stub
		return null;
	}

}
