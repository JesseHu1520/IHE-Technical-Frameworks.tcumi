package edu.tcu.mi.ihe.iti.model;

import java.util.Set;

import com.google.common.collect.Sets;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class Author extends BaseModel {
	private String entryUuid;
	@Getter @Setter
	private String parentId;
	@Expose @Getter
	private Set<String> authorPersons;
	@Expose @Getter
	private Set<String> authorInstitutions;
	@Expose @Getter
	private Set<String> authorRoles;
	@Expose @Getter
	private Set<String> authorSpecialtys;
	
	public void addAuthorRole(String author) {
		if(authorRoles == null) authorRoles = Sets.newTreeSet();
		authorRoles.add(author);			
	}
	public void addAuthorInstitution(String author) {
		if(authorInstitutions == null) authorInstitutions = Sets.newTreeSet();
		authorInstitutions.add(author);			
	}
	public void addAuthorPerson(String author) {
		if(authorPersons == null) authorPersons = Sets.newTreeSet();
		authorPersons.add(author);			
	}
	public void addAuthorSpecialty(String author) {
		if(authorSpecialtys == null) authorSpecialtys = Sets.newTreeSet();
		authorSpecialtys.add(author);			
	}
}
