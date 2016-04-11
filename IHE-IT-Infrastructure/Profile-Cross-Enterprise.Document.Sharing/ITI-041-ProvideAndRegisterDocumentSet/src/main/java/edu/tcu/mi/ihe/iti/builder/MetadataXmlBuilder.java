package edu.tcu.mi.ihe.iti.builder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.axiom.om.OMElement;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tcu.mi.ihe.constants.DocumentRelationshipsConstants;
import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.model.Association;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import edu.tcu.mi.ihe.utility.xml.XMLPath;
import lombok.Getter;

public class MetadataXmlBuilder extends MessageBuilder {

	public static Set<String> objectRef;
	public static XMLPath codes;
	public static XMLPath web;
	
	private final String SOURCE_ID;
	private final String IP;
	
	@Getter
	private Metadata metadata;
	
	public MetadataXmlBuilder(Metadata metadata){
		this.metadata = metadata;
		this.setEndpoint(metadata.getEndpoint());
		MetadataXmlBuilder.objectRef = Sets.newTreeSet();
		InetAddress localHost = null;
		try {
			localHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if(localHost != null) IP = localHost.getHostAddress();
		else IP = "127.0.0.1";
		
		Properties prop = new Properties();
		try {
			ClassPathResource resource = new ClassPathResource("ihexds.properties");
			prop.load(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		SOURCE_ID = prop.getProperty("source.id");
		
		ClassPathResource cResource = new ClassPathResource("codes.xml");
		ClassPathResource wResource = new ClassPathResource("web.xml");
		try {
			MetadataXmlBuilder.codes = new XMLPath(cResource.getInputStream());
			MetadataXmlBuilder.web = new XMLPath(wResource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public OMElement getMessageFromXML() {
		AxiomUtil axiom = AxiomUtil.getInstance();
		/* ProvideAndRegisterDocumentSetRequest */
		OMElement provideAndRegisterDocumentSetRequest = axiom.createOMElement(EbXML.ProvideAndRegisterDocumentSetRequest, Namespace.IHE);
		/* SubmitObjectsRequest */
		OMElement submitObjectsRequest = axiom.createOMElement(EbXML.SubmitObjectsRequest, Namespace.LCM3);
		provideAndRegisterDocumentSetRequest.addChild(submitObjectsRequest);
		/* SubmitObjectsRequest */
		OMElement registryObjectList = axiom.createOMElement(EbXML.RegistryObjectList, Namespace.RIM3);
		submitObjectsRequest.addChild(registryObjectList);
		
		AssociationXmlBuilder associationBuilder = new AssociationXmlBuilder(SOURCE_ID, IP);
		DocumentEntryXmlBuilder documentEntryBuilder = new DocumentEntryXmlBuilder(SOURCE_ID, IP);
		String ssId = metadata.getSubmissionSet().getId();
		for(DocumentEntry document : metadata.getDocuments()){
			document.setPatient(metadata.getPatient());
			documentEntryBuilder.setDocumentEntry(document);
			
			String deId = document.getId();
			OMElement deElement = documentEntryBuilder.getMessageFromXML();

			// ------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------
			Association hm04 = new Association();
			hm04.setSourceObject(ssId);
			hm04.setTargetObject(deId);
			hm04.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
			if(!document.isExisting()){
				hm04.setNote("Original");
			} else {
				hm04.setNote("Reference");
			}
			associationBuilder.setAssociation(hm04);
			OMElement hm04Element = associationBuilder.getMessageFromXML();
			logger.info("------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------");
			
			if(deElement != null)
				registryObjectList.addChild(deElement);
			else
				logger.info("doc (id=" + deId + ") is null");
			if(hm04Element != null)
				registryObjectList.addChild(hm04Element);
			else
				logger.info("HasMember is null");
			
			// Relationship
			OMElement rElement = documentEntryBuilder.generateRelationship();
			if(rElement != null){
				registryObjectList.addChild(rElement);
			}
			// ------ Document ------
			if(!document.isExisting()){
				OMElement dElement = documentEntryBuilder.generateDocument();
				logger.debug("\n" + dElement);
				if(dElement != null)
					provideAndRegisterDocumentSetRequest.addChild(dElement);
			}
			logger.debug("\n" + deElement);
			logger.debug("\n" + hm04Element);
			logger.debug("\n" + rElement);
			logger.debug("\n" + registryObjectList);
			logger.debug("\n" + provideAndRegisterDocumentSetRequest);
			logger.info("------ Document ------");
		}
		// ------ Folder ------
		FolderXmlBuilder folderBuilder = new FolderXmlBuilder(SOURCE_ID, IP);
		for(Folder folder: metadata.getFolders()){
			folder.setPatient(metadata.getPatient());
			folderBuilder.setFolder(folder);
			
			String fId = folder.getId();
			// has a new folder
			if(!folder.isExisting()){
				OMElement element = folderBuilder.getMessageFromXML();
				registryObjectList.addChild(element);
				OMElement fCElement = folderBuilder.generateClassification();
				registryObjectList.addChild(fCElement);

				// ------ HasMember (1) SubmissionSet HasMemeber Folder ------ 
				Association hm01 = new Association();
				hm01.setSourceObject(ssId);
				hm01.setTargetObject(fId);
				hm01.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
				associationBuilder.setAssociation(hm01);
				OMElement hm01Element = associationBuilder.getMessageFromXML();
				logger.info("------ HasMember (1) SubmissionSet HasMemeber Folder ------");
				
				if(hm01Element != null)
					registryObjectList.addChild(hm01Element);

				logger.debug("\n" + element);
				logger.debug("\n" + fCElement);
				logger.debug("\n" + hm01Element);
				logger.debug("\n" + registryObjectList);
			}
			
			// ------ DocumentEntry ------
			List<DocumentEntry> documents02 = folder.getDocuments();
			for(DocumentEntry document:documents02){
				document.setPatient(metadata.getPatient());
				documentEntryBuilder.setDocumentEntry(document);
				
				String deId = document.getId();
				OMElement deElement = documentEntryBuilder.getMessageFromXML();
				
				Association hm02 = new Association();
				if(!folder.isExisting()){ // New Folder
					if(!document.isExisting()){ // New DocumentEntry
						// ------ HasMember (2) Folder HasMemeber DocumentEntry ------ 
						hm02.setSourceObject(fId);
						hm02.setTargetObject(deId);
						hm02.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
						logger.info("Scenario 1. DocumentEntry submitted as part of the Folder in a single submission ------");
						logger.info("Scenario 1. ------ HasMember (2) Folder HasMemeber DocumentEntry ------");
					}else{ // Old DocumentEntry
						// Scenario 3. ------ Folder submitted and existing DocumentEntry added to it ------ 
						hm02.setSourceObject(fId);
						hm02.setTargetObject(deId);
						hm02.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
						logger.info("Scenario 3. ------ Folder submitted and existing DocumentEntry added to it ------");
						logger.info("Scenario 3. ------ HasMember (2) Folder HasMemeber DocumentEntry ------");
					}
				}else{  // Old Folder
					if(!document.isExisting()){  // New DocumentEntry
						// Scenario 4. ------ DocumentEntry submitted and added to existing Folder ------
						hm02.setSourceObject(fId);
						hm02.setTargetObject(deId);
						hm02.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
						logger.info("Scenario 4. ------ DocumentEntry submitted and added to existing Folder ------");
						logger.info("Scenario 4. ------ HasMember (2) Folder HasMemeber DocumentEntry ------");
					}else{ // Old DocumentEntry
						// Scenario 2. ------ Add existing DocumentEntry to existing Folder ------ 11971
						hm02.setSourceObject(fId);
						hm02.setTargetObject(deId);
						hm02.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
						logger.info("Scenario 2. ------ Add existing DocumentEntry to existing Folder ------ 11971");
						logger.info("Scenario 2. ------ HasMember (2) Folder HasMemeber DocumentEntry ------");
					}
				}
				associationBuilder.setAssociation(hm02);
				OMElement hm02Element = associationBuilder.getMessageFromXML();
				
				// ------ HasMember (3) SubmissionSet HasMemeber HasMember (2) ------ 
				String hm02Id = hm02.getId();
				Association hm03 = new Association();
				hm03.setSourceObject(ssId);
				hm03.setTargetObject(hm02Id);
				hm03.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
				logger.info("------ HasMember (3) SubmissionSet HasMemeber HasMember (2) ------");

				associationBuilder.setAssociation(hm03);
				OMElement hm03Element = associationBuilder.getMessageFromXML();
					// ------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------
					Association hm04 = new Association();
					hm04.setSourceObject(ssId);
					hm04.setTargetObject(deId);
					hm04.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
					if(!document.isExisting()){
						hm04.setNote("Original");
					} else {
						hm04.setNote("Reference");
					}
					associationBuilder.setAssociation(hm04);
					OMElement hm04Element = associationBuilder.getMessageFromXML();
					if(hm04Element != null)
						registryObjectList.addChild(hm04Element);
					logger.info("------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------");
				if(deElement != null)
					registryObjectList.addChild(deElement);
				if(hm02Element != null)
					registryObjectList.addChild(hm02Element);
				if(hm03Element != null)
					registryObjectList.addChild(hm03Element);
				
				// ------ Document ------
				if(!document.isExisting()){
					OMElement dElement = documentEntryBuilder.generateDocument();
					logger.debug("\n" + dElement);
					if(dElement != null)
						provideAndRegisterDocumentSetRequest.addChild(dElement);
				}
				logger.info("------ Document in Folder------");

				logger.debug("\n" + deElement);
				logger.debug("\n" + hm02Element);
				logger.debug("\n" + hm03Element);
				logger.debug("\n" + registryObjectList);
				logger.debug("\n" + provideAndRegisterDocumentSetRequest);
			}
			logger.info("------ Folder ------");
		}
		SubmissionSet submissionSet = metadata.getSubmissionSet();
		submissionSet.setPatient(metadata.getPatient());
		
		SubmissionSetXmlBuilder submissionSetBuilder = new SubmissionSetXmlBuilder(SOURCE_ID, IP);
		submissionSetBuilder.setSubmissionSet(submissionSet);
		
		// ------ SubmissionSet ------
		OMElement ssElement = submissionSetBuilder.getMessageFromXML();
		registryObjectList.addChild(ssElement);
		OMElement ssCElement = submissionSetBuilder.generateClassification();
		registryObjectList.addChild(ssCElement);
		logger.info("------ SubmissionSet ------");
		
		logger.debug("\n" + ssElement);
		logger.debug("\n" + ssCElement);
		logger.debug("\n" + registryObjectList);
		
		return provideAndRegisterDocumentSetRequest;
	}
	
	@Override
	protected boolean validate() {
		return metadata.getPatient() != null && metadata.getSubmissionSet() != null;
	}

	@Deprecated
	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
	
}
