package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;

public class GetFolderAndContentsBuilder extends QueryBuilder {
	
	public GetFolderAndContentsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_FOLDER_AND_CONTENTS_UUID);
	}

	public GetFolderAndContentsBuilder andFolderEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.FOL_ENTRY_UUID, val);
		return this;
	}
	public GetFolderAndContentsBuilder andFolderUniqueId(String val){
		this.parameter.put(StoredQueryConstants.FOL_UNIQUE_ID, val);
		return this;
	}
	public GetFolderAndContentsBuilder andDocumentEntryFormatCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_FORMAT_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetFolderAndContentsBuilder andDocumentEntryCinfCode(String ... val){
		this.parameters.put(StoredQueryConstants.DE_CONF_CODE, Lists.newArrayList(val));
		return this;
	}
	public GetFolderAndContentsBuilder andHomecommunityid(String val){
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
		return true;
	}
}
