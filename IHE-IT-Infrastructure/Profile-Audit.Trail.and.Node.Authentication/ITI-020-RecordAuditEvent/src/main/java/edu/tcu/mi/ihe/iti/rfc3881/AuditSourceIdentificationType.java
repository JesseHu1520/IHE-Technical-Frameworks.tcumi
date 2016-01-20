package edu.tcu.mi.ihe.iti.rfc3881;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.AuditSourceTypeCode;
import edu.tcu.mi.ihe.constants.atna.RFC3881;

@XmlType(name="AuditSourceIdentificationType")
@XmlAccessorType (XmlAccessType.FIELD)
public class AuditSourceIdentificationType extends RFC3881Message{
	@XmlAttribute(name="AuditSourceID", required=true)
	protected String auditSourceID; // required
	@XmlAttribute(name="AuditEnterpriseSiteID")
	protected String auditEnterpriseSiteID;
	@XmlAttribute(name="code")
	private String code;
	
	@XmlElement(name="AuditSourceTypeCode")
	protected List<CodedValueType> auditSourceTypeCodes; // [0 ... *]
	

	
	public AuditSourceIdentificationType(){
	}
	
	
	public AuditSourceIdentificationType(AuditSourceTypeCode auditSourceTypeCode) {
		code = auditSourceTypeCode.getValue();
	}
	
	/* --------------------------------------------------------- */

	/**
	 * @param auditSourceID the auditSourceID to set
	 */
	public void setAuditSourceID(String auditSourceID) {
		this.auditSourceID = auditSourceID;
	}
	
	/* --------------------------------------------------------- */	
	
	@Override
	public OMElement buildRFC3881() {
		OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.AuditSourceIdentification.getTag(), namespace);
		if(code != null)
			root.addAttribute("code", code, null);
		if(auditSourceID != null)
			root.addAttribute("AuditSourceID", auditSourceID, null);
		if(auditEnterpriseSiteID != null)
			root.addAttribute("AuditEnterpriseSiteID", auditEnterpriseSiteID, null);
		if(auditSourceTypeCodes != null){
			Iterator<CodedValueType> iterator = auditSourceTypeCodes.iterator();
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
