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

/**
 * @author Gaduo
 * 
 */
public class FindFolders extends StoredQuery {

	private String patientId;
	private String status;
	private Date lastUpdateTimeFrom;
	private Date lastUpdateTimeTo;
	private Code folderCodeList;
	private Codes folderCodeListList;

	public FindFolders() {
		super();
		this.setPatientId("c6002e5679534ea^^^&1.3.6.1.4.1.21367.2005.3.7&ISO");
		this.setStatus("urn:oasis:names:tc:ebxml-regrep:StatusType:Approved");
		XMLPath codes = new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("codes.xml"));
		setFolderCodeListList(new CodesImpl(codes, "folderCodeList"));
	}

	public List<OMElement> getParameters() {
		if (patientId != null) {
			super.list
					.add(super.addParameter("$XDSFolderPatientId", patientId));
		} else {
			return null;
		}
		if (status != null) {
			super.list.add(super.addParameter("$XDSFolderStatus", status));
		} else {
			return null;
		}
		if (lastUpdateTimeFrom != null && lastUpdateTimeTo != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			super.list.add(super.addParameter("$XDSFolderLastUpdateTimeFrom",
					dateFormat.format(this.getLastUpdateTimeFrom())));
			super.list.add(super.addParameter("$XDSFolderLastUpdateTimeTo",
					dateFormat.format(this.getLastUpdateTimeTo())));
		}
		if (getFolderCodeList() != null) {
			Code code = this.getFolderCodeList();
			String value = code.getCode() + "^^" + code.getCodingScheme();
			super.list.add(super.addParameter("$XDSFolderCodeList", value));
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

	public Date getLastUpdateTimeFrom() {
		return lastUpdateTimeFrom;
	}

	public void setLastUpdateTimeFrom(Date lastUpdateTimeFrom) {
		this.lastUpdateTimeFrom = lastUpdateTimeFrom;
	}

	public Date getLastUpdateTimeTo() {
		return lastUpdateTimeTo;
	}

	public void setLastUpdateTimeTo(Date lastUpdateTimeTo) {
		this.lastUpdateTimeTo = lastUpdateTimeTo;
	}

	public Code getFolderCodeList() {
		return folderCodeList;
	}

	public void setFolderCodeList(Code folderCodeList) {
		this.folderCodeList = folderCodeList;
	}

	public List<Code> getFolderCodeListList() {
		return folderCodeListList.findAll();
	}

	public void setFolderCodeListList(CodesImpl folderCodeListList) {
		this.folderCodeListList = folderCodeListList;
	}

}
