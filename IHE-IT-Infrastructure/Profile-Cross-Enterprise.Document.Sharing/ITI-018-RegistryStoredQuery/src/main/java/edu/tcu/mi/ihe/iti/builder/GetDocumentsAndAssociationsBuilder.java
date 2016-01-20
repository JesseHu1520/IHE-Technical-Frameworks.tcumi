package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetDocumentsAndAssociationsBuilder extends QueryBuilder {
	
	public GetDocumentsAndAssociationsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_DOCUMENTS_AND_ASSOCIATIONS_UUID);
	}

	public GetDocumentsAndAssociationsBuilder andEntryUuid(String ... val){
		this.parameters.put(StoredQueryConstants.DE_ENTRY_UUID, Lists.newArrayList(val));
		return this;
	}	
	public GetDocumentsAndAssociationsBuilder andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}	
	public GetDocumentsAndAssociationsBuilder andHomecommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validate() {
		return true;
	}
}
