package edu.tcu.mi.ihe;

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
import edu.tcu.mi.ihe.iti.builder.FindDocumentsBuilder;
import edu.tcu.mi.ihe.iti.builder.FindFoldersBuilder;
import edu.tcu.mi.ihe.iti.builder.FindSubmissionSetsBuilder;
import edu.tcu.mi.ihe.iti.builder.GetAllBuilder;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class RegsitryStoredQueryTest {

	@Autowired
	private XDSDocumentConsumer consumer;
	
	private NonBlockCallBack callback = new NonBlockCallBack();
	
	@Test
	public void findDocument() {
		FindDocumentsBuilder builder = new FindDocumentsBuilder();
		OMElement request = 
				builder .andPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO")
						.andStatusByApproved()
						.andReturnObjectRef()
						.getMessageFromXML();

		OMElement response = consumer.registryStoredQuery(builder, callback);
		System.out.println(response);
	}
	

	public void findFolders() {
		FindFoldersBuilder builder = new FindFoldersBuilder();
		OMElement request = 
				builder .andPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO")
						.andStatusByApproved()
						.andReturnObjectRef()
						.getMessageFromXML();

		OMElement response = consumer.registryStoredQuery(builder, callback);
		System.out.println(response);
	}
	
	public void findSubmissionSets() {
		FindSubmissionSetsBuilder builder = new FindSubmissionSetsBuilder();
		OMElement request = 
				builder .andPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO")
						.andStatusByApproved()
						.andReturnObjectRef()
						.getMessageFromXML();

		OMElement response = consumer.registryStoredQuery(builder, callback);
		System.out.println(response);
	}
	
	public void getAll() {
		GetAllBuilder builder = new GetAllBuilder();
		OMElement request = 
				builder .andPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO")
						.andReturnObjectRef()
						.getMessageFromXML();

		OMElement response = consumer.registryStoredQuery(builder, callback);
		System.out.println(response);
	}	
}
