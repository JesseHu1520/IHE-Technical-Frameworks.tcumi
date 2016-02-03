package edu.tcu.mi.ihe.iti.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class Folder extends BaseModel {
	@JsonIgnore @Getter @Setter
	private Patient patient; 
	@Expose @Getter @Setter
	private String title;
	@Expose @Getter @Setter
	private String description;
	@Expose @Getter @Setter
	private Set<String> folderCodeList;
	@Expose @Getter @Setter
	private List<DocumentEntry> documents;
	@Expose @Getter @Setter
	private boolean existing;
 
	
	public Folder(){
		documents = Lists.newArrayList();
		folderCodeList = Sets.newConcurrentHashSet();
	}
	
	public Folder(String id){
		this.id = id;
	}
	
	public DocumentEntry addDocument(){
		if(documents == null) documents = Lists.newArrayList();
		DocumentEntry document = new DocumentEntry();
		document.setPatient(this.getPatient());
		documents.add(document);
		return document;
	}

	public void addDocument(String docId) {
		DocumentEntry document = new DocumentEntry(docId);
		document.setExisting(true);

		if(documents == null) documents = Lists.newArrayList();
		documents.add(document);
	}
	
	public void addFolderCode(String code){
		if(folderCodeList == null) folderCodeList = Sets.newConcurrentHashSet();
		folderCodeList.add(code);
	}
}
