package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.FindSubmissionSets;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class FindSubmissionSetsTest extends RegsitryStoredQueryTest {

	@Test
	public void findSubmissionSets() {
		query = new FindSubmissionSets();
		((FindSubmissionSets)query) 
			.andPatientId(this.patientId)
			.andStatusByApproved()
			.andReturnObjectRef();

	}


	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		// TODO Auto-generated method stub
		return true;
	}
}
