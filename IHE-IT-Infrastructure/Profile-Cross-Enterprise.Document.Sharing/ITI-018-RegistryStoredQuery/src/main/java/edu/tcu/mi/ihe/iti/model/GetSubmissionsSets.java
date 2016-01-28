package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetSubmissionsSets extends QueryModel {
	
	public GetSubmissionsSets(){
		super(RegistryStoredQueryUUIDs.GET_SUBMISSIONSETS_UUID);
	}
	public GetSubmissionsSets andUuid(String ... val){
		this.parameters.put(StoredQueryConstants.UUID, Lists.newArrayList(val));
		return this;
	}
	public GetSubmissionsSets andHomecommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}
	@Override
	public boolean validate() {
		return this.parameters.containsKey(StoredQueryConstants.UUID);
	}
}
