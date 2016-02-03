/**
 * 
 */
package edu.tcu.mi.zk.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.TreeNode;

import edu.tcu.mi.ihe.iti.model.Author;
import edu.tcu.mi.ihe.iti.model.DocumentEntry;
import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.iti.model.Patient;
import edu.tcu.mi.ihe.iti.model.SubmissionSet;
import edu.tcu.mi.zk.model.attachment.AttachmentEntry;
import edu.tcu.mi.zk.model.attachment.AttachmentEntryTreeNode;
import edu.tcu.mi.zk.model.code.Code;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class MetadataGenerator {
	@Getter
	private Metadata metadata;
	@Getter @Setter
	private String pid5, pid3, pid8 = "M", pid11, pid13;
	@Getter @Setter
	private Date pid7;
	@Getter @Setter
	private String sourceID;
	@Getter @Setter
	private String operations = "12049"; // zul 認  string, 不認 int  
	@Getter @Setter
	private String authorPerson, authorInstitution, authorRole, authorSpecialty;

	@Getter @Setter
	private Code contentTypeCode;
	@Getter @Setter
	private Code classCode;
	@Getter @Setter
	private Code confidentialityCode;
	@Getter @Setter
	private Code formatCode;
	@Getter @Setter
	private Code healthcareFacilityTypeCode;
	@Getter @Setter
	private Code practiceSettingCode;
	@Getter @Setter
	private Code typeCode;
	@Getter @Setter
	private Code eventCodeList;
	@Getter @Setter
	private Code folderCodeList;
	@Getter @Setter
	private CompanyInfomation companyRepository;
	@Getter @Setter
	private List<AttachmentEntry> documentList;
	@Setter
	private AttachmentEntryTreeNode<AttachmentEntry> root;
	@Getter @Setter
	private DefaultTreeModel<AttachmentEntry> folderList;
	@Getter @Setter
	private String existingDocument;
	@Getter @Setter
	private String existingFolder;

	public MetadataGenerator() {
		documentList = new ArrayList<AttachmentEntry>();
		root = new AttachmentEntryTreeNode<AttachmentEntry>(null, null);
		folderList = new DefaultTreeModel<AttachmentEntry>(root);
	}

	public void build() {
		metadata = new Metadata();
		
		SubmissionSet submissionSet = metadata.getSubmissionSet();
		submissionSet.setContentTypeCode(contentTypeCode.getCode());
		patient();
		author();

		for(AttachmentEntry doc : documentList){
			DocumentEntry document = metadata.addDocument();
			buildDocument(document, doc);
			int opt = Integer.valueOf(operations);
			switch (opt) {
			case 11971:
				document = metadata.addDocumentToFolder(existingFolder);
				break;
			case 11974:
				document.setReplaceDocumentId(existingDocument);
				break;
			case 11975:
				document.setTransformDocumentId(existingDocument);
				break;
			case 11976:
				document.setTransformAndReplaceDocumentId(existingDocument);
				break;
			case 11977:
				document.setAppendDocumentId(existingDocument);
				break;
			}
		}
		List<TreeNode<AttachmentEntry>> flist = getRoot();
		if ((!operations.equals("11971") || !operations.equals("11973")) && !flist.isEmpty()) {
			setOperations("11969");// 11970
			for(TreeNode<AttachmentEntry> fItem:flist){
				Folder folder = metadata.addFolder();
				
				AttachmentEntry ff = fItem.getData();
				folder.setTitle(ff.getTitle());
				folder.setDescription(ff.getDescription());
				folder.addFolderCode(folderCodeList.getCode());
				if (fItem.getChildCount() > 0) {
					setOperations("11970");
				}
				for (int i = 0; i < fItem.getChildCount(); i++) {
					AttachmentEntry doc = fItem.getChildAt(i).getData();
					DocumentEntry document = folder.addDocument();
					buildDocument(document, doc);
				}
			}
		}
		int opt = Integer.valueOf(operations);
		switch (opt) {
		case 11973:
			metadata.moveDocumentToFolder(existingDocument, existingFolder);
			break;
		}
	}

	private void patient() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Patient patient = metadata.getPatient();
		patient.setId(getPid3());
		patient.setName(getPid5());
		patient.setBirthday(dateFormat.format(getPid7()));
		patient.setGender(getPid8());
		patient.setAddress(getPid11());
		patient.setPhoneNumber(getPid13());
	}

	private void author() {
		SubmissionSet submissionSet = metadata.getSubmissionSet();
		Author author = submissionSet.addAuthor();
		author.addAuthorPerson(authorPerson);
		author.addAuthorInstitution(authorInstitution);
		author.addAuthorRole(authorRole);
		author.addAuthorSpecialty(authorSpecialty);
	}
	
	public void buildDocument(DocumentEntry document, AttachmentEntry doc) {
		document.setTitle(doc.getTitle());
		document.setDescription(doc.getDescription());
		document.setContent(doc.getContent());
		document.setClassCode(classCode.getCode());
		document.setHealthcareFacilityTypeCode(healthcareFacilityTypeCode.getCode());
		document.setFormatCode(formatCode.getCode());
		document.setFormatCode(formatCode.getCode());
		document.setPracticeSettingCode(practiceSettingCode.getCode());
		document.setTypeCode(typeCode.getCode());
        // TODO: ConfidentialityCodes can assign multi-Codes
		document.addConfidentialityCode(confidentialityCode.getCode());
        // TODO: EventCodeList can assign multi-Codes
		document.addEventCodeList(eventCodeList.getCode());
        

        //TODO: DocumentAuthors should be redesigned by implement requirements
        // default assign same author to all documents
		Author author = document.addAuthor();
		author.addAuthorPerson(authorPerson);
		author.addAuthorInstitution(authorInstitution);
		author.addAuthorRole(authorRole);
		author.addAuthorSpecialty(authorSpecialty);
	}

	public void addDocumentItem(AttachmentEntry doc) {
		this.documentList.add(doc);
	}

	public void removeDocumentItem(int i) {
		this.documentList.remove(i);
	}

	// -- Document
	// -- Folder
	public List<TreeNode<AttachmentEntry>> getRoot() {
		return this.root.getChildren();
	}

	public void addFolderItem(AttachmentEntry folder) {
		this.root.add(new AttachmentEntryTreeNode<AttachmentEntry>(folder, null));
	}

	public void removeFolderItem(int i) {
		this.root.remove(i);
	}

}
