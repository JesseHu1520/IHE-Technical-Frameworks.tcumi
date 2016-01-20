/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

/**
 * @author Gaduo
 * 
 */
public class GetFoldersForDocument extends StoredQuery {
	public static Logger logger = Logger.getLogger(GetFoldersForDocument.class);

	private String id;
	private String type;
	private String associationTypes;
	private String homeCommunityId;

	public GetFoldersForDocument() {
		super();
		this.setType("EntryUUID");
	}

	public List<OMElement> getParameters() {
		if(homeCommunityId != null){
			super.list.add(super.addParameter(
					"$homeCommunityId", homeCommunityId));
		}
		if (associationTypes != null) {
			super.list.add(super.addParameter("$XDSAssociationStatus",
					associationTypes));
		}
		if (id != null) {
			if (this.type.equals("EntryUUID")) {
				super.list.add(super.addParameter("$XDSDocumentEntryEntryUUID",
						id));
			}
			if (this.type.equals("UniqueId")) {
				super.list.add(super.addParameter("$XDSDocumentEntryUniqueId",
						id));
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

	public String getAssociationTypes() {
		return associationTypes;
	}

	public void setAssociationTypes(String associationTypes) {
		this.associationTypes = associationTypes;
	}

	public String getHomeCommunityId() {
		return homeCommunityId;
	}

	public void setHomeCommunityId(String homeCommunityId) {
		this.homeCommunityId = homeCommunityId;
	}

}
