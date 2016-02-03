package edu.tcu.mi.ihe.test.unit;

import java.util.List;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryPackageType;
import edu.tcu.mi.ihe.iti.model.FindFolders;
import edu.tcu.mi.ihe.test.RegsitryStoredQueryTest;

public class FindFoldersTest extends RegsitryStoredQueryTest {

	@Test
	public void findFolders() {
		folderId = this.submitOneFolder();
		
		query = new FindFolders();
		((FindFolders)query) 
			.andPatientId(this.patientId)
			.andStatusByApproved()
			.andReturnLeafClass();
	}

	@Override
	protected boolean validation(AdhocQueryResponseType adhocQueryResponse) {
		RegistryObjectListType objectList = adhocQueryResponse.getRegistryObjectList();
		List<RegistryPackageType> registryPackages = objectList.getRegistryPackages();
		boolean found = false;
		for(RegistryPackageType registryPackage : registryPackages){
			if(registryPackage.getId().equals(folderId)){
				found = true;
				break;
			}
		}
		return found;
	}

}
