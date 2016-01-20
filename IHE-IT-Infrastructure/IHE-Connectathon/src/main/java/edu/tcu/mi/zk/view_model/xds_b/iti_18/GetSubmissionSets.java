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
public class GetSubmissionSets extends StoredQuery {
	public static Logger logger = Logger.getLogger(GetSubmissionSets.class);

	private String UUID = "";
	private String homeCommunityId;
	
	public GetSubmissionSets() {
		super();
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		this.UUID = uUID;
	}

	public List<OMElement> getParameters() {
		if(homeCommunityId != null){
			super.list.add(super.addParameter(
					"$homeCommunityId", homeCommunityId));
		}
		super.list.add(super.addParameter("$uuid", UUID));
		return super.list;
	}

	public String getHomeCommunityId() {
		return homeCommunityId;
	}

	public void setHomeCommunityId(String homeCommunityId) {
		this.homeCommunityId = homeCommunityId;
	}
}
