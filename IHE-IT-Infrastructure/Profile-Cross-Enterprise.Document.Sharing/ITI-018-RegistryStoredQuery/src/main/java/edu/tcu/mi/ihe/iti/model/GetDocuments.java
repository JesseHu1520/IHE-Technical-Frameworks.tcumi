package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetDocuments extends QueryModel {
	
	public GetDocuments(){
		super(RegistryStoredQueryUUIDs.GET_DOCUMENTS_UUID);
	}

	public GetDocuments andPatientId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PATIENT_ID, Lists.newArrayList(val));
		return this;
	}
				
	public GetDocuments andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}

	public GetDocuments andHomecommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		return true;
	}

}
