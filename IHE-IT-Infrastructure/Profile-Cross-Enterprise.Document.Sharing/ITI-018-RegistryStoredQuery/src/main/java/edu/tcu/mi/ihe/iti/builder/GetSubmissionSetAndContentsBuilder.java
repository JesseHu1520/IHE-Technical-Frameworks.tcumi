package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetSubmissionSetAndContentsBuilder extends QueryBuilder {
	
	public GetSubmissionSetAndContentsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_SUBMISSIONSETS_AND_CONTENTS_UUID);
	}
	public GetSubmissionSetAndContentsBuilder andSubmissionSetEntryId(String val){
		this.parameter.put(StoredQueryConstants.SS_ENTRY_ID, val);
		return this;
	}
	public GetSubmissionSetAndContentsBuilder andSubmissionSetUniqueId(String val){
		this.parameter.put(StoredQueryConstants.SS_UNIQUE_ID, val);
		return this;
	}
	public GetSubmissionSetAndContentsBuilder andDocumentEntryFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetSubmissionSetAndContentsBuilder andDocumentEntryConfCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetSubmissionSetAndContentsBuilder andHomeCommunityId(String val){
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
