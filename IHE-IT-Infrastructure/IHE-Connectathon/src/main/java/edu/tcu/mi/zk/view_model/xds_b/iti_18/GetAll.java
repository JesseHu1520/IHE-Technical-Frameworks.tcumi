/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;


import edu.tcu.mi.ihe.utility.xml.XMLPath;
import edu.tcu.mi.zk.model.code.Code;
import edu.tcu.mi.zk.model.code.Codes;
import edu.tcu.mi.zk.model.code.CodesImpl;

/**
 * @author Gaduo
 */
public class GetAll extends StoredQuery {
	public static Logger logger = Logger.getLogger(GetAll.class);

	private String patientId;
	private String homeCommunityId;
	private String documentEntryStatus;
	private String submissionSetStatus;
	private String folderStatus;
	private Code confidentialityCode;
	private Code formatCode;

	private Codes confidentialityCodeList;
	private Codes formatCodeList;

	public GetAll() {
		super();
		XMLPath codes = new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("codes.xml"));
		setConfidentialityCodeList(new CodesImpl(codes, "confidentialityCode"));
		setFormatCodeList(new CodesImpl(codes, "formatCode"));
	}

	public List<OMElement> getParameters() {
		if (patientId != null) {
			super.list.add(super.addParameter("$patientId", patientId));
		} else {
			return null;
		}
		if (documentEntryStatus != null) {
			super.list.add(super.addParameter("$XDSDocumentEntryStatus",
					documentEntryStatus));
		} else {
			return null;
		}
		if (submissionSetStatus != null) {
			super.list.add(super.addParameter("$XDSSubmissionSetStatus",
					submissionSetStatus));
		} else {
			return null;
		}
		if (folderStatus != null) {
			super.list
					.add(super.addParameter("$XDSFolderStatus", folderStatus));
		} else {
			return null;
		}
		if (getFormatCode() != null) {
			Code code = this.getFormatCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSDocumentEntryFormatCode",
					value));
		}
		if (getConfidentialityCode() != null) {
			Code code = this.getConfidentialityCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter(
					"$XDSDocumentEntryConfidentialityCode", value));
		}
		if(homeCommunityId != null){
			super.list.add(super.addParameter(
					"$homeCommunityId", homeCommunityId));
		}
		return super.list;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDocumentEntryStatus() {
		return this.documentEntryStatus;
	}

	public void setDocumentEntryStatus(String documentEntryStatus) {
		this.documentEntryStatus = documentEntryStatus;
	}

	public String getSubmissionSetStatus() {
		return this.submissionSetStatus;
	}

	public void setSubmissionSetStatus(String submissionSetStatus) {
		this.submissionSetStatus = submissionSetStatus;
	}

	public String getFolderStatus() {
		return this.folderStatus;
	}

	public void setFolderStatus(String folderStatus) {
		this.folderStatus = folderStatus;
	}

	public Code getConfidentialityCode() {
		return this.confidentialityCode;
	}

	public void setConfidentialityCode(Code confidentialityCode) {
		this.confidentialityCode = confidentialityCode;
	}

	public Code getFormatCode() {
		return this.formatCode;
	}

	public void setFormatCode(Code formatCode) {
		this.formatCode = formatCode;
	}

	public List<Code> getConfidentialityCodeList() {
		return this.confidentialityCodeList.findAll();
	}

	public void setConfidentialityCodeList(CodesImpl confidentialityCodeList) {
		this.confidentialityCodeList = confidentialityCodeList;
	}

	public List<Code> getFormatCodeList() {
		return this.formatCodeList.findAll();
	}

	public void setFormatCodeList(CodesImpl formatCodeList) {
		this.formatCodeList = formatCodeList;
	}

	public String getHomeCommunityId() {
		return homeCommunityId;
	}

	public void setHomeCommunityId(String homeCommunityId) {
		this.homeCommunityId = homeCommunityId;
	}

}
