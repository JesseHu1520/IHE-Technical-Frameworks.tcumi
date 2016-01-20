package edu.tcu.mi.ihe.fhir.repository;

import java.util.List;

import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICreate;
import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.gclient.IQuery;
import ca.uhn.fhir.rest.gclient.IRead;
import ca.uhn.fhir.rest.gclient.IReadExecutable;
import ca.uhn.fhir.rest.gclient.IReadTyped;
import ca.uhn.fhir.rest.gclient.IUpdate;
import ca.uhn.fhir.rest.gclient.IUpdateTyped;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.exceptions.ResourceGoneException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class GenericFHIRRepository<T> {
	protected IGenericClient client;
	protected IQuery<Bundle> query;
    protected final Class clazz;

	public GenericFHIRRepository(Class<T> _class) {
		this("http://211.20.120.59:8000/fhir-jpaserver/base/", _class);
	}
	public GenericFHIRRepository(String endpoint, Class<T> _class) {
		clazz = _class;
		FhirContext ctx = FhirContext.forDstu2();
		client = ctx.newRestfulGenericClient(endpoint);
	}

	public boolean create(IBaseResource resource){
		ICreate create = client.create();
		ICreateTyped createTyped = create.resource(resource);
		createTyped.prettyPrint();
		createTyped.encodedJson();
		
		try {
			MethodOutcome methodOutcome = createTyped.execute();
			boolean created = methodOutcome.getCreated();
			return created;
		} catch (InvalidRequestException e){
			e.printStackTrace();
			return false;
		} catch (UnprocessableEntityException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(IBaseResource resource){
		IUpdate update = client.update();
		IUpdateTyped updateTyped = update.resource(resource);
		updateTyped.encodedJson();
		updateTyped.prettyPrint();
		MethodOutcome methodOutcome = updateTyped.execute();
		IBaseResource _resource = methodOutcome.getResource();
		return _resource == null ? false : true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T read(String id){
		IRead read = client.read();
		IReadTyped<?> readTyped = read.resource(clazz);
		IReadExecutable<?> readExecutable = readTyped.withId(id);
		readExecutable.prettyPrint();
		readExecutable.encodedJson();
		
		try {
			T resource = (T) readExecutable.execute();
			return resource;
		} catch (ResourceGoneException e){
			e.printStackTrace();
			return null;
		} catch (ResourceNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> executeQuery(){
		Bundle bundle = query.execute();
		List<T> resources = bundle.getResources(clazz);
		return resources;
	}
}
