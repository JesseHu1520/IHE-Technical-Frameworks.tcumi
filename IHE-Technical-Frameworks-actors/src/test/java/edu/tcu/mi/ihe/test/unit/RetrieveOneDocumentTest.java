package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.finder.ExternalIdentifierFinder;
import edu.tcu.mi.ihe.iti.ebxml.rim.finder.SlotFinder;
import edu.tcu.mi.ihe.iti.model.GetDocuments;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.test.RetrieveDocumentSetTest;
import edu.tcu.mi.ihe.utility.AxiomUtil;

public class RetrieveOneDocumentTest extends RetrieveDocumentSetTest {

	@Test
	public void test(){
		documentId = this.submitOneDocument();

		GetDocuments query = new GetDocuments();
		((GetDocuments)query) 
				.andEntryUuid(documentId)
				.andReturnLeafClass();

		OMElement response = consumer.registryStoredQuery(query, new NonBlockCallBack());
		AxiomUtil axiom = AxiomUtil.getInstance();
		AdhocQueryResponseType adhocQueryResponse = axiom.fromXML(response, AdhocQueryResponseType.class);
		RegistryObjectListType registryObjectList = adhocQueryResponse.getRegistryObjectList();
		List<ExtrinsicObjectType> extrinsicObjects = registryObjectList.getExtrinsicObjects();
		ExtrinsicObjectType extrinsicObject = extrinsicObjects.get(0);
		
		SlotFinder slotFinder = new SlotFinder();
		String repositoryUniqueId = slotFinder.findByDocumentEntryRepositoryUniqueId(extrinsicObject.getSlots());
		
		ExternalIdentifierFinder externalFinder = new ExternalIdentifierFinder();
		String documentUniqueId = externalFinder.findByDocumentEntryUniqueId(extrinsicObject.getExternalIdentifiers());
		
		model = new RetrieveModel();
		model.setRepositoryUniqueId(repositoryUniqueId);
//		builder.setHomeCommunityId(homeCommunityId);
		model.addDocumentId(documentUniqueId);
		System.out.println(model);
	}
	
	@Override
	protected boolean validation(RetrieveDocumentSetResponseType retrieveDocumentSetResponse) {
        List<DocumentResponseType> docs = retrieveDocumentSetResponse.getDocumentResponses();
        for(DocumentResponseType doc : docs){
        	System.out.println(doc);
        }
		return true;
	}

}
