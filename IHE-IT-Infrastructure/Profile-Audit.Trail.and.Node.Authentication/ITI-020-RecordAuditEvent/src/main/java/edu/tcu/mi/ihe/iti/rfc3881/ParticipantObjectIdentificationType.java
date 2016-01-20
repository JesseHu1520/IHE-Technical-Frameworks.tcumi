package edu.tcu.mi.ihe.iti.rfc3881;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.ParticipantObjectDataLifeCycle;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectIDTypeCode;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectTypeCode;
import edu.tcu.mi.ihe.constants.atna.ParticipantObjectTypeCodeRole;
import edu.tcu.mi.ihe.constants.atna.RFC3881;

/**
 * @author Gaduo
 *
 */


@XmlType(name="ParticipantObjectIdentificationType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ParticipantObjectIdentificationType extends RFC3881Message{
	@XmlAttribute(name = "ParticipantObjectTypeCode")
	protected int participantObjectTypeCode = -1;
	@XmlAttribute(name = "ParticipantObjectTypeCodeRole")
	protected int participantObjectTypeCodeRole = -1;	
	@XmlAttribute(name = "ParticipantObjectID")
	protected String participantObjectID;
	
	protected String participantObjectDataLifeCycle; // ITI-45
	
	@XmlElement(name = "ParticipantObjectIDTypeCode")
	protected CodedValueType participantObjectIDTypeCode; // [1 ... 1]
	protected String participantObjectSensitivity;
	@XmlElement(name = "ParticipantObjectName")
	protected List<ParticipantObjectNameType> participantObjectNames; // [0 ... 1]   // ITI-08, ITI-44, ITI-52
	@XmlElement(name = "ParticipantObjectQuery")
	protected List<ParticipantObjectQueryType> participantObjectQuerys; // [0 ... 1]  // ITI-09, ITI-18, ITI-21, ITI-22, ITI-45
	@XmlElement(name = "ParticipantObjectDetail")
	protected List<ParticipantObjectDetailType> participantObjectDetails; //[0 ... *] // ITI-08, ITI-09, ITI-18, ITI-21, ITI-22, ITI-43
	
	
	public ParticipantObjectIdentificationType(){
		
	}
	
	public ParticipantObjectIdentificationType(
			ParticipantObjectTypeCode participantObjectTypeCode, 
			ParticipantObjectTypeCodeRole participantObjectTypeCodeRole,
			ParticipantObjectDataLifeCycle participantObjectDataLifeCycle,
			ParticipantObjectIDTypeCode participantObjectIDTypeCode) {
		if(participantObjectTypeCode != null)
			this.participantObjectTypeCode = participantObjectTypeCode.getValue();
		if(participantObjectTypeCodeRole != null)
			this.participantObjectTypeCodeRole = participantObjectTypeCodeRole.getValue();
		if(participantObjectDataLifeCycle != null)
			this.participantObjectDataLifeCycle = participantObjectDataLifeCycle.toString();
		if(participantObjectIDTypeCode != null)
			this.participantObjectIDTypeCode = participantObjectIDTypeCode.getValue();
		
		participantObjectNames = new ArrayList<ParticipantObjectNameType>();
		participantObjectQuerys = new ArrayList<ParticipantObjectQueryType>();
		participantObjectDetails = new ArrayList<ParticipantObjectDetailType>();
	}


	/**
	 * @param participantObjectID the participantObjectID to set
	 */
	public void setParticipantObjectID(String participantObjectID) {
		this.participantObjectID = participantObjectID;
	}
	

	/**
	 * @param participantObjectIDTypeCode the participantObjectIDTypeCode to set
	 */
	public void setParticipantObjectIDTypeCode(ParticipantObjectIDTypeCode participantObjectIDTypeCode) {
		this.participantObjectIDTypeCode = participantObjectIDTypeCode.getValue();
	}	
	
	public void setParticipantObjectIDTypeCode(CodedValueType participantObjectIDTypeCode) {
		this.participantObjectIDTypeCode = participantObjectIDTypeCode;
	}


	public void addParticipantObjectName(ParticipantObjectNameType participantObjectName){
		participantObjectNames.add(participantObjectName);
	}
	
	public void addParticipantObjectQuery(ParticipantObjectQueryType participantObjectQuery){
		participantObjectQuerys.add(participantObjectQuery);
	}
	
	public void addParticipantObjectDetail(ParticipantObjectDetailType participantObjectDetail){
		participantObjectDetails.add(participantObjectDetail);
	}



	@Override
	public OMElement buildRFC3881() {
    	OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.ParticipantObjectIdentification.getTag(), namespace);
		if(participantObjectID != null){
			root.addAttribute("ParticipantObjectID", participantObjectID, null);
		}
		
		if(participantObjectTypeCode != -1){
			root.addAttribute("ParticipantObjectTypeCode", participantObjectTypeCode + "", null);
		}
		
		if(participantObjectTypeCodeRole != -1){
			root.addAttribute("ParticipantObjectTypeCodeRole", participantObjectTypeCodeRole + "", null);
		}
		
		if(participantObjectDataLifeCycle != null){
			root.addAttribute("ParticipantObjectDataLifeCycle", participantObjectDataLifeCycle, null);
		}
		
		if(participantObjectIDTypeCode != null){
			OMElement element = participantObjectIDTypeCode.buildRFC3881();
			root.addChild(element);
		}
		
		if(participantObjectNames != null){
			Iterator<ParticipantObjectNameType> iterator = participantObjectNames.iterator();
			while(iterator.hasNext()){
				ParticipantObjectNameType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null){
					root.addChild(element);
				}
			}
		}
		if(participantObjectQuerys != null){
			Iterator<ParticipantObjectQueryType> iterator = participantObjectQuerys.iterator();
			while(iterator.hasNext()){
				ParticipantObjectQueryType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null){
					root.addChild(element);
				}
			}
		}
		if(participantObjectDetails != null){
			Iterator<ParticipantObjectDetailType> iterator = participantObjectDetails.iterator();
			while(iterator.hasNext()){
				ParticipantObjectDetailType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null){
					root.addChild(element);
				}
			}
		}
		
		return root;
	}

}
