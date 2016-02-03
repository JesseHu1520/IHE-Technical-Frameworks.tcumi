package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.GetSubmissionSets;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetSubmissionSetsTest extends RegsitryStoredQueryTest {

	@Test
	public void documentId(){
		String documentId = this.submitOneDocument();
		
		query = new GetSubmissionSets();
		((GetSubmissionSets)query)
			.andUuid(documentId)
			.andReturnLeafClass();
	}
	
	@Test
	public void folderId(){
		String folderId = this.submitOneFolder();
		
		query = new GetSubmissionSets();
		((GetSubmissionSets)query)
			.andUuid(folderId)
			.andReturnLeafClass();
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
