package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryPackageType;
import edu.tcu.mi.ihe.iti.model.GetFoldersForDocument;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetFoldersForDocumentTest extends RegsitryStoredQueryTest{

	@Test
	public void test(){
		this.submitNewFolderIncludeADoc();
		
		query = new GetFoldersForDocument();
		((GetFoldersForDocument)query)
			.andDocumentEntryEntryUuid(documentId)
			.andReturnLeafClass();
		
		System.out.println(query);
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
    	RegistryObjectListType registryObjectList = adhocQueryResponse.getRegistryObjectList();
    	List<RegistryPackageType> registryPackages = registryObjectList.getRegistryPackages();
    	RegistryPackageType folder = registryPackages.get(0);
    	return folderId.equals(folder.getId());
	}
}
