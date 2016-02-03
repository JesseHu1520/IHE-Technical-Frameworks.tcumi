package edu.tcu.mi.ihe.test;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.QueryModel;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

public abstract class RegsitryStoredQueryTest extends BaseTest {

	@Autowired
	protected XDSDocumentConsumer consumer;
	protected QueryModel query;
	
	protected abstract boolean validation(AdhocQueryResponseType adhocQueryResponse);
	
	@After
	public void trasaction(){
		OMElement response = consumer.registryStoredQuery(query, new NonBlockCallBack());
		AxiomUtil axiom = AxiomUtil.getInstance();
		AdhocQueryResponseType adhocQueryResponse = axiom.fromXML(response, AdhocQueryResponseType.class);
    	String status = adhocQueryResponse.getStatus();

    	System.out.println(adhocQueryResponse); 
    	Assert.assertEquals(Namespace.SUCCESS.getNamespace(), status);
    	Assert.assertTrue(validation(adhocQueryResponse));
	}
}
