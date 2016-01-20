/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.constants.Namespace;

/**
 * The <code>Response_ITI_18_Fast.java</code> class.
 * 
 * @version $Name: $, $Revision: 1.0 $, $Date: 2014/1/24 $
 * @author <a href="mailto:leesh@datacom.com.tw">Siang Hao Lee</a>
 */

public class Response_ITI_18_Fast {
	
	public static Logger logger = Logger.getLogger(Response_ITI_18_Fast.class);
	private static String CREATION_TIME = "creationTime";
	private static String HASH = "hash";
	private static String LANGUAGE_CODE = "languageCode";
	private static String REPOSITORY_UNIQUE_ID = "repositoryUniqueId";
	private static String SERVICE_START_TIME = "serviceStartTime";
	private static String SERVICE_STOP_TIME = "serviceStopTime";
	private static String SIZE = "size";
	private static String SOURCE_PATIENT_ID = "sourcePatientId";
	private static String SOURCE_PATIENT_INFO = "sourcePatientInfo";
	private static String AUTHOR_INSTITUTION = "authorInstitution";
	private static String AUTHOR_PERSON = "authorPerson";
	private static String AUTHOR_ROLE = "authorRole";
	private static String AUTHOR_SPECIALTY = "authorSpecialty";
	private static String AUTHOR_SCHEME = "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d";
	private static String CLASS_CODE_SCHEME = "urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a";
	private static String CONFIDENTIALITY_CODE_SCHEME = "urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f";
	private static String FORMAT_CODE_SCHEME = "urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d";
	private static String HEALTHCARE_FACILITY_TYPE_CODE_SCHEME = "urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1";
	private static String PRACTICE_SETTING_CODE_SCHEME = "urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead";
	private static String EVENT_CODE_LIST_SCHEME = "urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4";
	private static String TYPE_CODE_SCHEME = "urn:uuid:f0306f51-975f-434e-a61c-c59651d33983";
	//private static String CONTENT_TYPE_CODE_SCHEME = "urn:uuid:aa543740-bdda-424e-8c96-df4873be8500";
	//private static String FOLDER_CODE_LIST_SCHEME = "urn:uuid:1ba97051-7806-41a8-a48b-8fce7af683c5";
	private static String CODEING_SCHEME = "codingScheme";
	private static String XDSDocumentEntryPatientId_SCHEME = "urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427";
	private static String XDSDocumentEntryUniqueId_SCHEME = "urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab";
	private OMElement response;
	private String status;
	private String codeContext;
	private String errorCode;
	private String severity;
	private String location;
	private Set<String> list;
	private List<ResponseAttachmentEntry> attachmentEntityList;

	/**
	 * @param msgContext
	 */
	public void parser(MessageContext msgContext) {
		setList(new TreeSet<String>());
		if (msgContext != null) {
			parser(msgContext.getEnvelope());
		} else {
			this.setCodeContext("NO MessageContext");
		}
	}

	public void parser(SOAPEnvelope envelope) {
		if (envelope != null) {
			SOAPBody body = envelope.getBody();// first layer
			parser(body);			
		}
	}
	
	public void parser(SOAPBody body){
		response = body.getFirstElement(); // second layer
		parser(response);
	}
	
	public void parser (OMElement response){
		setList(new TreeSet<String>());
//		logger.info(response);
		if (response != null) {
			status = response.getAttributeValue(new QName("status"));
			OMElement list = response.getFirstElement();
			if (list != null) {
				@SuppressWarnings("unchecked")
				Iterator<OMElement> elements = list.getChildElements();
				while (elements.hasNext()) {
					OMElement element = elements.next();// third layer
//					logger.info(element);
					if (element != null) {
						if (status.equals(Namespace.SUCCESS.getNamespace())) {
							success(element);
						} else if (status.equals(Namespace.FAILURE.getNamespace())) {
							failure(element);
						}
					}
				}
			}
		}
		synchronized (this) {
			this.notify();
		}
	}

