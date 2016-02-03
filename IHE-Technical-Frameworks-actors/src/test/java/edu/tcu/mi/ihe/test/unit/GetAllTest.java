package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.GetAll;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetAllTest extends RegsitryStoredQueryTest {

	@Test
	public void test() {
		query = new GetAll();
		((GetAll)query) 
			.andPatientId(this.patientId)
			.andDocumentEntryStatusByApproved()
			.andReturnObjectRef();
	}	

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		// TODO Auto-generated method stub
		return true;
	}
}
