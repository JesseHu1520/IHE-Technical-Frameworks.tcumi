package edu.tcu.mi.ihe.iti.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class SubmissionSet extends BaseModel {
	@JsonIgnore @Getter @Setter 
	private Patient patient; 

	@Expose @Getter @Setter
	private List<Author> authors;

	@Expose @Getter @Setter
	private String contentTypeCode;
	
	public SubmissionSet(){
		authors = Lists.newArrayList();
	}
	
	public Author addAuthor(){
		if(authors == null)
			authors = Lists.newArrayList();
		Author author = new Author();
		author.setParentId(this.getId());
		authors.add(author);
		return author;
	}

}
