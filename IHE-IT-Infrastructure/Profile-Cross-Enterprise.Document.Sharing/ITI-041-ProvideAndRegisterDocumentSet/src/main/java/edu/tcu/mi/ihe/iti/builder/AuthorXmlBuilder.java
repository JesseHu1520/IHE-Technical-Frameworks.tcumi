package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Setter;

public class AuthorXmlBuilder extends EntityXmlBuilder {
	@Setter
	private Author author;
	
	public AuthorXmlBuilder(){
		this.objectType = ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_AUTHOR;
	}
	
	@Override
	public OMElement getMessageFromXML() {
		MetadataXmlBuilder.objectRef.add(objectType);
		/* classification */
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement classification = axiom.createOMElement(EbXML.Classification, Namespace.RIM3);
		classification.addAttribute("id", generateUUID(), null);
		classification.addAttribute("classificationScheme", objectType, null);
		classification.addAttribute("classifiedObject", author.getParentId(), null);
		classification.addAttribute("nodeRepresentation", "", null);

		for(String authorPerson : author.getAuthorPersons()){
			if(authorPerson != null){
				OMElement slot = generateSlot("authorPerson", new String[]{authorPerson});
				classification.addChild(slot);
			}	
		}
		for(String authorInstitution : author.getAuthorInstitutions()){
			if(authorInstitution != null){
				OMElement slot = generateSlot("authorInstitution", new String[]{authorInstitution});
				classification.addChild(slot);
			}
		}
		for(String authorRole : author.getAuthorRoles()){
			if(authorRole != null){
				OMElement slot = generateSlot("authorRole", new String[]{authorRole});
				classification.addChild(slot);
			}
		}
		for(String authorSpecialty : author.getAuthorSpecialtys()){
			if(authorSpecialty != null){
				OMElement slot = generateSlot("authorSpecialty", new String[]{authorSpecialty});
				classification.addChild(slot);
			}
		}
		
		return classification;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getId() {
		return author.getId();
	}
}
