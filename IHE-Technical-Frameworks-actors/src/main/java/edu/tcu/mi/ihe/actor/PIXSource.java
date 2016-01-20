package edu.tcu.mi.ihe.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.tcu.mi.hl7v2.ADT.A01;
import edu.tcu.mi.hl7v2.ADT.A04;
import edu.tcu.mi.hl7v2.ADT.A05;
import edu.tcu.mi.hl7v2.ADT.A08;
import edu.tcu.mi.hl7v2.ADT.A40;
import edu.tcu.mi.ihe.iti.service.PatientIdentityFeedService;

@Component
public class PIXSource extends Actor {
	
	@Autowired
	private PatientIdentityFeedService patientIdentityFeedService;

	public PIXSource(){
		if(patientIdentityFeedService == null) patientIdentityFeedService = new PatientIdentityFeedService();
	}
	
	public String patientIdentityFeed(A01 builder){
		return patientIdentityFeedService.transaction(builder);
	}
	
	public String patientIdentityFeed(A04 builder){
		return patientIdentityFeedService.transaction(builder);
	}
	
	public String patientIdentityFeed(A05 builder){
		return patientIdentityFeedService.transaction(builder);
	}
	
	public String patientIdentityFeed(A08 builder){
		return patientIdentityFeedService.transaction(builder);
	}
	
	public String patientIdentityFeed(A40 builder){
		return patientIdentityFeedService.transaction(builder);
	}
	
}
