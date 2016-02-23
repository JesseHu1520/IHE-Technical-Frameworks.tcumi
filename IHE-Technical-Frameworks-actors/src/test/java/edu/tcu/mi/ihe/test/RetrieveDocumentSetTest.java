package edu.tcu.mi.ihe.test;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

public abstract class RetrieveDocumentSetTest extends BaseTest {

	@Autowired
	protected XDSDocumentConsumer consumer;
	protected RetrieveModel model;
	
	protected abstract boolean validation(RetrieveDocumentSetResponseType retrieveDocumentSetResponse);
	
	@After
	public void trasaction(){
		OMElement response = consumer.retrieveDocumentSet(model, new NonBlockCallBack());
		AxiomUtil axiom = AxiomUtil.getInstance();
		RetrieveDocumentSetResponseType retrieveDocumentSetResponse = axiom.fromXML(response, RetrieveDocumentSetResponseType.class);
		
		RegistryResponseType registryResponse = retrieveDocumentSetResponse.getRegistryResponseType();
		String status = registryResponse.getStatus();

    	Assert.assertEquals(Namespace.SUCCESS.getNamespace(), status);
    	Assert.assertTrue(validation(retrieveDocumentSetResponse));
	}
}
