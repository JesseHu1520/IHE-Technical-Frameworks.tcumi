package edu.tcu.mi.ihe;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.axiom.om.OMElement;
import org.junit.Test;

import edu.tcu.mi.ihe.constants.atna.EventOutcomeIndicator;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.service.RecordAuditEventService;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_18_110112;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_41_110106;
import edu.tcu.mi.ihe.iti.syslog.SysLoggerITI_43_110106;
import edu.tcu.mi.ihe.utility.AxiomUtil;

/**
 * @author Gaduo
 *
 */
public class AppTest01  {

//	/**
//	 * @throws UnknownHostException 
//	 * 
//	 */
//	@Test
//	public void testAppITI_41_110106() throws UnknownHostException {
//		String endpoint = "http://ihexds.nist.gov:9080/tf6/services/xdsrepositoryb";
//		String patientId = "TestPatient1^^^&1.3.6.1.4.1.21367.13.20.1000&ISO";
//		String XDSSubmissionSetUniqueId = "1.3.6.1.4.1.21367.2010.1.2.203.64.84.247.20140417204613.1";
//		
//		MessageBuilder loger = new SysLoggerITI_41_110106();
//		((SysLoggerITI_41_110106) loger).setEndpoint(endpoint);
//		((SysLoggerITI_41_110106) loger).setPatientId(patientId);
//		((SysLoggerITI_41_110106) loger).setXDSSubmissionSetUniqueId(XDSSubmissionSetUniqueId);
//		((SysLoggerITI_41_110106) loger).setEventOutcomeIndicator(EventOutcomeIndicator.Success);
//		/** humanRequestor*/
//		((SysLoggerITI_41_110106) loger).setUserID("1");
//		
//		OMElement element = loger.build();
//		System.out.println(element);
//		
//		RecordAuditEventService rae = new RecordAuditEventService();
//		rae.AuditGenerator(element);
//	}
//	
//	public void testAppITI_18_110112() throws UnknownHostException {
//		String endpoint = "http://ihexds.nist.gov:9080/tf6/services/xdsrepositoryb";
//		String patientId = "TestPatient1^^^&1.3.6.1.4.1.21367.13.20.1000&ISO";
//		
//		MessageBuilder loger = new SysLoggerITI_18_110112();
//		((SysLoggerITI_18_110112) loger).setEndpoint(endpoint);
//		((SysLoggerITI_18_110112) loger).setPatientId(patientId);
//		((SysLoggerITI_18_110112) loger).setEventOutcomeIndicator(EventOutcomeIndicator.Success);
//		/** --- Source --- */
//		((SysLoggerITI_18_110112) loger).setReplyTo("http://www.w3.org/2005/08/addressing/anonymous");
//		InetAddress addr = InetAddress.getLocalHost();
//		((SysLoggerITI_18_110112) loger).setLocalIPAddress(addr.getHostAddress());
//
//		/** QueryParameters */
//		((SysLoggerITI_18_110112) loger).setQueryUUID("urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d");
//		AxiomUtil axiom = AxiomUtil.getInstance();
//		OMElement request = axiom.fromResources("ITI-18_request_FIND_DOCUMENTS.xml");
//		((SysLoggerITI_18_110112) loger).setRequest(request );
//		
//		OMElement element = loger.build();
//		System.out.println(element);
//		
//		RecordAuditEventService rae = new RecordAuditEventService();
//		rae.AuditGenerator(element);
//	}
//	
//	public void testAppITI_43_110106() throws UnknownHostException {
//		String endpoint = "http://ihexds.nist.gov:9080/tf6/services/xdsrepositoryb";
//		String patientId = "TestPatient1^^^&1.3.6.1.4.1.21367.13.20.1000&ISO";
//		String documentUniqueId = "1.3.6.1.4.1.21367.2010.1.2.203.64.84.247.20140417204613.1";
//		
//		MessageBuilder loger = new SysLoggerITI_43_110106();
//		((SysLoggerITI_43_110106) loger).setEndpoint(endpoint);
//		((SysLoggerITI_43_110106) loger).setPatientId(patientId);
//		((SysLoggerITI_43_110106) loger).addDocument(documentUniqueId, "1.2.3", "");
//		((SysLoggerITI_43_110106) loger).addDocument(documentUniqueId, "1.2.3", "");
//		((SysLoggerITI_43_110106) loger).setEventOutcomeIndicator(EventOutcomeIndicator.Success);
//		/** humanRequestor*/
//		((SysLoggerITI_43_110106) loger).setUserID("1");
//		/** --- Source --- */
//		((SysLoggerITI_43_110106) loger).setReplyTo("http://www.w3.org/2005/08/addressing/anonymous");
//		InetAddress addr = InetAddress.getLocalHost();
//		((SysLoggerITI_43_110106) loger).setLocalIPAddress(addr.getHostAddress());
//		
//		OMElement element = loger.build();
//		System.out.println(element);
//		
////		RecordAuditEvent rae = new RecordAuditEvent();
////		rae.AuditGenerator(element);
//	}

}
