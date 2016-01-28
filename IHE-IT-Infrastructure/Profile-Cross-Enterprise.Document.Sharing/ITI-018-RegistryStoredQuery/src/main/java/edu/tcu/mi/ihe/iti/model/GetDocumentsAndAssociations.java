package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetDocumentsAndAssociations extends QueryModel {
	
	public GetDocumentsAndAssociations(){
		super(RegistryStoredQueryUUIDs.GET_DOCUMENTS_AND_ASSOCIATIONS_UUID);
	}

	public GetDocumentsAndAssociations andEntryUuid(String ... val){
		this.parameters.put(StoredQueryConstants.DE_ENTRY_UUID, Lists.newArrayList(val));
		return this;
	}	
	public GetDocumentsAndAssociations andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}	
	public GetDocumentsAndAssociations andHomecommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		return true;
	}
}
