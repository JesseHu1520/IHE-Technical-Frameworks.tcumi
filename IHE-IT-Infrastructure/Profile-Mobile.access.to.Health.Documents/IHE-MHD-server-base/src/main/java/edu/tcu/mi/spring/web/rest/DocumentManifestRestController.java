package edu.tcu.mi.spring.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.fhir.model.dstu2.resource.Bundle;
import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.actor.XDSDocumentSource;

@RestController
@RequestMapping("/base/DocumentManifest")
public class DocumentManifestRestController extends GenericRestController {

	@Autowired
	private XDSDocumentSource documentSource;
	
	@Autowired
	private XDSDocumentConsumer documentConsumer;
	
    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<?> findDocumentManifests() {
    	
    	
		return new ResponseEntity<>(gson.toJson(""), headers, HttpStatus.OK);
    }   
	
    @RequestMapping(value = {"/"}, method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<?> documentManifest(@RequestParam("patientId") String patientId, Bundle bundle) {
//    	List<Entry> entries = bundle.getEntry();
//		Entry documentManifestEntry = Iterables.find(entries, new Predicate<Bundle.Entry>() {
//			@Override
//			public boolean apply(Entry entry) {
//	    		IResource resource = entry.getResource();
//				return resource instanceof DocumentManifest;
//			}
//		}, null);
//    	if(documentManifestEntry == null ) return new ResponseEntity<>(gson.toJson(""), headers, HttpStatus.BAD_REQUEST);
//    	
//    	
//    	MetadataXmlBuilder builder = new MetadataXmlBuilder();
//    	Patient patient = builder.getPatient();
//    	// TODO assign patient information
//    	
//    	/* ======== SubmissionSet ======== */
//    	SubmissionSet submissionSet = builder.getSubmissionSet();
//    	
//    	DocumentManifest documentManifest = (DocumentManifest) documentManifestEntry.getResource();
//
//    	// TODO submissionSet
////    	submissionSet.setContentTypeCode("34746-8"); //SubmissionSet 分類
////		
////		SubmissionSet.Author author = submissionSet.addAuthor();
////		author.addAuthorRole("行政");
////		author.addAuthorPerson("Gaduo");
////		author.addAuthorInstitution("醫院");
////		author.addAuthorSpecialty("行政");
//
//    	/* ======== DocumentEntry ======== */
//    	
//    	Iterables.filter(entries, new Predicate<Entry>(){
//			@Override
//			public boolean apply(Entry entry) {
//	    		IResource resource = entry.getResource();
//				return resource instanceof DocumentReference;
//			};
//    	});
//    	
//    	for(Entry entry : entries){
//    		IResource resource = entry.getResource();
//    		DocumentReference documentReference = (DocumentReference) resource;
//    		DocumentEntry document = builder.addDocument();
//    		
//    		List<ResourceReferenceDt> resourceReferences = documentReference.getAuthor();
//    		for(ResourceReferenceDt resourceReference : resourceReferences){
//    			IBaseResource res = resourceReference.getResource();
//        		Practitioner practitioner = (Practitioner) res;
//    			Author author = document.addAuthor();
//        		
//        		author.addAuthorPerson(practitioner.getId().getValueAsString());
//        		List<PractitionerRole> roles = practitioner.getPractitionerRole();
//        		for(PractitionerRole role : roles){
//        			author.addAuthorRole(role.getRole().getText());	
//        		}
//    		}
//    		
//    		document.setDescription(documentReference.getDescription());
//    		document.setCreationTime(new DateTime(documentReference.getCreated()).toString());
//
//        	// TODO Class type
////    		documentReference.getType();
////    		documentReference.getClassElement();
//    		
////    		document.setClassCode("DEMO-Procedure");
////    		document.setFormatCode("urn:ihe:rad:TEXT");
////    		document.setHealthcareFacilityTypeCode("281PC2000N");
////    		document.setPracticeSettingCode("394802001");
////    		document.setTypeCode("34096-8");
////    		document.addConfidentialityCode("N");
////    		document.addEventCodeList("T-D4909");
////    		document.addEventCodeList("TRID1001");
//    		
//    		List<Content> contents = documentReference.getContent();
//    		Content content = contents.get(0);
//    		document.setMimeType(content.getFormatFirstRep().getDisplay());
//    		document.setContent(content.getAttachment().getData());
//    	}
//		OMElement response = documentSource.provideAndRegisterDocumentSet(builder , new NonBlockCallBack());
    	
		return new ResponseEntity<>(gson.toJson(""), headers, HttpStatus.OK);
    }
    
	
}
