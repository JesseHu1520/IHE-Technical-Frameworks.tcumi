package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindFoldersBuilder extends QueryBuilder {
	
	public FindFoldersBuilder(){
		super(RegistryStoredQueryUUIDs.FIND_DOCUMENTS_UUID);
	}

	public FindFoldersBuilder andPatientId(String val){
		this.parameters.put(StoredQueryConstants.FOL_PATIENT_ID, Lists.newArrayList(val));
		return this;
	}
	public FindFoldersBuilder andLasUpdateTimeFrom(String val){
		this.parameters.put(StoredQueryConstants.FOL_LAST_UPDATE_TIME_FROM, Lists.newArrayList(val));
		return this;
	}
	public FindFoldersBuilder andLasUpdateTimeTo(String val){
		this.parameters.put(StoredQueryConstants.FOL_LAST_UPDATE_TIME_TO, Lists.newArrayList(val));
		return this;
	}
	public FindFoldersBuilder andCodeList(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_CODE_LIST, Lists.newArrayList(val));
		return this;
	}
	public FindFoldersBuilder andCodeListScheme(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_CODE_LIST_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindFoldersBuilder andStatusByApproved(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindFoldersBuilder andStatusByDeprecated(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andStatus(key, value);
		return this;
	}
	public FindFoldersBuilder andStatusBySubmitted(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andStatus(key, value);
		return this;
	}

	@Override
	protected boolean validate() {
		if(parameters.get(StoredQueryConstants.FOL_PATIENT_ID) == null) return false;
		if(parameters.get(StoredQueryConstants.FOL_STATUS) == null) return false;
		return true;
	}

	@Override
	public String getMessageFromHL7v2() {
		return null;
	}
	
}
