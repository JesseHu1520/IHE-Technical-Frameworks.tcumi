package edu.tcu.mi.ihe.test.unit;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.GetSubmissionSetAndContents;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetSubmissionSetAndContentsTest extends RegsitryStoredQueryTest {
	
	@Test
	public void test() {
		Metadata metadata = submitOneDocument02();
		documentId = metadata.getId();
		String id = metadata.getId();
//		System.out.println(id);
		query = new GetSubmissionSetAndContents();
		((GetSubmissionSetAndContents)query)
			.andSubmissionSetEntryId(id)
//			.andSubmissionSetUniqueId("1.3.6.1.4.1.21367.2010.1.2.203.64.84.247.1.20160405221548.31")
			.andReturnLeafClass();
	}
	
	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
    	RegistryObjectListType registryObjectList = adhocQueryResponse.getRegistryObjectList();
    	List<ExtrinsicObjectType> extrinsicObjects = registryObjectList.getExtrinsicObjects();
    	ExtrinsicObjectType document = extrinsicObjects.get(0);
		System.out.println("compare : " + documentId + "," + document.getId());
    	return documentId.equals(document.getId());
	}

	private Metadata submitOneDocument02(){
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
		
		return metadata;
	}
}
