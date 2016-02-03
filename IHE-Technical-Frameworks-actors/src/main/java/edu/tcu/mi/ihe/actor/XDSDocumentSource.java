package edu.tcu.mi.ihe.actor;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.iti.builder.MetadataXmlBuilder;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.service.ProvideAndRegisterDocumentSetService;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@Component
public class XDSDocumentSource extends Actor {
	
	@Autowired
	private ProvideAndRegisterDocumentSetService provideAndRegisterDocumentSet;

	public void init(){
		if(this.provideAndRegisterDocumentSet == null) this.provideAndRegisterDocumentSet = new ProvideAndRegisterDocumentSetService();
	}
	
	public OMElement provideAndRegisterDocumentSet(MetadataXmlBuilder builder){
		init();
		
		if(builder == null) return null;
		AxiomUtil axiom = AxiomUtil.getInstance();
		String response = provideAndRegisterDocumentSet.transaction(builder);
		return axiom.fromString(response);
	}
	
	public OMElement provideAndRegisterDocumentSet(Metadata metadata, NonBlockCallBack callback){
		System.out.println(metadata.toString());
		return provideAndRegisterDocumentSet(new MetadataXmlBuilder(metadata), callback);
	}

	public OMElement provideAndRegisterDocumentSet(MetadataXmlBuilder builder, NonBlockCallBack callback) {
		init();
		
		if(builder == null) return null;
		String response = provideAndRegisterDocumentSet.transaction(builder, callback);
		if(response.length() == 0) return null;
		AxiomUtil axiom = AxiomUtil.getInstance();
		return axiom.fromString(response);
	}

	public OMElement provideAndRegisterDocumentSet(OMElement request, String endpoint, NonBlockCallBack callback) {
		init();
		
		String response = provideAndRegisterDocumentSet.transaction(request, endpoint, callback);
		if(response.length() == 0) return null;
		AxiomUtil axiom = AxiomUtil.getInstance();
		return axiom.fromString(response);
	}

}
