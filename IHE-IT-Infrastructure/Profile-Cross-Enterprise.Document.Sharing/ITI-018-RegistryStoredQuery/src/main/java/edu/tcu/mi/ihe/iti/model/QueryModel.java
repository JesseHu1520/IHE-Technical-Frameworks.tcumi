package edu.tcu.mi.ihe.iti.model;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import edu.tcu.mi.ihe.constants.StoredQueryConstants;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import lombok.Getter;
import lombok.Setter;

public class QueryModel implements QueryMessageValidator {
	public static Logger logger = Logger.getLogger(MessageBuilder.class);
	
	@Expose @Getter @Setter
	private String uuid;
	@Expose @Getter @Setter
	private String returnType;

	@Expose @Getter @Setter
	protected Map<String, String> parameter;
	@Expose @Getter @Setter
	protected Map<String, List<String>> parameters;

	public QueryModel(){
	}
	
	public QueryModel(String uuid){
		this.uuid = uuid;
		parameter = Maps.newHashMap();
		parameters = Maps.newHashMap();
	}

	public QueryModel andReturnObjectRef() {
		returnType = StoredQueryConstants.OBJECTREF;
		return this;
	}

	public QueryModel andReturnLeafClass() {
		returnType = StoredQueryConstants.LEAFCLASS;
		return this;
	}
	
	protected void andConstantsValue(String key, String value){
		if(this.parameters.containsKey(key)){
			List<String> val = this.parameters.get(key);
			TreeSet<String> set = Sets.newTreeSet(val);
			set.add(value);
			this.parameters.put(key, Lists.newArrayList(set));
		} else {
			this.parameters.put(key, Lists.newArrayList(value));
		}
	}


	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
	@Override
	public boolean validate() {
		return true;
	}
}
