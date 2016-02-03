package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.GetAssociations;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetAssociationsTest extends RegsitryStoredQueryTest {

	@Test
	public void test() {
		this.submitNewFolderIncludeADoc();
		query = new GetAssociations();
		((GetAssociations)query)
			.andUuid(documentId)
			.andReturnLeafClass();
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		// TODO Auto-generated method stub
		return true;
	}
}
