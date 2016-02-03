package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.constants.SubmissionSetConstants;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Setter;

public class SubmissionSetXmlBuilder extends EntityXmlBuilder {
	@Setter
	private SubmissionSet submissionSet; 

	public SubmissionSetXmlBuilder(String sourceId, String ip){
		super(sourceId, ip);
		super.objectType = ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSON_SET_OBJECT;
	}
	
	@Override
	public OMElement getMessageFromXML() {
		if(!validate()){
			logger.info("validate error");
			return null;
		}
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement root = axiom.createOMElement(EbXML.RegistryPackage, Namespace.RIM3);
		root.addAttribute("id", this.getId(), null);
		root.addAttribute("objectType", objectType, null);
		MetadataXmlBuilder.objectRef.add(objectType);
		// --submissionTime
		String submissionTime = generateTimeStamp();
		if (submissionTime != null) {
			OMElement slot = this.generateSlot(SubmissionSetConstants.SUBMISSION_TIME, new String[] { submissionTime });
			root.addChild(slot);
		}
		// ---------------------Author
		AuthorXmlBuilder authorBuilder = new AuthorXmlBuilder(sourceId, ip);
		for(Author author : submissionSet.getAuthors()){
			author.setParentId(submissionSet.getId());
			authorBuilder.setAuthor(author);
			OMElement element = authorBuilder.getMessageFromXML();
			if(element != null)
				root.addChild(element);
		}
		// ---ContentTypeCode
		if (submissionSet.getContentTypeCode() != null) {
			OMElement element = generateClassification("contentTypeCode", submissionSet.getContentTypeCode().trim(), SubmissionSetConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_CONTENT_TYPE_CODE);
			if(element != null)
				root.addChild(element);
		}
		
		// ---------------------ExternalIdentifier
		OMElement name;
		name = generateNameOrDescription(SubmissionSetConstants.PATIENT_ID, EbXML.Name);
		Patient patient = submissionSet.getPatient();
		OMElement externalIdentifier01 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_PATIENT_IDENTIFICATION_SCHEME, this.getId(), patient.getId(), name);
		root.addChild(externalIdentifier01);
		
		String uniqueId = generateUniqueId();
		name = generateNameOrDescription(SubmissionSetConstants.UNIQUE_ID, EbXML.Name);
		OMElement externalIdentifier02 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_UNIQUE_IDENTIFICATION_SCHEME, this.getId(), uniqueId, name);
		root.addChild(externalIdentifier02);
		
		name = generateNameOrDescription(SubmissionSetConstants.SOURCE_ID, EbXML.Name);
		OMElement externalIdentifier03 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_SOURCE_IDENTIFICATION_SCHEME, this.getId(), sourceId, name);
		root.addChild(externalIdentifier03);
		return root;
	}

	@Override
	protected boolean validate() {
		if(sourceId == null || sourceId.equals(""))
			return false;
		return true;
	}
	
	@Override
	protected String getId() {
		return submissionSet.getId();
	}

}
