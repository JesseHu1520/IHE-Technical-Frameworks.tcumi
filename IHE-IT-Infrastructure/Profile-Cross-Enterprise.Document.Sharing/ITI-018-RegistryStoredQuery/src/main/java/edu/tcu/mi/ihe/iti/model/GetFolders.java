package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


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
		return true;
	}
}
