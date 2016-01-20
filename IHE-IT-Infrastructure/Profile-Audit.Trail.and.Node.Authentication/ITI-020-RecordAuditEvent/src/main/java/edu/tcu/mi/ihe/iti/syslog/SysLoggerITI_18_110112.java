package edu.tcu.mi.ihe.iti.syslog;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.binary.Base64;

import com.sun.istack.NotNull;

import edu.tcu.mi.ihe.constants.atna.AuditSourceTypeCode;
import edu.tcu.mi.ihe.constants.atna.CodeType;
import edu.tcu.mi.ihe.constants.atna.ETransaction;
import edu.tcu.mi.ihe.constants.atna.EventOutcomeIndicator;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectIDTypeCode;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectTypeCode;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectTypeCodeRole;
import edu.tcu.mi.ihe.constants.atna.RFC3881;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.rfc3881.ActiveParticipantType;
import edu.tcu.mi.ihe.iti.rfc3881.AuditMessageType;
import edu.tcu.mi.ihe.iti.rfc3881.AuditSourceIdentificationType;
import edu.tcu.mi.ihe.iti.rfc3881.CodedValueType;
import edu.tcu.mi.ihe.iti.rfc3881.EventIdentificationType;
import edu.tcu.mi.ihe.iti.rfc3881.ParticipantObjectDetailType;
import edu.tcu.mi.ihe.iti.rfc3881.ParticipantObjectIdentificationType;
import edu.tcu.mi.ihe.iti.rfc3881.ParticipantObjectNameType;
import edu.tcu.mi.ihe.iti.rfc3881.ParticipantObjectQueryType;
import lombok.Getter;
import lombok.Setter;

public class SysLoggerITI_18_110112 extends MessageBuilder {
	@Getter @Setter @NotNull
	private EventOutcomeIndicator eventOutcomeIndicator;
	
	@Getter @Setter @NotNull
	private String endpoint;
	
	@Getter @Setter @NotNull
	private String patientId;
	
	@Getter @Setter @NotNull
	private String homeCommunityId;
	
	@Getter @NotNull
	private String request;
	
	@Getter @Setter @NotNull
	private String queryUUID;
	
	@Getter @Setter
	private String localIPAddress;
	
	@Getter @Setter
	private String replyTo;

