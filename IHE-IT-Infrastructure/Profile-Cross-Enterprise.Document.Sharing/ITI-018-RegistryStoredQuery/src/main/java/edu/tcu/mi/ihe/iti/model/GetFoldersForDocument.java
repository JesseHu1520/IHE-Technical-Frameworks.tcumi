package edu.tcu.mi.ihe.iti.model;

import edu.tcu.mi.ihe.constants.DocumentRelationshipsConstants;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetFoldersForDocument extends QueryModel {
	public GetFoldersForDocument(){
		super(RegistryStoredQueryUUIDs.GET_FOLDERS_FOR_DOCUMENT_UUID);
	}
	public GetFoldersForDocument andDocumentEntryEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.DE_ENTRY_UUID, val);
		return this;
	}
	public GetFoldersForDocument andDocumentEntryUniqueId(String val){
		this.parameter.put(StoredQueryConstants.DE_UNIQUE_ID, val);
		return this;
	}
	public GetFoldersForDocument andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}

	
	public GetFoldersForDocument andAssociationTypesByHasMember(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.HAS_MEMBER;
		this.andConstantsValue(key, value);
		return this;
	}
	public GetFoldersForDocument andAssociationTypesBySigns(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.Signs;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetFoldersForDocument andAssociationTypesByReplace(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.RPLC;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetFoldersForDocument andAssociationTypesByAppend(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.APND;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetFoldersForDocument andAssociationTypesByTransform(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.XFRM;
		this.andConstantsValue(key, value);
		return this;
	}
	
	@Override
	public boolean validate() {
		return true;
	}
}
