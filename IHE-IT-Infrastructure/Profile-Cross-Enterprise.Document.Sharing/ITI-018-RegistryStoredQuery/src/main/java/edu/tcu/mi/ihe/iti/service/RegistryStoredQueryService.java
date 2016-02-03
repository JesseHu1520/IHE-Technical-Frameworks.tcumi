package edu.tcu.mi.ihe.iti.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.atna.EventOutcomeIndicator;
import edu.tcu.mi.ihe.iti.builder.QueryXmlBuilder;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.core.SoapTransaction;
import edu.tcu.mi.ihe.iti.model.QueryModel;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_18_110112;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.sender.ws.ServiceConsumer;
import edu.tcu.mi.ihe.sender.ws.Soap;

@Component
public class RegistryStoredQueryService extends SoapTransaction {
	private final String ACTION = "urn:ihe:iti:2007:RegistryStoredQuery";
	
	@Value("${xds.registry}")
	protected String endpoint;
	
	@Autowired
	protected RecordAuditEventService auditService;
	
	private String uuid;

	@Override
	public String webservice(MessageBuilder builder) {
		endpoint = getEndpoint(endpoint, builder);
		
		Soap soap = new Soap(endpoint, ACTION);
		request = builder.getMessageFromXML();
		response = soap.send(request);
		if(response == null) return "";
		
		QueryModel query = ((QueryXmlBuilder)builder).getQuery();
		uuid = query.getUuid();
		return response.toString();
	}

	@Override
	public String webservice(OMElement request, String _endpoint, NonBlockCallBack callback) {
		this.request = request;
		if(this.endpoint == null) this.endpoint = _endpoint;

		logger.info("\nendpoint: " + this.endpoint);
		ServiceConsumer soap = new ServiceConsumer(endpoint, ACTION, callback);
		soap.setMtomXop(false);
		this.response = soap.send(this.request);
		if(this.response == null) return "";
		return this.response.toString();
	}

	@Override
	public void auditLog() {
		// TODO 未完成
		EventOutcomeIndicator eventOutcomeIndicator;
		if (response == null) {
			eventOutcomeIndicator = EventOutcomeIndicator.MajorFailure;
		} else if (assertEquals(
				response,
				"<rs:RegistryResponse xmlns:rs=\"" + Namespace.RS3.getNamespace() + "\" status=\"" + Namespace.SUCCESS.getNamespace() + "\"/>")) {
			eventOutcomeIndicator = EventOutcomeIndicator.Success;
		} else {
			eventOutcomeIndicator = EventOutcomeIndicator.SeriousFaailure;
		}
		
		SysLoggerITI_18_110112 logger = new SysLoggerITI_18_110112();
		logger.setEndpoint(endpoint);

//		List<ParameterType> lis	
		
		logger.setEventOutcomeIndicator(eventOutcomeIndicator);
		logger.setQueryUUID(uuid);
		logger.setRequest(request);
		/** --- Source --- */
		logger.setReplyTo("http://www.w3.org/2005/08/addressing/anonymous");
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
			logger.setLocalIPAddress(addr.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		if(auditService != null) this.auditService.webservice(logger);
	}
}
