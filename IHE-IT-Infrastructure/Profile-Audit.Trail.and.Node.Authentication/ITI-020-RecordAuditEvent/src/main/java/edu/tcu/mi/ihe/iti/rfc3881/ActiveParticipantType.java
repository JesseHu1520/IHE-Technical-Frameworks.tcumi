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
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.constants.atna.NetworkAccessPointTypeCode;
import edu.tcu.mi.ihe.constants.atna.RFC3881;


@XmlType(name="ActiveParticipantType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ActiveParticipantType extends RFC3881Message {

	public static Logger logger = Logger.getLogger(ActiveParticipantType.class);

	@XmlAttribute(name = "UserID")
	protected String userID;
	@XmlAttribute(name = "AlternativeUserID")
	protected String alternativeUserID;
	@XmlAttribute(name = "UserName")
	protected String userName; // ITI-41, ITI-63
	@XmlAttribute(name = "UserIsRequestor")
	protected boolean userIsRequestor;
	@XmlAttribute(name="NetworkAccessPointTypeCode")
	protected String networkAccessPointTypeCode;
	@XmlAttribute(name="NetworkAccessPointID")
	protected String networkAccessPointID;
	
	@XmlElement(name = "RoleIDCode")
	protected List<CodedValueType> roleIDCodes; // [0 ... *]
	
	
	public ActiveParticipantType(){
		
	}
	
	public ActiveParticipantType(boolean userIsRequestor){
		this.userIsRequestor = userIsRequestor;
		roleIDCodes = new ArrayList<CodedValueType>();
	}
	
	/* --------------------------------------------------- */

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @param alternativeUserID the alternativeUserID to set
	 */
	public void setAlternativeUserID(String alternativeUserID) {
		this.alternativeUserID = alternativeUserID;
	}

	/**
	 * @param networkAccessPointID the networkAccessPointID to set
	 */
	public void setNetworkAccessPoint(String networkAccessPointID) {
		this.networkAccessPointID = networkAccessPointID;
		if(networkAccessPointID != null){
			if (validateIPAddress(networkAccessPointID)) {
				this.networkAccessPointTypeCode = NetworkAccessPointTypeCode.IPAddress.getValue();
			} else {
				this.networkAccessPointTypeCode = NetworkAccessPointTypeCode.MachineName.getValue();
			}
		}
	}
	
	public boolean validateIPAddress(String ipAddress) {
		String[] tokens = ipAddress.split("\\.");
		if (tokens.length != 4) {
			return false;
		}
		for (String str : tokens) {
			int i = Integer.parseInt(str);
			if ((i < 0) || (i > 255)) {
				return false;
			}
		}
		return true;
	}
	
	
	
	public void addRoleIDCode(CodedValueType roleIDCode){
		roleIDCodes.add(roleIDCode);
	}
	
	/* --------------------------------------------------- */
	

	@Override
	public OMElement buildRFC3881() {
		OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.ActiveParticipant.getTag(), namespace);
		root.addAttribute("UserIsRequestor", userIsRequestor + "", null);
		
		
		if(userID != null)
			root.addAttribute("UserID", userID, null);
		if(alternativeUserID != null)
			root.addAttribute("AlternativeUserID", alternativeUserID, null);
		if(userName != null)
			root.addAttribute("UserName", userName, null);
		if(networkAccessPointID != null)
			root.addAttribute("NetworkAccessPointID", networkAccessPointID, null);
		if(networkAccessPointTypeCode != null)
			root.addAttribute("NetworkAccessPointTypeCode", networkAccessPointTypeCode, null);
		
		if(roleIDCodes != null){
			Iterator<CodedValueType> iterator = roleIDCodes.iterator();
			while(iterator.hasNext()){
				CodedValueType next = iterator.next();
				OMElement element = next.buildRFC3881();
				if(element != null)
					root.addChild(element);
			}
		}
		return root;
	}

}
