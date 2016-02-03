package edu.tcu.mi.ihe.iti.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class Metadata extends BaseModel {

	@Expose @Getter @Setter
	private Patient patient; 
	@Expose @Getter @Setter
	private SubmissionSet submissionSet;
	@Expose @Getter @Setter
	private List<DocumentEntry> documents;
	@Expose @Getter @Setter
	private List<Folder> folders;


	public Metadata(){
		documents = Lists.newArrayList();
		folders = Lists.newArrayList();
	}

	@JsonIgnore
	public Patient createPatient(){
		if(patient == null) 
			patient = new Patient();
		return patient;
	}

	@JsonIgnore
	public SubmissionSet createSubmissionSet(){
		if(submissionSet == null)
			submissionSet = new SubmissionSet();
		submissionSet.setPatient(this.getPatient());
		return submissionSet;
	}
	
	public DocumentEntry addDocument(){
		DocumentEntry document = new DocumentEntry();
		document.setPatient(this.getPatient());
		if(documents == null)  documents = Lists.newArrayList();
		documents.add(document);
		return document;
	}
	
	public DocumentEntry getDocumentEntriesFirst(){
		return this.documents.get(0);
	}
	
	public Folder addFolder(){
		Folder folder = new Folder();
		folder.setPatient(this.getPatient());
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder;
	}

	public DocumentEntry addDocumentToFolder(String folderId) {
		Folder folder = new Folder(folderId);
		folder.setExisting(true);
		folder.setPatient(this.getPatient());
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder.addDocument();
	}

	public void moveDocumentToFolder(String docId, String folderId) {
		Folder folder = new Folder(folderId);
		folder.setExisting(true);
		folder.addDocument(docId);
		
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
	}

	public Folder moveDocumentToFolder(String docId) {
		Folder folder = new Folder();
		folder.setPatient(this.getPatient());
		folder.addDocument(docId);
		
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder;
	}
	
	@Override
	public String toString(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder = gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = gsonBuilder.create();
		return gson.toJson(this);
	}

}
