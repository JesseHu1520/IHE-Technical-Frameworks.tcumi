package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindDocumentsByReferenceId extends QueryModel{

	public FindDocumentsByReferenceId() {
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_BY_REFERENCE_ID);
	}

	public FindDocumentsByReferenceId andPatientId(String val){
		this.parameter.put(StoredQueryConstants.DE_PATIENT_ID, val);
		return this;
	}
	
	public FindDocumentsByReferenceId andReferenceIdList(String ... val){
		this.parameters.put(StoredQueryConstants.DE_REFERENCE_ID_LIST, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andCreationTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_FROM, val);
		return this;
	}
	public FindDocumentsByReferenceId andCreationTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_TO, val);
		return this;
	}
	public FindDocumentsByReferenceId andServiceStartTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_FROM, val);
		return this;
	}
	public FindDocumentsByReferenceId andServiceStartTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_TO, val);
		return this;
	}
	public FindDocumentsByReferenceId andServiceStopTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_FROM, val);
		return this;
	}
	public FindDocumentsByReferenceId andServiceStopTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_TO, val);
		return this;
	}
	public FindDocumentsByReferenceId andClassCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CLASS_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andTypeCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_TYPE_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andPracticeSettingCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PRAC_SETTING_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andHealthcareFacilityTypeCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_HC_FACILITY_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andEventCodeList(String ... val){
		this.parameters.put(StoredQueryConstants.DE_EVENT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andConfidentialityCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsByReferenceId andStatusByApproved(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindDocumentsByReferenceId andStatusByDeprecated(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindDocumentsByReferenceId andStatusBySubmitted(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	@Override
	public boolean validate() {
		if(!parameter.containsKey(StoredQueryConstants.DE_PATIENT_ID)) return false;
		if(!parameters.containsKey(StoredQueryConstants.DE_REFERENCE_ID_LIST)) return false;
		if(!parameters.containsKey(StoredQueryConstants.DE_STATUS)) return false;
		return true;
	}

}
