package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.model.Association;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Setter;

public class AssociationXmlBuilder extends EntityXmlBuilder {
	@Setter
	private Association association;
	

	@Override
	public OMElement getMessageFromXML() {
		if(!validate()){
			return null;
		}
		AxiomUtil axiom = AxiomUtil.getInstance();
		
		OMElement root = axiom.createOMElement(EbXML.Association, Namespace.RIM3);
		root.addAttribute("id", this.getId(), null);
		root.addAttribute("objectType", ProvideAndRegistryDocumentSet_B_UUIDs.ASSOCIATION, null);
		
		root.addAttribute("sourceObject", association.getSourceObject(), null);
		root.addAttribute("targetObject", association.getTargetObject(), null);
		root.addAttribute("associationType", association.getAssociation(), null);

		if(association.getNote() != null){
			OMElement slot = generateSlot("SubmissionSetStatus", new String[] { association.getNote() });
			root.addChild(slot);
		}
		return root;
	}

	@Override
	protected boolean validate() {
		if(getId() == null){ 
			logger.info("id is null.");
			return false;		
		}
		if(association.getSourceObject() == null){ 
			logger.info("sourceObject is null.");
			return false;		
		}
		if(association.getTargetObject() == null){ 
			logger.info("targetObject is null.");
			return false;		
		}
		if(association.getAssociation() == null) { 
			logger.info("associationType is null.");
			return false;		
		}
		return true;
	}

	@Override
	protected String getId() {
		return association.getId();
	}

}
