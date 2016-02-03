package edu.tcu.mi.ihe;

import java.io.IOException;

import org.apache.axiom.om.OMElement;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import edu.tcu.mi.ihe.actor.XDSDocumentSource;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReloadTest {
	@Autowired
	private XDSDocumentSource source;

//	@Test
	public void test() throws IOException {
		ClassPathResource resource = new ClassPathResource("iti-41-test01.xml");
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement metadata = axiom.fromResources(resource.getInputStream());
		
		String endpoint = "http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl";
		NonBlockCallBack callback = new NonBlockCallBack();
		OMElement response = source.provideAndRegisterDocumentSet(metadata, endpoint, callback );
		System.out.println(response);
	}
}
