package edu.tcu.mi.ihe.iti.model;

import java.util.Set;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class RetrieveModel {
	@Expose @Getter  @Setter
	private String repositoryUniqueId;
	@Expose @Getter  @Setter
	private String homeCommunityId;
	@Expose @Getter  @Setter
	private Set<String> documentIds;
	
	public void addDocumentId(String documentId){
		if(documentIds == null)  documentIds = Sets.newTreeSet();
		documentIds.add(documentId);
	}
	
	@Override
	public String toString(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder = gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = gsonBuilder.create();
		return gson.toJson(this);
	}
}
