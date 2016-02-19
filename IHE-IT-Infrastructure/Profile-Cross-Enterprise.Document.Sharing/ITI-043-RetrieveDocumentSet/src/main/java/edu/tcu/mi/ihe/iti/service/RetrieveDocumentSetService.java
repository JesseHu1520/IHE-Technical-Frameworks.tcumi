package edu.tcu.mi.ihe.iti.service;

import java.util.Set;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.iti.builder.RetrieveBuilder;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.core.SoapTransaction;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_43_110106;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.sender.ws.ServiceConsumer;
import edu.tcu.mi.ihe.sender.ws.Soap;
import lombok.Setter;

@Component
public class RetrieveDocumentSetService extends SoapTransaction {
	private final String ACTION = "urn:ihe:iti:2007:RetrieveDocumentSet";
	
	@Value("${xds.repository}")
	private String endpoint;
	
	@Setter
	@Autowired
	private RecordAuditEventService auditService;
	
	private RetrieveBuilder builder;
	
	@Override
	public String webservice(MessageBuilder builder) {
		this.builder = (RetrieveBuilder) builder;
		if(endpoint == null) endpoint = builder.getEndpoint();
		
		Soap soap = new Soap(endpoint, ACTION);
		soap.setMtomXop(true);
		request = builder.getMessageFromXML();
		response = soap.send(request);
		return response.toString();
	}

	@Override
	public String webservice(OMElement request, String _endpoint, NonBlockCallBack callback) {
		this.request = request;
		if(this.endpoint == null) this.endpoint = _endpoint;

		logger.info("\nendpoint: " + this.endpoint);
		ServiceConsumer soap = new ServiceConsumer(endpoint, ACTION, callback);
		soap.setMtomXop(true);
		this.response = soap.send(this.request);
		if(this.response == null) return "";
		return this.response.toString();
	}

	@Override
	public void auditLog() {
		SysLoggerITI_43_110106 logger = new SysLoggerITI_43_110106();
		logger.setEndpoint(endpoint);
		RetrieveModel model = builder.getRetrieveModel();
		Set<String> ids = model.getDocumentIds();
		String repositoryUniqueId = model.getRepositoryUniqueId();
		String homeCommunityId = model.getHomeCommunityId();
		for(String id:ids){
			logger.addDocument(id, repositoryUniqueId, homeCommunityId);
		}
		// TODO Auto-generated method stub
//		if(auditService != null) this.auditService.transaction(logger);
	}

}
