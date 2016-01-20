package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetAllBuilder extends QueryBuilder {
	
	public GetAllBuilder(){
		super(RegistryStoredQueryUUIDs.GET_ALL_UUID);
	}

	public GetAllBuilder andPatientId(String val){
		this.parameter.put(StoredQueryConstants.PATIENT_ID, val);
		return this;
	}

	public GetAllBuilder andDocumentEntryStatusByApproved(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andDocumentEntryStatusByDeprecated(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andDocumentEntryStatusBySubmitted(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}

	public GetAllBuilder andSubmissionSetStatusByApproved(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andSubmissionSetStatusByDeprecated(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andSubmissionSetStatusBySubmitted(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}

	public GetAllBuilder andFolderStatusByApproved(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andFolderStatusByDeprecated(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public GetAllBuilder andFolderStatusBySubmitted(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}
	
	public GetAllBuilder andDocumentEntryFormatCode(String val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	
	public GetAllBuilder andDocumentEntryConfCode(String val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}

	public GetAllBuilder andHomeCommunityId(String val){
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
		if(!this.parameter.containsKey(StoredQueryConstants.PATIENT_ID)){
			return false;
		}
		if( !this.parameters.containsKey(StoredQueryConstants.DE_STATUS) || 
			!this.parameters.containsKey(StoredQueryConstants.SS_STATUS) || 
			!this.parameters.containsKey(StoredQueryConstants.FOL_STATUS)){
			return false;
		}
		return true;
	}
	
}

