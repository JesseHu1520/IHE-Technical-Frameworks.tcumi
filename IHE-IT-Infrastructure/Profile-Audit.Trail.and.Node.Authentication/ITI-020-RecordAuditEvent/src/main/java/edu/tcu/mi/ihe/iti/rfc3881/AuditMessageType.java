package edu.tcu.mi.ihe.iti.rfc3881;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.ETransaction;
import edu.tcu.mi.ihe.constants.atna.EventActionCode;
import edu.tcu.mi.ihe.constants.atna.RFC3881;

/**
 * @author Gaduo
 *
 */
@XmlRootElement(name = "AuditMessage")
@XmlAccessorType (XmlAccessType.FIELD)
public class AuditMessageType extends RFC3881Message{

	@XmlElement(name="EventIdentification")
	protected EventIdentificationType eventIdentification; // [1 ... 1]
	
	@XmlElement(name="ActiveParticipant")
	protected List<ActiveParticipantType> activeParticipants; // [1 ... *]
	@XmlElement(name="AuditSourceIdentification")
	protected List<AuditSourceIdentificationType> auditSourceIdentifications; // [1 ... *]
	@XmlElement(name="ParticipantObjectIdentification")
	protected List<ParticipantObjectIdentificationType> participantObjectIdentifications; // [0 ... *]
	
	private ETransaction eTransaction;
	
	
	public AuditMessageType(){
		activeParticipants = new ArrayList<ActiveParticipantType>();
		auditSourceIdentifications = new ArrayList<AuditSourceIdentificationType>();
		participantObjectIdentifications = new ArrayList<ParticipantObjectIdentificationType>();
		
	}
	public AuditMessageType(ETransaction eTransaction){
		activeParticipants = new ArrayList<ActiveParticipantType>();
		auditSourceIdentifications = new ArrayList<AuditSourceIdentificationType>();
		participantObjectIdentifications = new ArrayList<ParticipantObjectIdentificationType>();
		
		this.eTransaction = eTransaction;
	}
	
	public void setEventIdentification(EventIdentificationType eventIdentification){
		this.eventIdentification = eventIdentification;
		CodedValueType eventID = eTransaction.getEventID();
		CodedValueType eventTypeCode = eTransaction.getEventTypeCode();
		EventActionCode eventActionCode = eTransaction.getEventActionCode();
		this.eventIdentification.eventActionCode = eventActionCode.getValue();
		this.eventIdentification.eventID = eventID;
		this.eventIdentification.eventTypeCode.add(eventTypeCode);
	}
	
	public void addActiveParticipant(ActiveParticipantType a){
		activeParticipants.add(a);
	}
	
	public void addAuditSourceIdentification(AuditSourceIdentificationType a){
		auditSourceIdentifications.add(a);
	}
	
	public void addParticipantObjectIdentification(ParticipantObjectIdentificationType p){
		participantObjectIdentifications.add(p);
	}
	
	@Override
	public OMElement buildRFC3881() {
		OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.AuditMessage.getTag(), namespace);
		
		if(eventIdentification != null){
			OMElement element = eventIdentification.buildRFC3881();
			if(element != null)
				root.addChild(element);
		}		
		
		if(activeParticipants != null){
			Iterator<ActiveParticipantType> iterator = activeParticipants.iterator();
			while(iterator.hasNext()){
				ActiveParticipantType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null)
					root.addChild(element);
			}
		}
		
		if(auditSourceIdentifications != null){
			Iterator<AuditSourceIdentificationType> iterator = auditSourceIdentifications.iterator();
			while(iterator.hasNext()){
				AuditSourceIdentificationType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null)
					root.addChild(element);
			}
		}
		
		if(participantObjectIdentifications != null){
			Iterator<ParticipantObjectIdentificationType> iterator = participantObjectIdentifications.iterator();
			while(iterator.hasNext()){
				ParticipantObjectIdentificationType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null)
					root.addChild(element);
			}
		}
		return root;
	}

}
