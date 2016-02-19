package edu.tcu.mi.ihe.iti.model;

import java.util.Set;

import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.Setter;

public class RetrieveModel {
	@Getter  @Setter
	private String repositoryUniqueId;
	@Getter  @Setter
	private String homeCommunityId;
	@Getter  @Setter
	private Set<String> documentIds;
	
	public void addDocumentId(String documentId){
		if(documentIds == null)  documentIds = Sets.newTreeSet();
		documentIds.add(documentId);
	}
}
