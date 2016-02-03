package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


/**
 * Retrieve a collection of XDSFolder objects. 
 * XDSFolder objects are selected either by their entryUUID or uniqueId attribute. 
 * @author TCUMI
 *
 */
public class GetFolders extends QueryModel {
	public GetFolders(){
		super(RegistryStoredQueryUUIDs.GET_FOLDERS_UUID);
	}
	
	public GetFolders andEntryUuid(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_ENTRY_UUID, Lists.newArrayList(val));
		return this;
	}
	public GetFolders andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}
	public GetFolders andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		if(	!this.parameters.containsKey(StoredQueryConstants.FOL_ENTRY_UUID) && 
				!this.parameters.containsKey(StoredQueryConstants.FOL_UNIQUE_ID))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
