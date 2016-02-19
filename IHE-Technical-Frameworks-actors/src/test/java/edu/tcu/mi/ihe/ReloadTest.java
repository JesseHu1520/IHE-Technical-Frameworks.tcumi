package edu.tcu.mi.ihe;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.actor.XDSDocumentSource;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.iti.model.QueryModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReloadTest {
	@Autowired
	private XDSDocumentSource source;

	@Test
	public void test() throws IOException {
//		ClassPathResource resource = new ClassPathResource("iti-41-test01.xml");
//		AxiomUtil axiom = AxiomUtil.getInstance();
//		OMElement metadata = axiom.fromResources(resource.getInputStream());
//		
//		String endpoint = "http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl";
//		NonBlockCallBack callback = new NonBlockCallBack();
//		OMElement response = source.provideAndRegisterDocumentSet(metadata, endpoint, callback );
//		System.out.println(response);
		
		String json = "{\"uuid\":\"urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d\",\"returnType\":\"ObjectRef\",\"parameter\":{\"$XDSDocumentEntryPatientId\":\"bb74071cffbc41d^^^\u00261.3.6.1.4.1.21367.2005.13.20.2000\u0026ISO\"},\"parameters\":{\"$XDSDocumentEntryStatus\":[\"urn:oasis:names:tc:ebxml-regrep:StatusType:Approved\"]}}";
		Gson gson = new Gson();
		QueryModel queryModel = gson.fromJson(json, QueryModel.class);
		System.out.println(queryModel);
		
	}
	
}
