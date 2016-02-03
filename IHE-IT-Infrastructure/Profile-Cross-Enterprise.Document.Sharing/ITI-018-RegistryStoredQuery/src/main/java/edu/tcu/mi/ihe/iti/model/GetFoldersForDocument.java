package edu.tcu.mi.ihe.iti.model;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetFoldersForDocument extends QueryModel {
	public GetFoldersForDocument(){
		super(RegistryStoredQueryUUIDs.GET_FOLDERS_FOR_DOCUMENT_UUID);
	}
	public GetFoldersForDocument andDocumentEntryEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.DE_ENTRY_UUID, val);
		return this;
	}
	public GetFoldersForDocument andDocumentEntryUniqueId(String val){
		this.parameter.put(StoredQueryConstants.DE_UNIQUE_ID, val);
		return this;
	}
	public GetFoldersForDocument andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}
	
	@Override
	public boolean validate() {
		if(	!this.parameter.containsKey(StoredQueryConstants.DE_ENTRY_UUID) && 
				!this.parameter.containsKey(StoredQueryConstants.DE_UNIQUE_ID))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
