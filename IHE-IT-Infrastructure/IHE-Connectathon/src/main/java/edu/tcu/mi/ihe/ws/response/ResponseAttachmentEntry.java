/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.tcu.mi.ihe.utility.xml.XMLPath;

/**
 * @author Gaduo
 * 
 */
public class ResponseAttachmentEntry implements Comparable<ResponseAttachmentEntry> {
	public static final int DOCUMENT = 0;
	public static final int FOLDER = 1;

	private String uuid;
	private String title;
	private String description;
	private String content = "";
	private String mimeType;
	private int type;
	
	private ResponseCode contentTypeCode;
    private ResponseCode classCode;
    private ResponseCode confidentialityCode;
    private ResponseCode formatCode;
    private ResponseCode healthcareFacilityTypeCode;
    private ResponseCode practiceSettingCode;
    private ResponseCode eventCodeList;
    private ResponseCode typeCode;
    private ResponseCode folderCodeList;
    
    private String creationTime;
    private String serviceStartTime;
    private String serviceStopTime;
    private String hash;
    private String languageCode;
    private String repositoryUniqueId;
    private String size;
    private String sourcePatientId;
    private String[] sourcePatientInfo = new String[6];
    
    private String authorInstitution;
    private String authorPerson;
    private String authorRole;
    private String authorSpecialty;
    
    private String documentEntryPatientId;
    private String documentEntryUniqueId;

	public ResponseAttachmentEntry(String uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        //genMimeType();
    }
	
	public ResponseAttachmentEntry(String title, String description) {
		this.title = title;
		this.description = description;
		//genMimeType();
	}

	public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public void genMimeType() {
        if (getTitle().matches(".")) {
            XMLPath codes = new XMLPath(getClass().getClassLoader().getResourceAsStream("codes.xml"));
            
            List<ResponseCode> mimeType = new ArrayList<ResponseCode>();
            String expression = "Codes/CodeType[@name='mimeType']/Code";
            NodeList nodeList = codes.QueryNodeList(expression);
            if(nodeList == null)
                return ;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NamedNodeMap attr = node.getAttributes();
                String code = attr.getNamedItem("code").getNodeValue();
                String codingScheme = "mimeType";
                String display = attr.getNamedItem("ext").getNodeValue();
                mimeType.add(new ResponseCode(code, codingScheme, display));
            }
            
            String[] s = getTitle().split(".");
            for (ResponseCode code : mimeType) {
                if (code.getDisplay().equalsIgnoreCase(s[s.length])) {
                    setMimeType(s[s.length]);
                }
            }
        }
    }

    public String toString() {
		return title + "," + description + "," + content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int compareTo(ResponseAttachmentEntry o) {
		return o.content.compareTo(this.content);
	}
	
	public ResponseCode getContentTypeCode() {
        return contentTypeCode;
    }

    public void setContentTypeCode(ResponseCode contentTypeCode) {
        this.contentTypeCode = contentTypeCode;
    }

    public ResponseCode getClassCode() {
        return classCode;
    }

    public void setClassCode(ResponseCode classCode) {
        this.classCode = classCode;
    }

    public ResponseCode getConfidentialityCode() {
        return confidentialityCode;
    }

    public void setConfidentialityCode(ResponseCode confidentialityCode) {
        this.confidentialityCode = confidentialityCode;
    }

    public ResponseCode getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(ResponseCode formatCode) {
        this.formatCode = formatCode;
    }

    public ResponseCode getHealthcareFacilityTypeCode() {
        return healthcareFacilityTypeCode;
    }

    public void setHealthcareFacilityTypeCode(ResponseCode healthcareFacilityTypeCode) {
        this.healthcareFacilityTypeCode = healthcareFacilityTypeCode;
    }

    public ResponseCode getPracticeSettingCode() {
        return practiceSettingCode;
    }

    public void setPracticeSettingCode(ResponseCode practiceSettingCode) {
        this.practiceSettingCode = practiceSettingCode;
    }

    public ResponseCode getEventCodeList() {
        return eventCodeList;
    }

    public void setEventCodeList(ResponseCode eventCodeList) {
        this.eventCodeList = eventCodeList;
    }

    public ResponseCode getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(ResponseCode typeCode) {
        this.typeCode = typeCode;
    }
    public ResponseCode getFolderCodeList() {
        return folderCodeList;
    }

    public void setFolderCodeList(ResponseCode folderCodeList) {
        this.folderCodeList = folderCodeList;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceStopTime() {
        return serviceStopTime;
    }

    public void setServiceStopTime(String serviceStopTime) {
        this.serviceStopTime = serviceStopTime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getRepositoryUniqueId() {
        return repositoryUniqueId;
    }

    public void setRepositoryUniqueId(String repositoryUniqueId) {
        this.repositoryUniqueId = repositoryUniqueId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSourcePatientId() {
        return sourcePatientId;
    }

    public void setSourcePatientId(String sourcePatientId) {
        this.sourcePatientId = sourcePatientId;
    }

    public String[] getSourcePatientInfo() {
        return sourcePatientInfo;
    }

    public void setSourcePatientInfo(String[] sourcePatientInfo) {
        this.sourcePatientInfo = sourcePatientInfo;
    }

    public String getAuthorInstitution() {
        return authorInstitution;
    }

    public void setAuthorInstitution(String authorInstitution) {
        this.authorInstitution = authorInstitution;
    }

    public String getAuthorPerson() {
        return authorPerson;
    }

    public void setAuthorPerson(String authorPerson) {
        this.authorPerson = authorPerson;
    }

    public String getAuthorRole() {
        return authorRole;
    }

    public void setAuthorRole(String authorRole) {
        this.authorRole = authorRole;
    }

    public String getAuthorSpecialty() {
        return authorSpecialty;
    }

    public void setAuthorSpecialty(String authorSpecialty) {
        this.authorSpecialty = authorSpecialty;
    }

    public String getDocumentEntryPatientId() {
        return documentEntryPatientId;
    }

    public void setDocumentEntryPatientId(String documentEntryPatientId) {
        this.documentEntryPatientId = documentEntryPatientId;
    }

    public String getDocumentEntryUniqueId() {
        return documentEntryUniqueId;
    }

    public void setDocumentEntryUniqueId(String documentEntryUniqueId) {
        this.documentEntryUniqueId = documentEntryUniqueId;
    }

}
