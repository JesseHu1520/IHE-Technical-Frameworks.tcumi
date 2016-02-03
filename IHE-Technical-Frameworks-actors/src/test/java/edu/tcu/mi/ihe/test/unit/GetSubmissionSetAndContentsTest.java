package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.GetSubmissionSetAndContents;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetSubmissionSetAndContentsTest extends RegsitryStoredQueryTest {
	
	@Test
	public void test() {
		Metadata metadata = submitOneFolder02();
		
		query = new GetSubmissionSetAndContents();
		((GetSubmissionSetAndContents)query)
			.andSubmissionSetEntryId(metadata.getId())
			.andReturnLeafClass();
	}
	
	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		// TODO Auto-generated method stub
		return false;
	}

	private Metadata submitOneFolder02(){
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
		
		return metadata;
	}
}
