package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetAssociations extends QueryModel {
	
	public GetAssociations(){
		super(RegistryStoredQueryUUIDs.GET_ASSOCIATIONS_UUID);
	}
	
	public GetAssociations andUuid(String ... val){
		this.parameters.put(StoredQueryConstants.UUID, Lists.newArrayList(val));
		return this;
	}
	
	public GetAssociations andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		return this.parameters.containsKey(StoredQueryConstants.UUID);
	}
	
	
}
