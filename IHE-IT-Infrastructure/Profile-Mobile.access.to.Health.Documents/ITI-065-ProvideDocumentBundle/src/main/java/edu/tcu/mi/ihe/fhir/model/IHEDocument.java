package edu.tcu.mi.ihe.fhir.model;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.resource.Conformance.Document;
import ca.uhn.fhir.model.dstu2.resource.DocumentManifest;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference;

@ResourceDef(name="Document")
public class IHEDocument extends Document {
//	http://wiki.ihe.net/index.php?title=MHD_Implementation_Guide
	
	public IHEDocument(){
		// submission set
		DocumentManifest manifest = new DocumentManifest();
	}
	
	public void addDocumentEntry(){
		DocumentReference reference = new DocumentReference();
		reference.addAuthor();
	}
	
}
