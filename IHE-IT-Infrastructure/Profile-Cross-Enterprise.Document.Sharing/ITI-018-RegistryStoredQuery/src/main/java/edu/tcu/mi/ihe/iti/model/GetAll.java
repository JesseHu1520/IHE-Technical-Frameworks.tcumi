package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

/**
 * Get all registry content for a patient given the indicated status, format codes, and confidentiality codes. 
 * @author TCUMI
 *
 */
public class GetAll extends QueryModel {
	
	public GetAll(){
		super(RegistryStoredQueryUUIDs.GET_ALL_UUID);
	}

	public GetAll andPatientId(String val){
		this.parameter.put(StoredQueryConstants.PATIENT_ID, val);
		return this;
	}

	public GetAll andDocumentEntryStatusByApproved(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andDocumentEntryStatusByDeprecated(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andDocumentEntryStatusBySubmitted(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	public GetAll andSubmissionSetStatusByApproved(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andSubmissionSetStatusByDeprecated(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andSubmissionSetStatusBySubmitted(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	public GetAll andFolderStatusByApproved(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andFolderStatusByDeprecated(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public GetAll andFolderStatusBySubmitted(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	
	public GetAll andDocumentEntryFormatCode(String ...  val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	
	public GetAll andDocumentEntryConfidentialityCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	
	public GetAll andDocumentEntryTypeCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_TYPE_CODE, Lists.newArrayList(val));
		return this;
	}

	public GetAll andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		if(!this.parameter.containsKey(StoredQueryConstants.PATIENT_ID)){
			return false;
		}
		if( !this.parameters.containsKey(StoredQueryConstants.DE_STATUS) && 
			!this.parameters.containsKey(StoredQueryConstants.SS_STATUS) && 
			!this.parameters.containsKey(StoredQueryConstants.FOL_STATUS)){
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}

