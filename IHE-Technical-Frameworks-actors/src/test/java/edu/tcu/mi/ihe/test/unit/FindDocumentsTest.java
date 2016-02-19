package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.model.FindDocuments;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class FindDocumentsTest extends RegsitryStoredQueryTest {


	@Test
	public void findDocument() {
		documentId = this.submitOneDocument();
		
		query = new FindDocuments();
		((FindDocuments)query) 
				.andPatientId(this.patientId)
				.andStatusByApproved()
				.andReturnObjectRef();
		
		System.out.println(query);
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		RegistryObjectListType objectList = adhocQueryResponse.getRegistryObjectList();
		List<ExtrinsicObjectType> extrinsicObjects = objectList.getExtrinsicObjects();
		boolean found = false;
		for(ExtrinsicObjectType extrinsicObject : extrinsicObjects){
			if(extrinsicObject.getId().equals(documentId)){
				found = true;
				break;
			}
		}
		return found;
	}

}
