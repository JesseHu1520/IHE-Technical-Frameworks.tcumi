package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindSubmissionSetsBuilder extends QueryBuilder {
	
	public FindSubmissionSetsBuilder(){
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_UUID);
	}

	public FindSubmissionSetsBuilder andPatientId(String val){
		this.parameters.put(StoredQueryConstants.SS_PATIENT_ID, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andSourceId(String ... val){
		this.parameters.put(StoredQueryConstants.SS_SOURCE_ID, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andSubmissionTimeFrom(String val){
		this.parameters.put(StoredQueryConstants.SS_SUBMISSION_TIME_FROM, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andSubmissionTimeTo(String val){
		this.parameters.put(StoredQueryConstants.SS_SUBMISSION_TIME_TO, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andAuthorPerson(String val){
		this.parameters.put(StoredQueryConstants.SS_AUTHOR_PERSON, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andContentType(String ... val){
		this.parameters.put(StoredQueryConstants.SS_CONTENT_TYPE, Lists.newArrayList(val));
		return this;
	}
	public FindSubmissionSetsBuilder andStatusByApproved(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindSubmissionSetsBuilder andStatusByDeprecated(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindSubmissionSetsBuilder andStatusBySubmitted(){
		String key = StoredQueryConstants.SS_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}

	@Override
	protected boolean validate() {
		if(parameters.get(StoredQueryConstants.SS_PATIENT_ID) == null) return false;
		if(parameters.get(StoredQueryConstants.SS_STATUS) == null) return false;
		return true;
	}

	@Override
	public String getMessageFromHL7v2() {
		return null;
	}
	
}