	@SuppressWarnings("unchecked")
	private void success(OMElement bodyRootElement){
		logger.info("SUCCESS");
		QName qname = new QName("objectType");
		String objectType = bodyRootElement.getAttributeValue(qname);
		objectType = objectType != null ? objectType.trim() : null;
		logger.info(objectType);
		if (objectType != null) {// LeafClass
			String id = bodyRootElement.getAttributeValue(new QName("id")).trim();
			
			// file name
			Iterator<OMElement> nameIterator = bodyRootElement.getChildrenWithName(new QName("Name"));
			String filename = null;
			if (nameIterator != null && nameIterator.hasNext()) {
    	        while (nameIterator.hasNext()) {
    	            OMElement next = nameIterator.next();
    	            if (next != null && next.getFirstElement() != null)
	                    if (next.getFirstElement().getAttributeValue(new QName("value")) != null)
	                        filename = next.getFirstElement().getAttributeValue(new QName("value")).trim();
    	            //next.getFirstChildWithName(new QName("LocalizedString"));
    	        }
			}
			
			// file desc
			Iterator<OMElement> descIterator = bodyRootElement.getChildrenWithName(new QName("Description"));
            String description = null;
            if (descIterator != null && descIterator.hasNext()) {
                while (descIterator.hasNext()) {
                    OMElement next = descIterator.next();
                    if (next != null && next.getFirstElement() != null)
                        if (next.getFirstElement().getAttributeValue(new QName("value")) != null)
                            description = next.getFirstElement().getAttributeValue(new QName("value")).trim();
                }
            }
            
			//element.getChildrenWithLocalName("Name");
			//element.getFirstElement();
			this.list.add(id);
			ResponseAttachmentEntry rae = new ResponseAttachmentEntry(id, filename, description);
			
			// Slot Info
            Iterator<OMElement> slotIterator = bodyRootElement.getChildrenWithName(new QName("Slot"));
            if (slotIterator != null && slotIterator.hasNext()) {
                while (slotIterator.hasNext()) {
                    OMElement next = slotIterator.next();
                    if (next != null && next.getFirstElement() != null && next.getFirstElement().getFirstElement() != null) {
                        if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(CREATION_TIME)) {
                            rae.setCreationTime(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(HASH)) {
                            rae.setHash(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(LANGUAGE_CODE)) {
                            rae.setLanguageCode(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(REPOSITORY_UNIQUE_ID)) {
                            rae.setRepositoryUniqueId(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(SERVICE_START_TIME)) {
                            rae.setServiceStartTime(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(SERVICE_STOP_TIME)) {
                            rae.setServiceStopTime(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(SIZE)) {
                            rae.setSize(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(SOURCE_PATIENT_ID)) {
                            rae.setSourcePatientId(next.getFirstElement().getFirstElement().getText());
                        }else if (next.getAttributeValue(new QName("name")) != null && next.getAttributeValue(new QName("name")).equalsIgnoreCase(SOURCE_PATIENT_INFO)) {
                            Iterator<OMElement> subSourcePatientInfo = next.getFirstElement().getChildrenWithName(new QName("Value"));
                            if (subSourcePatientInfo.hasNext()) {
                                int i = 0;
                                while (subSourcePatientInfo.hasNext()) {
                                    OMElement patientInfo = subSourcePatientInfo.next();
                                    rae.getSourcePatientInfo()[i] = patientInfo.getText();
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
            
            // Classification Info
            Iterator<OMElement> classificationIterator = bodyRootElement.getChildrenWithName(new QName("Classification"));
            if (classificationIterator != null && classificationIterator.hasNext()) {
                while (classificationIterator.hasNext()) {
                    OMElement next = classificationIterator.next();
                    if (next != null && next.getFirstElement() != null) {
                        if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(AUTHOR_SCHEME)) {
                            Iterator<OMElement> authorSlotIterator = next.getChildrenWithName(new QName("Slot"));
                            if (authorSlotIterator.hasNext()) {
                                while (authorSlotIterator.hasNext()) {
                                    OMElement authorNext = authorSlotIterator.next();
                                    if (authorNext != null && authorNext.getFirstElement() != null && authorNext.getFirstElement().getFirstElement() != null) {
                                        if (authorNext.getAttributeValue(new QName("name")) != null && authorNext.getAttributeValue(new QName("name")).equalsIgnoreCase(AUTHOR_INSTITUTION)) {
                                            rae.setAuthorInstitution(authorNext.getFirstElement().getFirstElement().getText());
                                        }else if (authorNext.getAttributeValue(new QName("name")) != null && authorNext.getAttributeValue(new QName("name")).equalsIgnoreCase(AUTHOR_PERSON)) {
                                            rae.setAuthorPerson(authorNext.getFirstElement().getFirstElement().getText());
                                        }else if (authorNext.getAttributeValue(new QName("name")) != null && authorNext.getAttributeValue(new QName("name")).equalsIgnoreCase(AUTHOR_ROLE)) {
                                            rae.setAuthorRole(authorNext.getFirstElement().getFirstElement().getText());
                                        }else if (authorNext.getAttributeValue(new QName("name")) != null && authorNext.getAttributeValue(new QName("name")).equalsIgnoreCase(AUTHOR_SPECIALTY)) {
                                            rae.setAuthorSpecialty(authorNext.getFirstElement().getFirstElement().getText());
                                        }
                                    }
                                }
                            }
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(CLASS_CODE_SCHEME)) {
                            rae.setClassCode(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(CONFIDENTIALITY_CODE_SCHEME)) {
                            rae.setConfidentialityCode(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(FORMAT_CODE_SCHEME)) {
                            rae.setFormatCode(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(HEALTHCARE_FACILITY_TYPE_CODE_SCHEME)) {
                            rae.setHealthcareFacilityTypeCode(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(PRACTICE_SETTING_CODE_SCHEME)) {
                            rae.setPracticeSettingCode(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(EVENT_CODE_LIST_SCHEME)) {
                            rae.setEventCodeList(getCodes(next));
                        }else if (next.getAttributeValue(new QName("classificationScheme")) != null && next.getAttributeValue(new QName("classificationScheme")).equalsIgnoreCase(TYPE_CODE_SCHEME)) {
                            rae.setTypeCode(getCodes(next));
                        }
                    }
                }
            }
            
            // ExternalIdentifier Info
            Iterator<OMElement> externalIdentifierIterator = bodyRootElement.getChildrenWithName(new QName("ExternalIdentifier"));
            if (externalIdentifierIterator != null && externalIdentifierIterator.hasNext()) {
                while (externalIdentifierIterator.hasNext()) {
                    OMElement next = externalIdentifierIterator.next();
                    if (next != null && next.getFirstElement() != null) {
                        if (next.getAttributeValue(new QName("identificationScheme")) != null && next.getAttributeValue(new QName("identificationScheme")).equalsIgnoreCase(XDSDocumentEntryPatientId_SCHEME)) {
                            rae.setDocumentEntryPatientId(next.getAttributeValue(new QName("value")).trim());
                        }else if (next.getAttributeValue(new QName("identificationScheme")) != null && next.getAttributeValue(new QName("identificationScheme")).equalsIgnoreCase(XDSDocumentEntryUniqueId_SCHEME)) {
                            rae.setDocumentEntryUniqueId(next.getAttributeValue(new QName("value")).trim());
                        }
                    }
                }
            }
            
            rae.setType(0);
            attachmentEntityList.add(rae);
            
            
            
//			if (objectType
//					.equals(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSON_SET_OBJECT)) {
//				this.list.add(id);
//			} else if (objectType
//					.equals(ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_OBJECT)) {
//				this.list.add(id);
//			} else if (objectType
//					.equals(ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_OBJECT)) {
//				this.list.add(id);
//			}
		} else {
			objectRef(bodyRootElement);
		}
	}
	
	@SuppressWarnings("unchecked")
	private ResponseCode getCodes(OMElement element) {
	    String code = element.getAttributeValue(new QName("nodeRepresentation"));
        String scheme = null;
        String display = null;
        Iterator<OMElement> subSlotIterator = element.getChildrenWithName(new QName("Slot"));
        if (subSlotIterator.hasNext()) {
            while (subSlotIterator.hasNext()) {
                OMElement slotNext = subSlotIterator.next();
                if (slotNext != null && slotNext.getFirstElement() != null && slotNext.getFirstElement().getFirstElement() != null)
                    if (slotNext.getAttributeValue(new QName("name")) != null && slotNext.getAttributeValue(new QName("name")).equalsIgnoreCase(CODEING_SCHEME))
                        scheme = slotNext.getFirstElement().getFirstElement().getText();
            }
        }
        
        Iterator<OMElement> subNameIterator = element.getChildrenWithName(new QName("Name"));
        if (subNameIterator.hasNext()) {
            while (subNameIterator.hasNext()) {
                OMElement nameNext = subNameIterator.next();
                if (nameNext != null && nameNext.getFirstElement() != null)
                    if (nameNext.getFirstElement().getAttributeValue(new QName("value")) != null)
                        display = nameNext.getFirstElement().getAttributeValue(new QName("value")).trim();
            }
        }
        
        return new ResponseCode(code, scheme, display);
	}
	
	private void failure(OMElement element){
		logger.info("FAILURE");
		this.setCodeContext(setValue(element,
				"codeContext"));
		this.setErrorCode(setValue(element,
				"errorCode"));
		this.setLocation(setValue(element,
				"location"));
		this.setSeverity(setValue(element,
				"severity"));
	}

//	private void leafClass(){
//		
//	}
	
	private void objectRef(OMElement element){
		/* ObjectRef */
		logger.info("ObjectRef");
		String id = element.getAttributeValue(
				new QName("id")).trim();
		this.list.add(id);
	}
	
	

	private String setValue(OMElement element, String type) {
		try {
			return element.getAttributeValue(new QName(type)).trim();
		} catch (java.lang.NullPointerException e) {
			return "";
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getCodeContext() {
		return codeContext;
	}

	public void setCodeContext(String codeContext) {
		this.codeContext = codeContext;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OMElement getResponse() {
		return this.response;
	}

	public void setResponse(OMElement response) {
		this.response = response;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String toString() {
		logger.trace(getCodeContext());
		logger.trace(getStatus());
		logger.trace(getErrorCode());
		logger.trace(getLocation());
		logger.trace(getSeverity());
		logger.trace(getResponse());
		return getCodeContext() + "\t" + getStatus() + "\t" + getErrorCode()
				+ "\t" + getLocation() + "\t" + getSeverity() + "\t"
				+ getResponse();
	}

	public Set<String> getList() {
		return list;
	}

	public void setList(Set<String> list) {
		this.list = list;
	}

	public void addItemToList(String str) {
		this.list.add(str);
	}

	public List<ResponseAttachmentEntry> getAttachmentEntityList() {
        return attachmentEntityList;
    }

    public void setAttachmentEntityList(List<ResponseAttachmentEntry> attachmentEntityList) {
        this.attachmentEntityList = attachmentEntityList;
    }
    
    public void addItemToAttachmentEntityList(ResponseAttachmentEntry attachmentEntity) {
        this.attachmentEntityList.add(attachmentEntity);
    }

    public boolean clean() {
    	errorCode = "";
    	codeContext = "";
    	status = "";
    	severity = "";
    	location = "";
    	attachmentEntityList = new ArrayList<ResponseAttachmentEntry>();
    	return true;
	}
}
