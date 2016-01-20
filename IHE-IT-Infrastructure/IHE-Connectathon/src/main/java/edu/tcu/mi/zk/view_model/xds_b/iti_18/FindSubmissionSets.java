/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;


import edu.tcu.mi.ihe.utility.xml.XMLPath;
import edu.tcu.mi.zk.model.code.Code;
import edu.tcu.mi.zk.model.code.Codes;
import edu.tcu.mi.zk.model.code.CodesImpl;

/**
 * @author Gaduo
 * 
 */
public class FindSubmissionSets extends StoredQuery {
	public static Logger logger = Logger.getLogger(FindSubmissionSets.class);

	private String patientId;
	private String status;
	private Date submissionTimeFrom;
	private Date submissionTimeTo;
	private String sourceId;
	private String authorPerson;
	private Code contentTypeCode;
	private Codes contentTypeCodeList;

	public FindSubmissionSets() {
		super();
		this.setPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
		this.setStatus("urn:oasis:names:tc:ebxml-regrep:StatusType:Approved");
		// DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// try {
		// this.setSubmissionTimeFrom(dateFormat.parse("20130430"));
		// this.setSubmissionTimeTo(dateFormat.parse("20130501"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		XMLPath codes = new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("codes.xml"));
		setContentTypeCodeList(new CodesImpl(codes, "contentTypeCode"));
	}

	public List<OMElement> getParameters() {
		if (patientId != null) {
			super.list.add(super.addParameter("$XDSSubmissionSetPatientId",
					patientId));
		} else {
			return null;
		}
		if (status != null) {
			super.list.add(super
					.addParameter("$XDSSubmissionSetStatus", status));
		} else {
			return null;
		}
		if (submissionTimeFrom != null && submissionTimeTo != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			super.list.add(super.addParameter(
					"$XDSSubmissionSetSubmissionTimeFrom",
					dateFormat.format(this.getSubmissionTimeFrom())));
			super.list.add(super.addParameter(
					"$XDSSubmissionSetSubmissionTimeTo",
					dateFormat.format(this.getSubmissionTimeTo())));
		}
		if (sourceId != null) {
			super.list.add(super.addParameter("$XDSSubmissionSetSourceId",
					sourceId));
		}
		if (authorPerson != null) {
			super.list.add(super.addParameter("$XDSSubmissionSetAuthorPerson",
					authorPerson));
		}
		if (this.getContentTypeCode() != null) {
			Code code = this.getContentTypeCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSSubmissionSetContentType",
					value));
		}
		return super.list;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmissionTimeFrom() {
		return submissionTimeFrom;
	}

	public void setSubmissionTimeFrom(Date submissionTimeFrom) {
		this.submissionTimeFrom = submissionTimeFrom;
	}

	public Date getSubmissionTimeTo() {
		return submissionTimeTo;
	}

	public void setSubmissionTimeTo(Date submissionTimeTo) {
		this.submissionTimeTo = submissionTimeTo;
	}

	public Code getContentTypeCode() {
		return contentTypeCode;
	}

	public void setContentTypeCode(Code contentTypeCode) {
		this.contentTypeCode = contentTypeCode;
	}

	public String getAuthorPerson() {
		return authorPerson;
	}

	public void setAuthorPerson(String authorPerson) {
		this.authorPerson = authorPerson;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public List<Code> getContentTypeCodeList() {
		return this.contentTypeCodeList.findAll();
	}

	public void setContentTypeCodeList(CodesImpl codesList) {
		this.contentTypeCodeList = codesList;
	}

}
