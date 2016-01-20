package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindDocumentsBuilder extends QueryBuilder {
	
	public FindDocumentsBuilder(){
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_UUID);
	}

	public FindDocumentsBuilder andPatientId(String val){
		this.parameter.put(StoredQueryConstants.DE_PATIENT_ID, val);
		return this;
	}
	public FindDocumentsBuilder andClassCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CLASS_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andClassCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CLASS_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andSettingCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PRAC_SETTING_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andSettingScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_PRAC_SETTING_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andCreationTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_FROM, val);
		return this;
	}
	public FindDocumentsBuilder andCreationTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_CREATION_TIME_TO, val);
		return this;
	}
	public FindDocumentsBuilder andServiceStartTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_FROM, val);
		return this;
	}
	public FindDocumentsBuilder andServiceStartTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_START_TIME_TO, val);
		return this;
	}
	public FindDocumentsBuilder andServiceStopTimeFrom(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_FROM, val);
		return this;
	}
	public FindDocumentsBuilder andServiceStopTimeTo(String val){
		this.parameter.put(StoredQueryConstants.DE_SERVICE_STOP_TIME_TO, val);
		return this;
	}
	public FindDocumentsBuilder andHealthcareFacilityTypeCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_HC_FACILITY_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andHealthcareFacilityTypeCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_HC_FACILITY_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andEventCodeList(String ... val){
		this.parameters.put(StoredQueryConstants.DE_EVENT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andEventCodeListScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_EVENT_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andConfidentialityCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andConfidentialityCodeScheme(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public FindDocumentsBuilder andStatusByApproved(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindDocumentsBuilder andStatusByDeprecated(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindDocumentsBuilder andStatusBySubmitted(){
		String key = StoredQueryConstants.DE_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}

	@Override
	protected boolean validate() {
		if(!parameter.containsKey(StoredQueryConstants.DE_PATIENT_ID)) return false;
		if(!parameters.containsKey(StoredQueryConstants.DE_STATUS)) return false;
		return true;
	}

	@Override
	public String getMessageFromHL7v2() {
		return null;
	}
	
}
