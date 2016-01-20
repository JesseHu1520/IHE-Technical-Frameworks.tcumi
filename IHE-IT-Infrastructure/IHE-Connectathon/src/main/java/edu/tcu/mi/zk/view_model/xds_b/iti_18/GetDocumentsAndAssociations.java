/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

/**
 * @author Gaduo
 */
public class GetDocumentsAndAssociations extends StoredQuery {
	public static Logger logger = Logger
			.getLogger(GetDocumentsAndAssociations.class);
	private String id;
	private String type;
	private String homeCommunityId;

	public GetDocumentsAndAssociations() {
		super();
		this.setType("EntryUUID");
		this.setId("urn:uuid:a5dbc44d-de63-4bb1-9352-3f43d84ba859");
	}

	public List<OMElement> getParameters() {
		if(homeCommunityId != null){
			super.list.add(super.addParameter(
					"$homeCommunityId", homeCommunityId));
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
			logger.trace(type + "\t" + id);
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

	public String getHomeCommunityId() {
		return homeCommunityId;
	}

	public void setHomeCommunityId(String homeCommunityId) {
		this.homeCommunityId = homeCommunityId;
	}

}
