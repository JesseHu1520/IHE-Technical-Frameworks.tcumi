package edu.tcu.mi.ihe.iti.builder;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetFoldersForDocumentBuilder extends QueryBuilder {
	public GetFoldersForDocumentBuilder(){
		super(RegistryStoredQueryUUIDs.GET_FOLDERS_FOR_DOCUMENT_UUID);
	}
	public GetFoldersForDocumentBuilder andDocumentEntryEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.DE_ENTRY_UUID, val);
		return this;
	}
	public GetFoldersForDocumentBuilder andDocumentEntryUniqueId(String val){
		this.parameter.put(StoredQueryConstants.DE_UNIQUE_ID, val);
		return this;
	}
	public GetFoldersForDocumentBuilder andHomeCommunityId(String val){
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
