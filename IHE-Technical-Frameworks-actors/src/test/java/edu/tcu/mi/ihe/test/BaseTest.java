package edu.tcu.mi.ihe.test;

import java.io.IOException;

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
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XDSConfiguration.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseTest {
	protected String patientId = "bb74071cffbc41d^^^&1.3.6.1.4.1.21367.2005.13.20.2000&ISO";

	@Autowired
	protected XDSDocumentSource source;
	
	protected String documentId;
	protected String folderId;
	
	
	public String submitOneDocument(){
		Metadata metadata = new Metadata();
	
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
		
		DocumentEntry document = metadata.addDocument();
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
			document.setContentInputStream(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.provideAndRegisterDocumentSet(metadata, new NonBlockCallBack());
		return document.getId();
	}
	
	protected String submitOneFolder(){
		Metadata metadata = new Metadata();
		
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
		
		Folder folder = metadata.addFolder();
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");
		
		source.provideAndRegisterDocumentSet(metadata, new NonBlockCallBack());
		
		return folder.getId();
	}
	
	public void submitNewFolderIncludeADoc(){
		Metadata metadata = new Metadata();
		
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
		
		Folder folder = metadata.addFolder();
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
			document.setContentInputStream(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.provideAndRegisterDocumentSet(metadata, new NonBlockCallBack());
		
		documentId = document.getId();
		folderId = folder.getId();
	}
}
