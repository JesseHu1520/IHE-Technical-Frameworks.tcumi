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
public class GetSubmissionsetAndContents extends StoredQuery {
	public static Logger logger = Logger.getLogger(GetSubmissionsetAndContents.class);

	private String id;
	private String type;
	private String homeCommunityId;
	private Code formatCode;
	private Code confidentialityCode;

	private Codes formatCodeList;
	private Codes confidentialityCodeList;

	public GetSubmissionsetAndContents() {
		super();
		this.setType("EntryUUID");
		this.setId("urn:uuid:a5dbc44d-de63-4bb1-9352-3f43d84ba859");

		XMLPath codes = new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("codes.xml"));
		setConfidentialityCodeList(new CodesImpl(codes, "confidentialityCode"));
		setFormatCodeList(new CodesImpl(codes, "formatCode"));
	}

	public List<OMElement> getParameters() {
		if(homeCommunityId != null){
			super.list.add(super.addParameter(
					"$homeCommunityId", homeCommunityId));
		}
		if (id != null) {
			if (this.type.equals("EntryUUID")) {
				super.list.add(super.addParameter("$XDSSubmissionSetEntryUUID",
						id));
			}
			if (this.type.equals("UniqueId")) {
				super.list.add(super.addParameter("$XDSSubmissionSetUniqueId",
						id));
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
				super.list.add(super.addParameter(
						"$XDSDocumentEntryFormatCode", value));
			}
			return super.list;
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Code getFormatCode() {
		return this.formatCode;
	}

	public void setFormatCode(Code formatCode) {
		this.formatCode = formatCode;
	}

	public Code getConfidentialityCode() {
		return this.confidentialityCode;
	}

	public void setConfidentialityCode(Code confidentialityCode) {
		this.confidentialityCode = confidentialityCode;
	}

	public List<Code> getConfidentialityCodeList() {
		return confidentialityCodeList.findAll();
	}

	public void setConfidentialityCodeList(CodesImpl confidentialityCodeList) {
		this.confidentialityCodeList = confidentialityCodeList;
	}

	public List<Code> getFormatCodeList() {
		return formatCodeList.findAll();
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
