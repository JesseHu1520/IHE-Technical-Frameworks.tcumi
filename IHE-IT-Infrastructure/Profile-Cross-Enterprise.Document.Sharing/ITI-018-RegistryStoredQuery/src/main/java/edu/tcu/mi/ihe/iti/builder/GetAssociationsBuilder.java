package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetAssociationsBuilder extends QueryBuilder {
	
	public GetAssociationsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_ASSOCIATIONS_UUID);
	}
	
	public GetAssociationsBuilder andUuid(String ... val){
		this.parameters.put(StoredQueryConstants.UUID, Lists.newArrayList(val));
		return this;
	}
	
	public GetAssociationsBuilder andHomeCommunityId(String val){
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
		return this.parameters.containsKey(StoredQueryConstants.UUID);
	}
	
	
}
