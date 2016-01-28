package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetSubmissionSetAndContents extends QueryModel {
	
	public GetSubmissionSetAndContents(){
		super(RegistryStoredQueryUUIDs.GET_SUBMISSIONSETS_AND_CONTENTS_UUID);
	}
	public GetSubmissionSetAndContents andSubmissionSetEntryId(String val){
		this.parameter.put(StoredQueryConstants.SS_ENTRY_ID, val);
		return this;
	}
	public GetSubmissionSetAndContents andSubmissionSetUniqueId(String val){
		this.parameter.put(StoredQueryConstants.SS_UNIQUE_ID, val);
		return this;
	}
	public GetSubmissionSetAndContents andDocumentEntryFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetSubmissionSetAndContents andDocumentEntryConfCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetSubmissionSetAndContents andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}
	@Override
	public boolean validate() {
		return true;
	}
}
