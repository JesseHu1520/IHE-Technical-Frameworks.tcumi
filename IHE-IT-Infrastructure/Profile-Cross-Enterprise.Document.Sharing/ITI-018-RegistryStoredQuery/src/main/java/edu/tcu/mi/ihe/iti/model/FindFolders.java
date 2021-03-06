package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class FindFolders extends QueryModel {
	
	public FindFolders(){
		super(RegistryStoredQueryUUIDs.FIND_FOLDERS_UUID);
	}

	public FindFolders andPatientId(String val){
		this.parameter.put(StoredQueryConstants.FOL_PATIENT_ID, val);
		return this;
	}
	public FindFolders andLasUpdateTimeFrom(Long val){
		this.parameter.put(StoredQueryConstants.FOL_LAST_UPDATE_TIME_FROM, val);
		return this;
	}
	public FindFolders andLasUpdateTimeTo(Long val){
		this.parameter.put(StoredQueryConstants.FOL_LAST_UPDATE_TIME_TO, val);
		return this;
	}
	public FindFolders andCodeList(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_CODE_LIST, Lists.newArrayList(val));
		return this;
	}
	public FindFolders andCodeListScheme(String ... val){
		this.parameters.put(StoredQueryConstants.FOL_CODE_LIST_SCHEME, Lists.newArrayList(val));
		return this;
	}
	public FindFolders andStatusByApproved(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.APPROVED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindFolders andStatusByDeprecated(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.DEPRECATED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}
	public FindFolders andStatusBySubmitted(){
		String key = StoredQueryConstants.FOL_STATUS;
		String value = Namespace.SUBMITTED.getNamespace();
		andConstantsValue(key, value);
		return this;
	}

	@Override
	public boolean validate() {
		if(parameter.get(StoredQueryConstants.FOL_PATIENT_ID) == null) return false;
		if(parameters.get(StoredQueryConstants.FOL_STATUS) == null) return false;
		return true;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
