package edu.tcu.mi.ihe;

import java.io.IOException;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tcu.mi.ihe.actor.XDSDocumentSource;
import edu.tcu.mi.ihe.configuration.XDSConfiguration;
import edu.tcu.mi.ihe.iti.builder.MetadataBuilder;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XDSDocumentSourceTest {
	@Autowired
	private XDSDocumentSource source;
	private MetadataBuilder builder;
	
	@Before
	public void setup(){
//		CertificateDetails cert = CertificateDetails.getInstance();
//		cert.setCertificate("openxds_2010/OpenXDS_2010_Truststore.jks", "password", 
//				"openxds_2010/OpenXDS_2010_Truststore.jks", "password");
		
		builder = new MetadataBuilder();
		builder.setEndpoint("http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl");
		
		Patient patient = builder.getPatient();
		patient.setPatientId("bb74071cffbc41d^^^&1.3.6.1.4.1.21367.2005.13.20.2000&ISO");
		patient.setPatientName("王大尾");
		patient.setPatientBirthday("19990801000000");
		patient.setPatientGender("M");
		patient.setPatientAddress("tcumi@gms.tcu.edu.tw");
		
		SubmissionSet ss = builder.getSubmissionSet();
		ss.setContentTypeCode("34746-8"); //SubmissionSet 分類

		Author author = ss.addAuthor();
		author.addAuthorRole("行政");
		author.addAuthorPerson("Gaduo");
		author.addAuthorInstitution("醫院");
		author.addAuthorSpecialty("行政");
	}

	@Test
	public void test01_submitaDocument() {
		DocumentEntry document = builder.addDocument();
		Author author02 = document.addAuthor();
		author02.addAuthorRole("主治醫師");
		author02.addAuthorPerson("醫師");
		author02.addAuthorInstitution("醫院");
		author02.addAuthorSpecialty("心臟內科醫師");
		
		document.setClassCode("DEMO-Procedure");
		document.setFormatCode("urn:ihe:rad:TEXT");
		document.setHealthcareFacilityTypeCode("281PC2000N");
		document.setPracticeSettingCode("394802001");
		document.setTypeCode("34096-8");
		document.addConfidentialityCode("N");
		document.addEventCodeList("T-D4909");
		document.addEventCodeList("TRID1001");
		
		String filename = "0001k.xml";
		ClassPathResource resource = new ClassPathResource("test_data/" + filename);
		try {
			document.setTitle(filename);
			document.setContent(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void test02_submitEmptyFolder(){
		Folder folder = builder.addFolder();
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");
		System.out.println(folder.getId());
	}
	
//	@Test
	public void test03_submitNewFolderIncludeADoc(){
		Folder folder = builder.addFolder();
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");

		DocumentEntry document = folder.addDocument();
		Author author02 = document.addAuthor();
		author02.addAuthorRole("主治醫師");
		author02.addAuthorPerson("醫師");
		author02.addAuthorInstitution("醫院");
		author02.addAuthorSpecialty("心臟內科醫師");
		
		document.setClassCode("DEMO-Procedure");
		document.setFormatCode("urn:ihe:rad:TEXT");
		document.setHealthcareFacilityTypeCode("281PC2000N");
		document.setPracticeSettingCode("394802001");
		document.setTypeCode("34096-8");
		document.addConfidentialityCode("N");
		document.addEventCodeList("T-D4909");
		document.addEventCodeList("TRID1001");
		
		String filename = "0001k.xml";
		ClassPathResource resource = new ClassPathResource("test_data/" + filename);
		try {
			document.setTitle(filename);
			document.setContent(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void test04_APND() {  /** 新文件附加至已存在文件下*/
		{
			DocumentEntry document = builder.addDocument();
			Author author02 = document.addAuthor();
			author02.addAuthorRole("主治醫師");
			author02.addAuthorPerson("醫師");
			author02.addAuthorInstitution("醫院");
			author02.addAuthorSpecialty("心臟內科醫師");
			
			document.setClassCode("DEMO-Procedure");
			document.setFormatCode("urn:ihe:rad:TEXT");
			document.setHealthcareFacilityTypeCode("281PC2000N");
			document.setPracticeSettingCode("394802001");
			document.setTypeCode("34096-8");
			document.addConfidentialityCode("N");
			document.addEventCodeList("T-D4909");
			document.addEventCodeList("TRID1001");
			
			String filename = "0001k.xml";
			ClassPathResource resource = new ClassPathResource("test_data/" + filename);
			try {
				document.setTitle(filename);
				document.setContent(resource.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String docId = "urn:uuid:64828eed-b21a-457c-95ec-6e117ef1c9de";
			document.setAppendDocumentId(docId);
//			document.setReplaceDocumentId(docId);
//			document.setTransformDocumentId(docId);
//			document.setTransformAndReplaceDocumentId(docId);
//			document.setSignatureDocumentId(docId);
		}
	}
	
//	@Test
	public void documentEntryAddToExistingFolder(){
		DocumentEntry document = builder.addDocumentToFolder("urn:uuid:a5a68469-a85f-46e6-8fb1-7ee88d98fadf");
		{
			Author author02 = document.addAuthor();
			author02.addAuthorRole("主治醫師");
			author02.addAuthorPerson("醫師");
			author02.addAuthorInstitution("醫院");
			author02.addAuthorSpecialty("心臟內科醫師");
			
			document.setClassCode("DEMO-Procedure");
			document.setFormatCode("urn:ihe:rad:TEXT");
			document.setHealthcareFacilityTypeCode("281PC2000N");
			document.setPracticeSettingCode("394802001");
			document.setTypeCode("34096-8");
			document.addConfidentialityCode("N");
			document.addEventCodeList("T-D4909");
			document.addEventCodeList("TRID1001");
			
			String filename = "0001k.xml";
			ClassPathResource resource = new ClassPathResource("test_data/" + filename);
			try {
				document.setTitle(filename);
				document.setContent(resource.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//	@Test
	public void existingDocumentEntryAddToExistingFolder(){
		builder.moveDocumentToFolder("urn:uuid:64828eed-b21a-457c-95ec-6e117ef1c9de", "urn:uuid:a5a68469-a85f-46e6-8fb1-7ee88d98fadf");
	}
	
//	@Test
	public void existingDocumentEntryAddToFolder(){
		Folder folder = builder.moveDocumentToFolder("urn:uuid:64828eed-b21a-457c-95ec-6e117ef1c9de");
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");
	}
	
	
	@After
	public void trasaction(){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println(gson.toJson(builder));
		System.out.println(builder.getMessageFromXML());
		OMElement response = source.provideAndRegisterDocumentSet(builder, new NonBlockCallBack());
		System.out.println(response);
	}
	
}
