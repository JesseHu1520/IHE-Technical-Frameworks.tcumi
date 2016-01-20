package edu.tcu.mi.spring.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/DocumentReference")
public class DocumentReferenceRestController extends GenericRestController { 
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<?> findDocumentReferences() {
    	
    	
		return new ResponseEntity<>(gson.toJson(""), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/retrieveDocument"}, method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<?> retrieveDocument() {
    	
		return new ResponseEntity<>(gson.toJson(""), headers, HttpStatus.OK);
    }
    
	
}
