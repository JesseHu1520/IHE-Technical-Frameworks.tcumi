package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetDocumentsBuilder extends QueryBuilder {
	
	public GetDocumentsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_DOCUMENTS_UUID);
	}

	public GetDocumentsBuilder andPatientId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PATIENT_ID, Lists.newArrayList(val));
		return this;
	}
				
	public GetDocumentsBuilder andUniqueId(String ... val){
		this.parameters.put(StoredQueryConstants.DE_UNIQUE_ID, Lists.newArrayList(val));
		return this;
	}

	public GetDocumentsBuilder andHomecommunityid(String val){
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
