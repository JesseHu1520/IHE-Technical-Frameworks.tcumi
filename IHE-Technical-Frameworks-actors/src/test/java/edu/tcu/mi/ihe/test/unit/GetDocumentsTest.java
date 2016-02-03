package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.model.GetDocuments;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetDocumentsTest extends RegsitryStoredQueryTest {

	@Test
	public void test(){
		documentId = this.submitOneDocument();
		
		query = new GetDocuments();
		((GetDocuments)query)
			.andEntryUuid(documentId)
			.andReturnLeafClass();
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
    	RegistryObjectListType registryObjectList = adhocQueryResponse.getRegistryObjectList();
    	List<ExtrinsicObjectType> extrinsicObjects = registryObjectList.getExtrinsicObjects();
    	ExtrinsicObjectType document = extrinsicObjects.get(0);
    	return documentId.equals(document.getId());
	}
}
