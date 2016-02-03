package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.test.ProvideAndRegisterDocumentSetTest;

public class SubmitExistingDocumentEntryAddToExistingFolderTest extends ProvideAndRegisterDocumentSetTest {

	
	@Test
	public void existingDocumentEntryAddToExistingFolder(){
		String docId = submitOneDocument();
		String folderId = submitOneFolder();
		metadata.moveDocumentToFolder(docId, folderId);
	}
}
