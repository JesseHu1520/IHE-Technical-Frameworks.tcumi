package edu.tcu.mi.ihe.iti.service;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.atna.EventOutcomeIndicator;
import edu.tcu.mi.ihe.iti.builder.MetadataXmlBuilder;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.core.SoapTransaction;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_41_110106;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.sender.ws.ServiceConsumer;
import edu.tcu.mi.ihe.sender.ws.Soap;
import lombok.Setter;

@Component
public class ProvideAndRegisterDocumentSetService extends SoapTransaction {
	private final String ACTION = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b";

	@Value("${xds.repository}")
	protected String endpoint;
	
	@Setter
	@Autowired
	protected RecordAuditEventService auditService;

	@Override
	public String webservice(MessageBuilder builder) {
		endpoint = getEndpoint(endpoint, builder);
		
		Soap soap = new Soap(endpoint, ACTION);
		soap.setMtomXop(true);
		request = builder.getMessageFromXML();
		response = soap.send(request);
		if(response == null) return "";
		return response.toString();
	}

	@Override
	public String webservice(OMElement request, String _endpoint, NonBlockCallBack callback) {
		this.request = request;
		if(this.endpoint == null) this.endpoint = _endpoint;

		logger.info("\nendpoint: " + this.endpoint);
		ServiceConsumer soap = new ServiceConsumer(this.endpoint, ACTION, callback);
		soap.setMtomXop(true);
		this.response = soap.send(this.request);
		if(this.response == null) return "";
		return this.response.toString();
	}

	@Override
	public void auditLog() {
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
		
		Metadata metadata = ((MetadataXmlBuilder)messageBuilder).getMetadata();
		Patient patient = metadata.getPatient();
		SubmissionSet submissionSet = metadata.getSubmissionSet();
		String patientId = patient.getId();
		String XDSSubmissionSetUniqueId = submissionSet.getId();
		
		SysLoggerITI_41_110106 logger = new SysLoggerITI_41_110106();
		logger.setEndpoint(endpoint);
		logger.setPatientId(patientId);
		logger.setXDSSubmissionSetUniqueId(XDSSubmissionSetUniqueId);
		logger.setEventOutcomeIndicator(eventOutcomeIndicator);
		/** humanRequestor*/
		logger.setUserID("1");
		
		if(auditService != null) this.auditService.webservice(logger);
	}

}
