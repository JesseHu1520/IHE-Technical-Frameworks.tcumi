package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.DocumentRelationshipsConstants;
import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetRelatedDocuments extends QueryModel {
	
	public GetRelatedDocuments(){
		super(RegistryStoredQueryUUIDs.GET_RELATED_DOCUMENTS_UUID);
	}

	public GetRelatedDocuments andDocumentEntryEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.DE_ENTRY_UUID, val);
		return this;
	}
	
	public GetRelatedDocuments andDocumentEntryUniqueId(String val){
		this.parameter.put(StoredQueryConstants.DE_UNIQUE_ID, val);
		return this;
	}
	
	public GetRelatedDocuments andDocumentEntryType(String ... val){
		this.parameters.put(StoredQueryConstants.DE_TYPE, Lists.newArrayList(val));
		return this;
	}
	
	public GetRelatedDocuments andHomeCommunityId(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}
	
	public GetRelatedDocuments andAssociationTypesBySigns(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.Signs;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetRelatedDocuments andAssociationTypesByReplace(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.RPLC;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetRelatedDocuments andAssociationTypesByAppend(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.APND;
		this.andConstantsValue(key, value);
		return this;
	}
	
	public GetRelatedDocuments andAssociationTypesByTransform(){
		String key = StoredQueryConstants.ASSOCIATION_TYPES;
		String value = DocumentRelationshipsConstants.XFRM;
		this.andConstantsValue(key, value);
		return this;
	}

	@Override
	public boolean validate() {
		if(	!this.parameter.containsKey(StoredQueryConstants.DE_ENTRY_UUID) && 
				!this.parameter.containsKey(StoredQueryConstants.DE_UNIQUE_ID))
			return false;
		return this.parameters.containsKey(StoredQueryConstants.ASSOCIATION_TYPES);
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
