package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.tcu.mi.ihe.constants.DocumentEntryConstants;
import edu.tcu.mi.ihe.constants.DocumentRelationshipsConstants;
import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.model.Association;
import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Setter;

public class DocumentEntryXmlBuilder extends EntityXmlBuilder {

	@Setter
	private DocumentEntry documentEntry;
	
	private Association lifecycle;

	public DocumentEntryXmlBuilder(String sourceId, String ip) {
		super(sourceId, ip);
		objectType = ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_OBJECT;
	}

	public OMElement generateRelationship() {
//			// Relationship APND 11977
		if(documentEntry.getAppendDocumentId() != null) 
			createLifecycle(documentEntry.getAppendDocumentId(), DocumentRelationshipsConstants.APND);
//			// Relationship RPLC 11974
		if(documentEntry.getReplaceDocumentId() != null) 
			createLifecycle(documentEntry.getReplaceDocumentId(), DocumentRelationshipsConstants.RPLC);
//			// Relationship XFRM 11975
		if(documentEntry.getTransformDocumentId() != null) 
			createLifecycle(documentEntry.getTransformDocumentId(), DocumentRelationshipsConstants.XFRM);
//			// Relationship XFRM_RPLC 11976
		if(documentEntry.getTransformAndReplaceDocumentId() != null) 
			createLifecycle(documentEntry.getTransformAndReplaceDocumentId(), DocumentRelationshipsConstants.XFRM_RPLC);
//			// Relationship Signs 
		if(documentEntry.getSignatureDocumentId() != null) 
			createLifecycle(documentEntry.getSignatureDocumentId(), DocumentRelationshipsConstants.Signs);
		AssociationXmlBuilder associationBuilder = new AssociationXmlBuilder(sourceId, ip);
		associationBuilder.setAssociation(lifecycle);
		if(lifecycle != null)
			return associationBuilder.getMessageFromXML();
		return null;
	}
	
