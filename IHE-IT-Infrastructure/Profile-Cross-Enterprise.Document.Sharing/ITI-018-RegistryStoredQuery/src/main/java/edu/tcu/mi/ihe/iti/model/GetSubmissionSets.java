package edu.tcu.mi.ihe.iti.model;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


/**
 * Retrieve the XDSSubmissionSet objects used to submit a collection of XDSDocumentEntry and XDSFolder objects. 
 * The XDSDocumentEntry and XDSFolder objects of interest are identified by their UUIDs in the $uuid parameter. 
 * @author TCUMI
 *
 */
public class GetSubmissionSets extends QueryModel {
	
	public GetSubmissionSets(){
		super(RegistryStoredQueryUUIDs.GET_SUBMISSIONSETS_UUID);
	}
	/**
	 * @param val who is the XDSDocumentEntry or the XDSFolder id. 
	 * @return
	 */
	public GetSubmissionSets andUuid(String ... val){
		this.parameters.put(StoredQueryConstants.UUID, Lists.newArrayList(val));
		return this;
	}
	
	public GetSubmissionSets andHomeCommunityid(String val){
		this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
		return this;
	}
	@Override
	public boolean validate() {
		return this.parameters.containsKey(StoredQueryConstants.UUID);
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
}
