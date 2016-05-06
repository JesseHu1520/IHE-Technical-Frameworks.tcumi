package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindDocuments extends QueryModel {
	
	public FindDocuments(){
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_UUID);
	}

	public FindDocuments andPatientId(String val){
		this.parameter.put(StoredQueryConstants.DE_PATIENT_ID, val);
		return this;
	}
	public FindDocuments andClassCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CLASS_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andClassCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CLASS_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andPracticeSettingCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PRAC_SETTING_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andPracticeSettingScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PRAC_SETTING_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andCreationTimeFrom(Long val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_FROM, val);
		return this;
	}
	public FindDocuments andCreationTimeTo(Long val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_TO, val);
		return this;
	}
	public FindDocuments andServiceStartTimeFrom(Long val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_FROM, val);
		return this;
	}
	public FindDocuments andServiceStartTimeTo(Long val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_TO, val);
		return this;
	}
	public FindDocuments andServiceStopTimeFrom(Long val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_FROM, val);
		return this;
	}
	public FindDocuments andServiceStopTimeTo(Long val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_TO, val);
		return this;
	}
	public FindDocuments andHealthcareFacilityTypeCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_HC_FACILITY_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andHealthcareFacilityTypeCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_HC_FACILITY_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andEventCodeList(String ... val){
		this.parameters.put(StoredQueryConstants.DE_EVENT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andEventCodeListScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_EVENT_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andConfidentialityCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andConfidentialityCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andAuthorPerson(String ... val){
		this.parameters.put(StoredQueryConstants.DE_AUTHOR_PERSON, Lists.newArrayList(val));
		return this;
	}
	public FindDocuments andStatusByApproved(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindDocuments andStatusByDeprecated(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindDocuments andStatusBySubmitted(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	@Override
	public boolean validate() {
		if(!parameter.containsKey(StoredQueryConstants.DE_PATIENT_ID)){
			logger.error("Not found message of " + StoredQueryConstants.DE_PATIENT_ID);
			return false;
		}
		if(!parameters.containsKey(StoredQueryConstants.DE_STATUS)){
			logger.error("Not found message of " + StoredQueryConstants.DE_STATUS);
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
