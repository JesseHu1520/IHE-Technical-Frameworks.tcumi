package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.test.ProvideAndRegisterDocumentSetTest;

public class SubmitExistingDocumentEntryAddToFolderTest extends ProvideAndRegisterDocumentSetTest {

	@Test
	public void existingDocumentEntryAddToFolder(){
		String docId = submitOneDocument();
		Folder folder = metadata.moveDocumentToFolder(docId);
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");
	}
}
