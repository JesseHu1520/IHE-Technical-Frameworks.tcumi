package edu.tcu.mi.ihe.iti.syslog;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.axiom.om.OMElement;

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
import edu.tcu.mi.ihe.iti.rfc3881.ParticipantObjectIdentificationType;
import lombok.Getter;
import lombok.Setter;

public class SysLoggerITI_41_110106 extends MessageBuilder {
	@Getter @Setter @NotNull
	private EventOutcomeIndicator eventOutcomeIndicator;
	@Getter @Setter @NotNull
	private String endpoint;
	@Getter @Setter @NotNull
	private String patientId;
	@Getter @Setter @NotNull
	private String XDSSubmissionSetUniqueId;
	@Getter @Setter
	private String replyTo;
	@Getter @Setter
	private String userID;
	@Getter @Setter
	private String localIPAddress;
	
	public SysLoggerITI_41_110106(){
		replyTo = "http://www.w3.org/2005/08/addressing/anonymous";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			localIPAddress = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public OMElement getMessageFromXML(){
		AuditMessageType auditMsg = new AuditMessageType(ETransaction.ITI_41_110106);
		/** --- Event --- */
		/** EventID = EV(110106, DCM, “Export”), Opt=M */
		/** EventTypeCode = EV(“ITI-41”, “IHE Transactions”, “Provider And Registry Document Set”), Opt=M */
		/** “E” (Execute), Opt=M */
		/** Opt=M */
		EventIdentificationType event = new EventIdentificationType(eventOutcomeIndicator);
		auditMsg.setEventIdentification(event);
		/** --- Event --- */

		/** --- Source --- */
		ActiveParticipantType source = new ActiveParticipantType(true);
		/** The content of the <wsa:ReplyTo/> element */
		source.setUserID(replyTo);
		/** the process ID as used within the local operating system in the local system logs. */
		String processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		source.setAlternativeUserID(processId);
		source.setNetworkAccessPoint(localIPAddress);
		/** EV(110153, DCM, “Source”), Opt=M */
		source.addRoleIDCode(new CodedValueType(RFC3881.RoleIDCode, "110153", CodeType.ParticipantRoleType));
		auditMsg.addActiveParticipant(source);
		/** --- Source --- */
		
		/** --- Human Requestor --- */
		ActiveParticipantType humanRequestor = new ActiveParticipantType(true);
		/** Identity of the human that initiated the transaction */
		humanRequestor.setUserID(userID);
		/** Access Control role(s) the user holds that allows this transaction. */
//		humanRequestor.addRoleIDCode(new CodedValueType(RFC3881.RoleIDCode, "110153", CodeType.ParticipantRoleType));
		auditMsg.addActiveParticipant(humanRequestor);
		/** --- Human Requestor --- */
		
		/** --- Destination --- */
		URL url = null;		
		try {
			url = new URL(endpoint);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String host = url.getHost();
		
		ActiveParticipantType destination = new ActiveParticipantType(false);
		/** SOAP endpoint URI. */
		destination.setUserID(endpoint);
		/** The machine name or IP address, as specified in RFC 3881. */
		destination.setNetworkAccessPoint(host);
		/** EV(110152, DCM, “Destination”), Opt=M */
		destination.addRoleIDCode(new CodedValueType(RFC3881.RoleIDCode, "110152", CodeType.ParticipantRoleType));
		auditMsg.addActiveParticipant(destination);
		/** --- Destination --- */
		
		/** --- Audit Source --- */
		AuditSourceIdentificationType auditSource = new AuditSourceIdentificationType(AuditSourceTypeCode.One);
		auditSource.setAuditSourceID(" + + + + + + ");
		auditMsg.addAuditSourceIdentification(auditSource);
		/** --- Audit Source --- */
		
		/** --- Patient --- */
		/** ParticipantObjectTypeCode = “1” (Person) */
		/** ParticipantObjectTypeCodeRole = “1” (Patient) */
		ParticipantObjectIdentificationType patient = new ParticipantObjectIdentificationType(ParticipantObjectTypeCode.Person, ParticipantObjectTypeCodeRole.Patient, null, null);
		/** EV(2, RFC-3881, “Patient Number”) */
		patient.setParticipantObjectIDTypeCode(ParticipantObjectIDTypeCode.PatientNumber);
		/** The patient ID in HL7 CX format. */
		patient.setParticipantObjectID(patientId);
		auditMsg.addParticipantObjectIdentification(patient);
		/** --- Patient --- */
		
		/** --- SubmissionSet --- */
		/** ParticipantObjectTypeCode = “2” (System) */
		/** ParticipantObjectTypeCodeRole = “20” (job) */
		ParticipantObjectIdentificationType submissionSet = new ParticipantObjectIdentificationType(ParticipantObjectTypeCode.SystemObject, ParticipantObjectTypeCodeRole.Job, null, null);
		/** EV(“urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd”, “IHE XDS Metadata”, “submission set classificationNode”) */
		submissionSet.setParticipantObjectIDTypeCode(ParticipantObjectIDTypeCode.SubmissionSetClassificationNode);
		/** The submissionSet unique ID */
		submissionSet.setParticipantObjectID(XDSSubmissionSetUniqueId);
		auditMsg.addParticipantObjectIdentification(submissionSet);
		/** --- SubmissionSet --- */
		
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
