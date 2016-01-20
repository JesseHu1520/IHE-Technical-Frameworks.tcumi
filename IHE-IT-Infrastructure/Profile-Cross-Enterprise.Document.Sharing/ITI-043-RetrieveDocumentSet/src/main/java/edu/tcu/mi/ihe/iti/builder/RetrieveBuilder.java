package edu.tcu.mi.ihe.iti.builder;

import java.util.Set;

import org.apache.axiom.om.OMElement;

import com.google.common.collect.Sets;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;
import lombok.Setter;

public class RetrieveBuilder extends MessageBuilder {
	@Getter  @Setter
	private String repositoryUniqueId;
	@Getter  @Setter
	private String homeCommunityId;
	@Getter  @Setter
	private Set<String> documentIds;
	
	public void addDocumentId(String documentId){
		if(documentIds == null)  documentIds = Sets.newTreeSet();
		documentIds.add(documentId);
	}
	
	@Override
	public OMElement getMessageFromXML() {
		if(!validate()) return null;
		
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement retrieveDocumentSetRequest = axiom.createOMElement("RetrieveDocumentSetRequest", "urn:ihe:iti:xds-b:2007", "");
		for(String documentId : documentIds) {
			OMElement documentRequest = axiom.createOMElement("DocumentRequest", "urn:ihe:iti:xds-b:2007", "");

			OMElement _repositoryUniqueId = axiom.createOMElement("RepositoryUniqueId", "urn:ihe:iti:xds-b:2007", "");
			_repositoryUniqueId.setText(repositoryUniqueId);
			documentRequest.addChild(_repositoryUniqueId);

			if (this.homeCommunityId != null && !this.homeCommunityId.equals("")) {
				OMElement homeCommunityId = axiom.createOMElement("HomeCommunityId", "urn:ihe:iti:xds-b:2007", "");
				homeCommunityId.setText(this.homeCommunityId);
				documentRequest.addChild(homeCommunityId);
			}
			
			OMElement documentUniqueId = axiom.createOMElement("DocumentUniqueId", "urn:ihe:iti:xds-b:2007", "");
			documentUniqueId.setText(documentId);
			documentRequest.addChild(documentUniqueId);
			
			retrieveDocumentSetRequest.addChild(documentRequest);
		}
		return retrieveDocumentSetRequest;
	}

	@Override
	protected boolean validate() {
		if(documentIds.isEmpty()) return false;
		if(repositoryUniqueId == null || repositoryUniqueId.length() == 0) return false;
		return true;
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}
}
