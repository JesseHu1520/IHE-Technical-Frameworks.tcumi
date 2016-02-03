package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.FolderConstants;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Setter;

public class FolderXmlBuilder extends EntityXmlBuilder {
	@Setter 
	private Folder folder;
	
	public FolderXmlBuilder(String sourceId, String ip) {
		super(sourceId, ip);
		objectType = ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_OBJECT;
	}
	
	@Override
	public OMElement getMessageFromXML() {
		AxiomUtil axiom = AxiomUtil.getInstance();
		
		OMElement root = axiom.createOMElement(EbXML.RegistryPackage, Namespace.RIM3);
		root.addAttribute("id", folder.getId(), null);
		root.addAttribute("objectType", objectType, null);
		MetadataXmlBuilder.objectRef.add(objectType);
		
		// --Folder Time
		String lastUpdateTime = generateTimeStamp();
		if (lastUpdateTime != null) {
			OMElement slot = generateSlot(FolderConstants.LAST_UPDATE_TIME, new String[] { lastUpdateTime });
			root.addChild(slot);
		}
		// // ---------------------Main
		if (folder.getTitle() != null) {
			OMElement name = generateNameOrDescription(folder.getTitle(), EbXML.Name);
			root.addChild(name);
		}
		if (folder.getDescription() != null) {
			OMElement name = generateNameOrDescription(folder.getDescription(), EbXML.Description);
			root.addChild(name);
		}
		// ---------------------Classification
		// --------FolderCodeList
		for(String value : folder.getFolderCodeList()){
			if (value != null) {
				OMElement classification = generateClassification("folderCodeList", value, FolderConstants.CODING_SCHEME, folder.getId(), ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_CODE);
				root.addChild(classification);
			}
		}
		// ---------------------ExternalIdentifier
		// --Folder SourcePatienId
		Patient patient = folder.getPatient();
		OMElement name01 = generateNameOrDescription(FolderConstants.PATIENT_ID, EbXML.Name);
		OMElement externalIdentifier01 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_PATIENT_IDENTIFICATION_SCHEME, folder.getId(), patient.getId(), name01);
		root.addChild(externalIdentifier01);
		
		String uniqueId = generateUniqueId();
		OMElement name02 = generateNameOrDescription(FolderConstants.UNIQUE_ID, EbXML.Name);
		OMElement externalIdentifier02 = generateExternalIdentifier(ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_UNIQUE_IDENTIFICATION_SCHEME, folder.getId(), uniqueId, name02);
		root.addChild(externalIdentifier02);
		
		return root;
	}

	@Override
	protected boolean validate() {
		return true;
	}

	@Override
	protected String getId() {
		return folder.getId();
	}

}
