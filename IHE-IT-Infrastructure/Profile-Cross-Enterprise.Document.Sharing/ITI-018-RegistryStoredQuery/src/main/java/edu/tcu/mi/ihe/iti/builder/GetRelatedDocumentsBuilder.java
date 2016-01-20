package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetRelatedDocumentsBuilder extends QueryBuilder {
	
	public GetRelatedDocumentsBuilder(){
		super(RegistryStoredQueryUUIDs.GET_RELATED_DOCUMENTS_UUID);
	}

	public GetRelatedDocumentsBuilder andDocumentEntryEntryUuid(String val){
		this.parameter.put(StoredQueryConstants.DE_ENTRY_UUID, val);
		return this;
	}
	public GetRelatedDocumentsBuilder andDocumentEntryUniqueId(String val){
		this.parameter.put(StoredQueryConstants.DE_UNIQUE_ID, val);
		return this;
	}
	public GetRelatedDocumentsBuilder andAssociationTypes(String ... val){
		this.parameters.put(StoredQueryConstants.ASSOCIATION_TYPES, Lists.newArrayList(val));
		return this;
	}
	public GetRelatedDocumentsBuilder andHomeCommunityId(String val){
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
		return this.parameters.containsKey(StoredQueryConstants.ASSOCIATION_TYPES);
	}
}
