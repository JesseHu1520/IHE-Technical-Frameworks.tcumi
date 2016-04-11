package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;
import lombok.Setter;

public class RetrieveBuilder extends MessageBuilder {

	@Getter @Setter
	private RetrieveModel retrieveModel;
	
	public RetrieveBuilder(RetrieveModel retrieveModel){
		this.retrieveModel = retrieveModel;
		this.setEndpoint(retrieveModel.getEndpoint());
	}
	
	@Override
	public OMElement getMessageFromXML() {
		if(!validate()) return null;
		
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement retrieveDocumentSetRequest = axiom.createOMElement("RetrieveDocumentSetRequest", "urn:ihe:iti:xds-b:2007", "");
		for(String documentId : retrieveModel.getDocumentIds()) {
			OMElement documentRequest = axiom.createOMElement("DocumentRequest", "urn:ihe:iti:xds-b:2007", "");

			OMElement _repositoryUniqueId = axiom.createOMElement("RepositoryUniqueId", "urn:ihe:iti:xds-b:2007", "");
			_repositoryUniqueId.setText(retrieveModel.getRepositoryUniqueId());
			documentRequest.addChild(_repositoryUniqueId);

			String homeCommunityId = retrieveModel.getHomeCommunityId();
			if (homeCommunityId != null && !retrieveModel.getHomeCommunityId().equals("")) {
				OMElement _homeCommunityId = axiom.createOMElement("HomeCommunityId", "urn:ihe:iti:xds-b:2007", "");
				_homeCommunityId.setText(homeCommunityId);
				documentRequest.addChild(_homeCommunityId);
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
		if(retrieveModel == null) return false;
		if(retrieveModel.getDocumentIds().isEmpty()) return false;
		if(retrieveModel.getRepositoryUniqueId() == null || retrieveModel.getRepositoryUniqueId().length() == 0) return false;
		return true;
	}

	@Deprecated
	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}
}
