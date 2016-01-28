package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindSubmissionSets extends QueryModel {
	
	public FindSubmissionSets(){
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_UUID);
	}

	public FindSubmissionSets andPatientId(String val){
		this.parameters.put(StoredQueryConstants.SS_PATIENT_ID, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andSourceId(String ... val){
		this.parameters.put(StoredQueryConstants.SS_SOURCE_ID, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andSubmissionTimeFrom(String val){
		this.parameters.put(StoredQueryConstants.SS_SUBMISSION_TIME_FROM, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andSubmissionTimeTo(String val){
		this.parameters.put(StoredQueryConstants.SS_SUBMISSION_TIME_TO, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andAuthorPerson(String val){
		this.parameters.put(StoredQueryConstants.SS_AUTHOR_PERSON, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andContentType(String ... val){
		this.parameters.put(StoredQueryConstants.SS_CONTENT_TYPE, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSets andStatusByApproved(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindSubmissionSets andStatusByDeprecated(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindSubmissionSets andStatusBySubmitted(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	@Override
	public boolean validate() {
		if(parameters.get(StoredQueryConstants.SS_PATIENT_ID) == null) return false;
		if(parameters.get(StoredQueryConstants.SS_STATUS) == null) return false;
		return true;
	}

	
}
