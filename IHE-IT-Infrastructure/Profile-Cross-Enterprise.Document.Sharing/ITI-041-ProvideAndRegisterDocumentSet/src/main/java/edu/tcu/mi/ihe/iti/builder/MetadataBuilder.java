package edu.tcu.mi.ihe.iti.builder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.axiom.om.OMElement;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import edu.tcu.mi.ihe.constants.DocumentRelationshipsConstants;
import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.model.Association;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import edu.tcu.mi.ihe.utility.xml.XMLPath;
import lombok.Getter;

public class MetadataBuilder extends MessageBuilder {

	public static Set<String> objectRef;
	public static XMLPath codes;
	public static XMLPath web;
	
	public static String SourceID  = "1";
	private static String bootTimestamp = "1";
	private static String IP  = "1";
	private static int count = 0;

	@Expose
	private Patient patient; 
	@Expose
	private SubmissionSet submissionSet;
	@Expose @Getter
	private List<DocumentEntry> documents;
	@Expose @Getter
	private List<Folder> folders;
	
	public MetadataBuilder(){
		documents = Lists.newArrayList();
		folders = Lists.newArrayList();

		MetadataBuilder.objectRef = Sets.newTreeSet();
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			MetadataBuilder.IP = localHost.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MetadataBuilder.count = 0;
		
		Properties prop = new Properties();
		try {
			ClassPathResource resource = new ClassPathResource("ihexds.properties");
			prop.load(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sourceId = prop.getProperty("source.id");
		MetadataBuilder.SourceID = sourceId;
		
		
		MetadataBuilder.bootTimestamp = this.generateTimeStamp();
		
		ClassPathResource cResource = new ClassPathResource("codes.xml");
		ClassPathResource wResource = new ClassPathResource("web.xml");
		try {
			MetadataBuilder.codes = new XMLPath(cResource.getInputStream());
			MetadataBuilder.web = new XMLPath(wResource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Patient getPatient(){
		if(patient == null) 
			patient = new Patient();
		return patient;
	}
	
	public SubmissionSet getSubmissionSet(){
		if(submissionSet == null)
			submissionSet = new SubmissionSet();
		submissionSet.setPatient(this.getPatient());
		return submissionSet;
	}
	
	public DocumentEntry addDocument(){
		DocumentEntry document = new DocumentEntry();
		document.setPatient(this.getPatient());
		if(documents == null)  documents = Lists.newArrayList();
		documents.add(document);
		return document;
	}
	
	public Folder addFolder(){
		Folder folder = new Folder();
		folder.setPatient(this.getPatient());
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder;
	}

	public DocumentEntry addDocumentToFolder(String folderId) {
		Folder folder = new Folder(folderId);
		folder.setExisting(true);
		folder.setPatient(this.getPatient());
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder.addDocument();
	}

	public void moveDocumentToFolder(String docId, String folderId) {
		Folder folder = new Folder(folderId);
		folder.setExisting(true);
		folder.addDocument(docId);
		
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
	}

	public Folder moveDocumentToFolder(String docId) {
		Folder folder = new Folder();
		folder.setPatient(this.getPatient());
		folder.addDocument(docId);
		
		if(folders == null)  folders = Lists.newArrayList();
		folders.add(folder);
		return folder;
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
		
		AssociationBuilder associationBuilder = new AssociationBuilder();
		DocumentEntryBuilder documentEntryBuilder = new DocumentEntryBuilder();
		String ssId = submissionSet.getId();
		for(DocumentEntry document : documents){
			documentEntryBuilder.setDocumentEntry(document);
			String deId = document.getId();
			OMElement deElement = documentEntryBuilder.getMessageFromXML();

			// ------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------
			Association hm04 = new Association();
			hm04.setSourceObject(ssId);
			hm04.setTargetObject(deId);
			hm04.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
			hm04.setNote("Original");
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
			OMElement dElement = documentEntryBuilder.generateDocument();
			if(dElement != null)
				provideAndRegisterDocumentSetRequest.addChild(dElement);

//			System.out.println(deElement);
//			System.out.println(hm04Element);
//			System.out.println(rElement);
//			System.out.println(registryObjectList);
//			System.out.println(provideAndRegisterDocumentSetRequest);
			logger.info("------ Document ------");
		}
		// ------ Folder ------
		FolderBuilder folderBuilder = new FolderBuilder();
		for(Folder folder: folders){
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

//				System.out.println(element);
//				System.out.println(fCElement);
//				System.out.println(hm01Element);
//				System.out.println(registryObjectList);
			}
			
			// ------ DocumentEntry ------
			List<DocumentEntry> documents02 = folder.getDocuments();
			for(DocumentEntry document:documents02){
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
				if(!document.isExisting()){
					// ------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------
					Association hm04 = new Association();
					hm04.setSourceObject(ssId);
					hm04.setTargetObject(deId);
					hm04.setAssociation(DocumentRelationshipsConstants.HAS_MEMBER);
					hm04.setNote("Original");
					associationBuilder.setAssociation(hm04);
					OMElement hm04Element = associationBuilder.getMessageFromXML();
					if(hm04Element != null)
						registryObjectList.addChild(hm04Element);
					logger.info("------ HasMember (4) SubmissionSet HasMemeber DocumentEntry ------");
				}
				if(deElement != null)
					registryObjectList.addChild(deElement);
				if(hm02Element != null)
					registryObjectList.addChild(hm02Element);
				if(hm03Element != null)
					registryObjectList.addChild(hm03Element);
				
				// ------ Document ------
				OMElement dElement = documentEntryBuilder.generateDocument();
				if(dElement != null)
					provideAndRegisterDocumentSetRequest.addChild(dElement);
				logger.info("------ Document in Folder------");

//				System.out.println(deElement);
//				System.out.println(hm02Element);
//				System.out.println(hm03Element);
//				System.out.println(dElement);
//				System.out.println(registryObjectList);
//				System.out.println(provideAndRegisterDocumentSetRequest);
			}
			logger.info("------ Folder ------");
		}

		SubmissionSetBuilder submissionSetBuilder = new SubmissionSetBuilder();
		submissionSetBuilder.setSubmissionSet(submissionSet);
		// ------ SubmissionSet ------
		OMElement ssElement = submissionSetBuilder.getMessageFromXML();
		registryObjectList.addChild(ssElement);
		OMElement ssCElement = submissionSetBuilder.generateClassification();
		registryObjectList.addChild(ssCElement);
		logger.info("------ SubmissionSet ------");
		
//		System.out.println(ssElement);
//		System.out.println(ssCElement);
//		System.out.println(registryObjectList);
		
		return provideAndRegisterDocumentSetRequest;
	}


	@Override
	protected boolean validate() {
		return patient != null && submissionSet != null;
	}

	public static String generateUniqueId() {
		MetadataBuilder.count++;
		return 	MetadataBuilder.SourceID + "." + 
				MetadataBuilder.IP + "." + 
				MetadataBuilder.bootTimestamp + "." + 
				Thread.currentThread().getId() + "." + 
				MetadataBuilder.count;
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
