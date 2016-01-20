/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.utility.xml.XMLPath;
import edu.tcu.mi.zk.model.code.Code;
import edu.tcu.mi.zk.model.code.Codes;
import edu.tcu.mi.zk.model.code.CodesImpl;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 * 
 */
public class FindDocuments extends StoredQuery  {

	@Getter @Setter
	private String patientId;
	@Getter @Setter
	private String status;
	@Getter @Setter
	private Date creationTimeFrom;
	@Getter @Setter
	private Date creationTimeTo;
	@Getter @Setter
	private Date serviceStartTimeFrom;
	@Getter @Setter
	private Date serviceStartTimeTo;
	@Getter @Setter
	private Date serviceStopTimeFrom;
	@Getter @Setter
	private Date serviceStopTimeTo;

	@Getter @Setter
	private Code classCode;
	@Getter @Setter
	private Code confidentialityCode;
	@Getter @Setter
	private Code formatCode;
	@Getter @Setter
	private Code healthcareFacilityTypeCode;
	@Getter @Setter
	private Code practiceSettingCode;
	@Getter @Setter
	private Code typeCode;
	@Getter @Setter
	private Code eventCodeList;

	@Setter
	private Codes classCodeList;
	@Setter
	private CodesImpl confidentialityCodeList;
	@Setter
	private CodesImpl formatCodeList;
	@Setter
	private CodesImpl healthcareFacilityTypeCodeList;
	@Setter
	private CodesImpl practiceSettingCodeList;
	@Setter
	private CodesImpl typeCodeList;
	@Setter
	private CodesImpl eventCodeListList;

	public FindDocuments() {
		super();
		this.setPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
		this.setStatus("urn:oasis:names:tc:ebxml-regrep:StatusType:Approved");

		XMLPath codes = new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("codes.xml"));
		setClassCodeList(new CodesImpl(codes, "classCode"));
		setConfidentialityCodeList(new CodesImpl(codes, "confidentialityCode"));
		setFormatCodeList(new CodesImpl(codes, "formatCode"));
		setHealthcareFacilityTypeCodeList(new CodesImpl(codes,
				"healthcareFacilityTypeCode"));
		setPracticeSettingCodeList(new CodesImpl(codes, "practiceSettingCode"));
		setTypeCodeList(new CodesImpl(codes, "typeCode"));
		setEventCodeListList(new CodesImpl(codes, "eventCodeList"));
	}

	public List<OMElement> getParameters() {
		if (this.getPatientId() != null) {
			super.list.add(super.addParameter("$XDSDocumentEntryPatientId",
					this.getPatientId()));
		} else {
			return null;
		}
		if (this.getStatus() != null) {
			super.list.add(super.addParameter("$XDSDocumentEntryStatus",
					this.getStatus()));
		} else {
			return null;
		}
		if (creationTimeFrom != null && creationTimeTo != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			super.list.add(super.addParameter(
					"$XDSDocumentEntryCreationTimeFrom",
					dateFormat.format(this.getCreationTimeFrom())));
			super.list.add(super.addParameter(
					"$XDSDocumentEntryCreationTimeTo",
					dateFormat.format(this.getCreationTimeTo())));
		}
		if (serviceStartTimeFrom != null && serviceStartTimeTo != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			super.list.add(super.addParameter(
					"$XDSDocumentEntryServiceStartTimeFrom",
					dateFormat.format(this.getServiceStartTimeFrom())));
			super.list.add(super.addParameter(
					"$XDSDocumentEntryServiceStartTimeTo",
					dateFormat.format(this.getServiceStartTimeTo())));
		}
		if (serviceStopTimeFrom != null && serviceStopTimeTo != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			super.list.add(super.addParameter(
					"$XDSDocumentEntryServiceStopTimeFrom",
					dateFormat.format(this.getServiceStopTimeFrom())));
			super.list.add(super.addParameter(
					"$XDSDocumentEntryServiceStopTimeTo",
					dateFormat.format(this.getServiceStopTimeTo())));
		}
		if (this.getClassCode() != null) {
			Code code = this.getClassCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSDocumentEntryClassCode",
					value));
		}
		if (this.getTypeCode() != null) {
			Code code = this.getTypeCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSDocumentEntryTypeCode",
					value));
		}//
		if (this.getHealthcareFacilityTypeCode() != null) {
			Code code = this.getHealthcareFacilityTypeCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter(
					"$XDSDocumentEntryHealthcareFacilityTypeCode", value));
		}
		if (this.getPracticeSettingCode() != null) {
			Code code = this.getPracticeSettingCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter(
					"$XDSDocumentEntryPracticeSettingCode", value));
		}
		if (this.getEventCodeList() != null) {
			Code code = this.getEventCodeList();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSDocumentEntryEventCodeList",
					value));
		}
		if (this.getConfidentialityCode() != null) {
			Code code = this.getConfidentialityCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter(
					"$XDSDocumentEntryConfidentialityCode", value));
		}
		if (this.getFormatCode() != null) {
			Code code = this.getFormatCode();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSDocumentEntryFormatCode",
					value));
		}
		return super.list;
	}

	// ----

	public List<Code> getClassCodeList() {
		return this.classCodeList.findAll();
	}

	public List<Code> getConfidentialityCodeList() {
		return this.confidentialityCodeList.findAll();
	}

	public List<Code> getFormatCodeList() {
		return this.formatCodeList.findAll();
	}

	public List<Code> getHealthcareFacilityTypeCodeList() {
		return this.healthcareFacilityTypeCodeList.findAll();
	}

	public List<Code> getPracticeSettingCodeList() {
		return this.practiceSettingCodeList.findAll();
	}

	public List<Code> getEventCodeListList() {
		return eventCodeListList.findAll();
	}

	public List<Code> getTypeCodeList() {
		return typeCodeList.findAll();
	}

}
