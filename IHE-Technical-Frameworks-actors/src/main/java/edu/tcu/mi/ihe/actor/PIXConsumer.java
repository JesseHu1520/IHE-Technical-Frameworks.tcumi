package edu.tcu.mi.ihe.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.tcu.mi.hl7.v2.QBP.Q23;
import edu.tcu.mi.ihe.iti.service.PIXQueryService;

@Component
public class PIXConsumer extends Actor {
	
	@Autowired
	private PIXQueryService pixQueryService;

	public PIXConsumer(){
		if(pixQueryService == null) pixQueryService = new PIXQueryService();
	}
	
	public String pixQuery(Q23 builder){
		return pixQueryService.transaction(builder);
	}
	
}
