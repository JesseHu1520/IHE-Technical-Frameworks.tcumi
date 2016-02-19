package edu.tcu.mi.ihe;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.iti.builder.RetrieveBuilder;
import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class XDSDocumentConsumerTest {

	@Autowired
	private XDSDocumentConsumer consumer;

	@Test
	public void retrieveDocumentSet() {
		RetrieveModel model = new RetrieveModel();
		model.setRepositoryUniqueId("1.3.6.1.4.1.21367.2010.1.2.1125.103");
//		builder.setHomeCommunityId(homeCommunityId);
		model.addDocumentId("203.64.84.247.20160117145203.1.1");

		RetrieveBuilder builder = new RetrieveBuilder(model);
		OMElement response = consumer.retrieveDocumentSet(builder, new NonBlockCallBack());
		System.out.println(response);
		
        AxiomUtil axiom = AxiomUtil.getInstance();
        RetrieveDocumentSetResponseType retrieveDocumentSetResponse = axiom.fromXML(response, RetrieveDocumentSetResponseType.class);
        
        List<DocumentResponseType> docs = retrieveDocumentSetResponse.getDocumentResponses();
        for(DocumentResponseType doc : docs){
        	System.out.println(doc);
        }
        
	}
	
}
