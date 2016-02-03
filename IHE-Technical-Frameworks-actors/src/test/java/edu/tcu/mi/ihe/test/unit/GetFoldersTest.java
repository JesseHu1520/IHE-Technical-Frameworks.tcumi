package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryPackageType;
import edu.tcu.mi.ihe.iti.model.GetFolders;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class GetFoldersTest extends RegsitryStoredQueryTest {

	@Test
	public void test(){
		folderId = this.submitOneFolder();
		
		query = new GetFolders();
		((GetFolders)query)
			.andEntryUuid(folderId)
			.andReturnLeafClass();
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
    	RegistryObjectListType registryObjectList = adhocQueryResponse.getRegistryObjectList();
    	List<RegistryPackageType> registryPackages = registryObjectList.getRegistryPackages();
    	RegistryPackageType folder = registryPackages.get(0);
    	return folderId.equals(folder.getId());
	}
}
