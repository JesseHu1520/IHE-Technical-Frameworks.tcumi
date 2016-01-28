package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetFolderAndContents extends QueryModel {
	
	public GetFolderAndContents(){
		super(RegistryStoredQueryUUIDs.GET_FOLDER_AND_CONTENTS_UUID);
	}

	public GetFolderAndContents andFolderEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.FOL_ENTRY_UUID, val);
		return this;
	}
	public GetFolderAndContents andFolderUniqueId(String val){
		this.parameter.put(StoredQueryConstants.FOL_UNIQUE_ID, val);
		return this;
	}
	public GetFolderAndContents andDocumentEntryFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetFolderAndContents andDocumentEntryCinfCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetFolderAndContents andHomecommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	@Override
	public boolean validate() {
		return true;
	}
}
