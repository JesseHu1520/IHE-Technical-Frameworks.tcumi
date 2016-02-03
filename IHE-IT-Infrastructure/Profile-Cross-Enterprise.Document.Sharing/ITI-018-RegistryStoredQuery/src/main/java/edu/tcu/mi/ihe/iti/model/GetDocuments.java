package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


/**
 * Retrieve a collection of XDSDocumentEntry objects. 
 * XDSDocumentEntry objects are selected either by their entryUUID or uniqueId attribute. 
 * @author TCUMI
 *
 */
public class GetDocuments extends QueryModel {
	
	public GetDocuments(){
		super(RegistryStoredQueryUUIDs.GET_DOCUMENTS_UUID);
	}

	public GetDocuments andEntryUuid(String ... val){
		this.parameters.put(StoredQueryConstants.DE_ENTRY_UUID, Lists.newArrayList(val));
		return this;
	}
				
	public GetDocuments andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}

	public GetDocuments andHomeCommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		if(	!this.parameters.containsKey(StoredQueryConstants.DE_ENTRY_UUID) && 
				!this.parameters.containsKey(StoredQueryConstants.DE_UNIQUE_ID))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

}
