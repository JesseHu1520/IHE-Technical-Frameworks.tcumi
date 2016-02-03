package edu.tcu.mi.ihe.test;

import java.io.IOException;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;

import com.google.gson.GsonBuilder;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;


public abstract class ProvideAndRegisterDocumentSetTest extends BaseTest {
	

	protected Metadata metadata;
	protected GsonBuilder gsonBuilder = new GsonBuilder();
	
	@Before
	public void setup(){
//		CertificateDetails cert = CertificateDetails.getInstance();
//		cert.setCertificate("openxds_2010/OpenXDS_2010_Truststore.jks", "password",  "openxds_2010/OpenXDS_2010_Truststore.jks", "password");

		metadata = new Metadata();
		
		Patient patient = metadata.createPatient();
		patient.setId(patientId);
		patient.setName("王大尾");
		patient.setBirthday("19990801000000");
		patient.setGender("M");
		patient.setAddress("test@gms.tcu.edu.tw");
		
		SubmissionSet ss = metadata.createSubmissionSet();
		ss.setContentTypeCode("34746-8"); //SubmissionSet 分類

		Author author = ss.addAuthor();
		author.addAuthorRole("行政");
		author.addAuthorPerson("Gaduo");
		author.addAuthorInstitution("醫院");
		author.addAuthorSpecialty("行政");
	}

	@After
	public void transaction(){
        AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement response = source.provideAndRegisterDocumentSet(metadata, new NonBlockCallBack());
        RegistryResponseType registryResponse = axiom.fromXML(response, RegistryResponseType.class);
        String status = registryResponse.getStatus();

        GsonBuilder _gsonBuilder = gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		System.out.println(_gsonBuilder.create().toJson(metadata));
    	System.out.println(registryResponse);
    	Assert.assertEquals(Namespace.SUCCESS.getNamespace(), status);
	}
}