	private void createLifecycle(String docId, String associationType){
		lifecycle = new Association();
		lifecycle.setSourceObject(getId());
		lifecycle.setTargetObject(docId);
		lifecycle.setAssociation(associationType);
	}
	
	
	@Override
	public OMElement getMessageFromXML() {
		if(!validate() || documentEntry.isExisting()){
			return null;
		}
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement root = axiom.createOMElement(EbXML.ExtrinsicObject, Namespace.RIM3);
		root.addAttribute("id", this.getId(), null);
		root.addAttribute("objectType", objectType, null);
		MetadataXmlBuilder.objectRef.add(objectType);
		
		String mimeType = extractMimeType(documentEntry.getTitle());
		if(mimeType != null)
			root.addAttribute("mimeType", mimeType, null);// MimeType
		root.addAttribute("status", Namespace.APPROVED.getNamespace(), null);

		if(documentEntry.getCreationTime() == null)
			documentEntry.setCreationTime(generateTimeStamp());
		if(documentEntry.getCreationTime() != null) {
			OMElement slot = this.generateSlot(DocumentEntryConstants.CREATION_TIME, new String[] { documentEntry.getCreationTime() });
			logger.debug("\n" + slot);
			root.addChild(slot);
		}

		String serviceStartTime = generateTimeStamp();
		if (serviceStartTime != null) {
			OMElement slot = this.generateSlot(DocumentEntryConstants.SERVICE_START_TIME, new String[] { serviceStartTime });
			logger.debug("\n" + slot);
			root.addChild(slot);
		}
		String serviceStopTime = generateTimeStamp();
		if (serviceStopTime != null) {
			OMElement slot = this.generateSlot(DocumentEntryConstants.SERVICE_STOP_TIME, new String[] { serviceStopTime });
			logger.debug("\n" + slot);
			root.addChild(slot);
		}
		String languageCode = "zh-tw";
		if (languageCode != null) {
			OMElement slot = this.generateSlot(DocumentEntryConstants.LANGUAGE_CODE, new String[] { languageCode });
			logger.debug("\n" + slot);
			root.addChild(slot);
		}
		Patient patient = documentEntry.getPatient();
		if (patient.getId() != null) {
			OMElement slot = this.generateSlot(DocumentEntryConstants.SOURCE_PATIENT_ID, new String[] { patient.getId() });
			logger.debug("\n" + slot);
			root.addChild(slot);
		}
		
		PatientXmlBuilder patientBuilder = new PatientXmlBuilder();
		patientBuilder.setPatient(patient);
		if(patient != null){
			OMElement pInfo = patientBuilder.getMessageFromXML();
			logger.debug("\n" + pInfo);
			root.addChild(pInfo);
		}

		if (documentEntry.getContent() != null && !documentEntry.getContent().contains("http")) {
			if (documentEntry.getHash() != null) {
				OMElement slot = this.generateSlot(DocumentEntryConstants.HASH, new String[] { documentEntry.getHash().toString() });
				logger.debug("\n" + slot);
				root.addChild(slot);
			}
			if (documentEntry.getSize() > 0) {
				OMElement slot = this.generateSlot(DocumentEntryConstants.SIZE, new String[] { documentEntry.getSize() + "" });
				logger.debug("\n" + slot);
				root.addChild(slot);
			}
		} else {
			String[] url = extractURL(documentEntry.getContent());
			if (url != null) {
				OMElement slot = this.generateSlot(DocumentEntryConstants.URI, url);
				logger.debug("\n" + slot);
				root.addChild(slot);
			}
		}
		// ---------------------Main
		if (documentEntry.getTitle() != null) {
			OMElement name = this.generateNameOrDescription(documentEntry.getTitle(), EbXML.Name);// Title
			logger.debug("\n" + name);
			root.addChild(name);
		}
		if (documentEntry.getDescription() != null) {
			OMElement name = this.generateNameOrDescription(documentEntry.getDescription(), EbXML.Description);
			logger.debug("\n" + name);
			root.addChild(name);
		}
		// ---------------------Author
		AuthorXmlBuilder builder = new AuthorXmlBuilder(sourceId, ip);
		for(Author author : documentEntry.getAuthors() ){
			author.setParentId(documentEntry.getId());
			builder.setAuthor(author);
			OMElement classification = builder.getMessageFromXML();
			logger.debug("\n" + classification);
			root.addChild(classification);
		}
		// ---------------------Classification

		// ---ConfidentialityCode
		for(String value : documentEntry.getConfidentialityCode()){
			if (value != null) {
				value = value.trim();
				OMElement classification = generateClassification("confidentialityCode", value, DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_CONFIDENTIALITY_CODE);
				if(classification != null)
					root.addChild(classification);
			}
		}
		// ---EventCodeList
		for(String value : documentEntry.getEventCodeList()){
			if (value != null) {
				value = value.trim();
				OMElement classification = generateClassification("eventCodeList", value, DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_EVENT_CODE);
				if(classification != null)
					root.addChild(classification);
			}
		}
		// ---ClassCode
		if (documentEntry.getClassCode() != null) {
			OMElement classification = generateClassification("classCode", documentEntry.getClassCode().trim(), DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_CLASS_CODE);
			if(classification != null)
				root.addChild(classification);
		}
		
		// ---FormatCode
		if (documentEntry.getFormatCode() != null) {
			OMElement classification = generateClassification("formatCode", documentEntry.getFormatCode().trim(), DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_FORMAT_CODE);
			if(classification != null)
				root.addChild(classification);
		}
		// ---HealthcareFacilityTypeCode
		if (documentEntry.getHealthcareFacilityTypeCode() != null) {
			OMElement classification = generateClassification("healthcareFacilityTypeCode", documentEntry.getHealthcareFacilityTypeCode().trim(), DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_HEALTH_CARE_FACILITY_CODE);
			if(classification != null)
				root.addChild(classification);
		}
		// ---PracticeSettingCode
		if (documentEntry.getPracticeSettingCode() != null) {
			OMElement classification = generateClassification("practiceSettingCode", documentEntry.getPracticeSettingCode().trim(), DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_PRACTICE_SETTING_CODE);
			if(classification != null)
				root.addChild(classification);
		}
		// ---TypeCode
		if (documentEntry.getTypeCode() != null) {
			OMElement classification = generateClassification("typeCode", documentEntry.getTypeCode().trim(), DocumentEntryConstants.CODING_SCHEME, this.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_TYPE_CODE);
			if(classification != null)
				root.addChild(classification);
		}

		// ---------------------ExternalIdentifier
		// ---PATIENT_ID
		OMElement name;
		name = generateNameOrDescription(DocumentEntryConstants.PATIENT_ID, EbXML.Name);
		OMElement externalIdentifier01 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_PATIENT_IDENTIFICATION_SCHEME, getId(), patient.getId(), name);
		root.addChild(externalIdentifier01);
		// ---UNIQUE_ID
		String uniqueId = generateUniqueId();
		name = generateNameOrDescription(DocumentEntryConstants.UNIQUE_ID, EbXML.Name);
		OMElement externalIdentifier02 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_UNIQUE_IDENTIFICATION_SCHEME, getId(), uniqueId, name);
		root.addChild(externalIdentifier02);
		
		logger.debug("\n" + root);
		return root;
	}

	@Override
	protected boolean validate() {
		if(documentEntry.getTitle() == null && !documentEntry.isExisting()) {
			logger.warn("\n" + "no title");
			return false;
		}
		int flag01 = documentEntry.getAppendDocumentId() != null? 1 : 0;
		int flag02 = documentEntry.getReplaceDocumentId() != null? 1 : 0;
		int flag03 = documentEntry.getTransformDocumentId() != null? 1 : 0;
		int flag04 = documentEntry.getTransformAndReplaceDocumentId() != null? 1 : 0;
		int flag05 = documentEntry.getSignatureDocumentId() != null? 1 : 0;
		int flag = flag01 + flag02 + flag03 + flag04 + flag05;

		if(!(flag == 0 || flag == 1)) {
			logger.error("\n" + "duplication exsting id");
			return false;
		}
		return true;
	}

	public OMElement generateDocument() {
		AxiomUtil axiom = AxiomUtil.getInstance();
		
		if (documentEntry.getContent() != null && documentEntry.getContent().contains("http")) {
			logger.info(documentEntry.getContent());
			return null;
		}

		OMElement root = axiom.createOMElement(EbXML.Document, Namespace.IHE);
		root.addAttribute("id", this.getId(), null);
		boolean swa = (documentEntry.getSoap() != null) ? documentEntry.getSoap().isSwa() : false;
		if (!swa) {
			root.setText(documentEntry.getContent());
		} else {
			OMNamespace xop = axiom.createNamespace("http://www.w3.org/2004/08/xop/include", "xop");
			OMElement inclue = axiom.createOMElement("Include", xop);
			inclue.addAttribute("href", documentEntry.getContent(), null);
			root.addChild(inclue);
		}
		return root;
	}
	
	private String[] extractURL(String content) {
		if(content == null) return null;
		int block = 128;
		int size = (content.length() / 128) + 1;
		String[] token = new String[size];
		for (int i = 0; i < token.length; i++) {
			if ((i + 1) * block <= content.length())
				token[i] = (i+1) + "|" + content.substring(i * block, (i + 1) * block);
			else
				token[i] = (i+1) + "|" + content.substring(i * block);
		}
		return token;
	}
	
	private String extractMimeType(String uri) {
		if(uri == null) return null;
		String type = null;
		int dotPos = uri.lastIndexOf(".");
		String extension = uri.substring(dotPos + 1).toLowerCase();
		Node node = MetadataXmlBuilder.codes.QueryNode("Codes/CodeType[@name='mimeType']/Code[@ext='" + extension + "']");
		if (node != null) {
			if (node != null) {
				NamedNodeMap attrs = node.getAttributes();
				if (attrs != null) {
					type = attrs.getNamedItem("code").getNodeValue();
					return type;
				}
			}
		}
		node = MetadataXmlBuilder.web.QueryNode("web-app/mime-mapping/extension[text()='" + extension + "']");
		if (node != null) {
			node = node.getParentNode();
			NodeList nodes = node.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				node = nodes.item(i);
				if (node.getNodeName().equalsIgnoreCase(DocumentEntryConstants.MIME_TYPE)) {
					return node.getTextContent();
				}
			}
		}
		return null;
	}


	@Override
	protected String getId() {
		return documentEntry.getId();
	}
}
