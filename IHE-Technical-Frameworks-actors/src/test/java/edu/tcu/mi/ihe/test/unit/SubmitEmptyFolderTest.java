package edu.tcu.mi.ihe.test.unit;

import org.junit.Test;

import edu.tcu.mi.ihe.iti.model.Folder;
import edu.tcu.mi.ihe.test.ProvideAndRegisterDocumentSetTest;

public class SubmitEmptyFolderTest extends ProvideAndRegisterDocumentSetTest {

	@Test
	public void test02_submitEmptyFolder(){
		Folder folder = metadata.addFolder();
		folder.setTitle("FF01");
		folder.setDescription("FF01");
		folder.addFolderCode("Referrals");
	}
}