	public SysLoggerITI_18_110112(){
		replyTo = "http://www.w3.org/2005/08/addressing/anonymous";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			localIPAddress = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request the request to set
	 */
	public void setRequest(OMElement request) {
		
		byte[] b = Base64.encodeBase64(request.toString().getBytes());
		try {
			this.request = new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public OMElement getMessageFromXML() {
		AuditMessageType auditMsg = new AuditMessageType(ETransaction.ITI_18_110112);
		/** --- Event --- */
		/** EventID = EV(110112, DCM, “Query”), Opt = M */
		/** EventTypeCode = EV(“ITI-18”, “IHE Transactions”, “Registry Stored Query”), Opt = M */
		/** “E” (Execute), Opt = M */
		/** Opt = M */
		EventIdentificationType event = new EventIdentificationType(eventOutcomeIndicator);
		auditMsg.setEventIdentification(event);
		/** --- Event --- */

		/** --- Source --- */
		ActiveParticipantType source = new ActiveParticipantType(true);
		/**The content of the <wsa:ReplyTo/> element. Opt = M*/
		source.setUserID(replyTo);
		/** The process ID as used within the local operating system in the local system logs. Opt = M */
		String processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		source.setAlternativeUserID(processId);
		/** The machine name or IP address, as specified in RFC 3881. Opt = M */
		source.setNetworkAccessPoint(localIPAddress);
		/** EV(110153, DCM, “Source”) Opt = M */
		source.addRoleIDCode(new CodedValueType(RFC3881.RoleIDCode, "110153", CodeType.ParticipantRoleType));
		auditMsg.addActiveParticipant(source);
		/** --- Source --- */
		
		/** --- Destination --- */
		URL url = null;		
		try {
			url = new URL(endpoint);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String host = url.getHost();
		ActiveParticipantType destination = new ActiveParticipantType(false);
		/** SOAP endpoint URI. Opt = M */
		destination.setUserID(endpoint);
		/** The machine name or IP address, as specified in RFC 3881. Opt = M */
		destination.setNetworkAccessPoint(host);
		/** EV(110152, DCM, “Destination”), Opt = M */
		destination.addRoleIDCode(new CodedValueType(RFC3881.RoleIDCode, "110152", CodeType.ParticipantRoleType));
		auditMsg.addActiveParticipant(destination);
		/** --- Destination --- */
		
		/** --- Audit Source --- */
		AuditSourceIdentificationType auditSource = new AuditSourceIdentificationType(AuditSourceTypeCode.One);
		auditSource.setAuditSourceID(" *** ");
		auditMsg.addAuditSourceIdentification(auditSource);
		/** --- Audit Source --- */
		
		/** --- Patient --- */
		if(patientId != null){
			/** ParticipantObjectTypeCode = “1” (Person), Opt = M */
			/** ParticipantObjectTypeCodeRole = “1” (Patient), Opt = M */
			ParticipantObjectIdentificationType patient = new ParticipantObjectIdentificationType(ParticipantObjectTypeCode.Person, ParticipantObjectTypeCodeRole.Patient, null, null);
			/** The patient ID in HL7 CX format. Opt = M */
			patient.setParticipantObjectID(patientId);
			/** EV(2, RFC-3881, “Patient Number”), Opt = M */
			patient.setParticipantObjectIDTypeCode(ParticipantObjectIDTypeCode.PatientNumber);
			auditMsg.addParticipantObjectIdentification(patient);
		}
		/** --- Patient --- */
		
		/** --- QueryParameters --- */
		/** ParticipantObjectTypeCode = “2” (system object), Opt = M */
		/** ParticipantObjectTypeCodeRole = “24” (query), Opt = M */
		ParticipantObjectIdentificationType registryStoredQuery = new ParticipantObjectIdentificationType(ParticipantObjectTypeCode.SystemObject, ParticipantObjectTypeCodeRole.Query, null, null);
		/** EV(“ITI-18”, “IHE Transactions”, “Registry Stored Query”) */
		registryStoredQuery.setParticipantObjectIDTypeCode(new CodedValueType(RFC3881.ParticipantObjectIDTypeCode, "ITI-18", CodeType.EventType));
		/** Stored Query ID (UUID) */
		registryStoredQuery.setParticipantObjectID(queryUUID);
		if(homeCommunityId != null){
			/** If known the value of <ihe:HomeCommunityId/> */
			registryStoredQuery.addParticipantObjectName(new ParticipantObjectNameType(homeCommunityId));
		}
		/** the AdhocQueryRequest, base64 encoded. */
		registryStoredQuery.addParticipantObjectQuery(new ParticipantObjectQueryType(request));
		
		/**
		 *  In one element, set “QueryEncoding”as the value of the attribute type,
		 *  Set the attribute value to the character encoding, such as “UTF-8”, used
		 *  to encode the ParticipantObjectQuery before base64 encoding.
		 */
		registryStoredQuery.addParticipantObjectDetail(new ParticipantObjectDetailType("QueryEncoding", "UTF-8"));
		
		if(homeCommunityId != null){
			/**
			 * In another element, set “urn:ihe:iti:xca:2010:homeCommunityId” as the
			 * value of the attribute type and the value of the homeCommunityID as
			 * the value of the attribute value, if known.
			 */
			registryStoredQuery.addParticipantObjectDetail(new ParticipantObjectDetailType("urn:ihe:iti:xca:2010:homeCommunityId", homeCommunityId));
		}
		auditMsg.addParticipantObjectIdentification(registryStoredQuery);
		/** --- QueryParameters --- */
		
		OMElement element = auditMsg.buildRFC3881();
		return element;
		
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
