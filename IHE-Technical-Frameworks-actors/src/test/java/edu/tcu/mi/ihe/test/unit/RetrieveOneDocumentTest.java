package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.test.RetrieveDocumentSetTest;

public class RetrieveOneDocumentTest extends RetrieveDocumentSetTest {

	@Test
	public void test(){
		model = new RetrieveModel();
		model.setRepositoryUniqueId("1.3.6.1.4.1.21367.2010.1.2.1125.103");
//		builder.setHomeCommunityId(homeCommunityId);
		model.addDocumentId("1.3.6.1.4.1.21367.2010.1.2.127.0.1.1.22.20160219234327.42");
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
