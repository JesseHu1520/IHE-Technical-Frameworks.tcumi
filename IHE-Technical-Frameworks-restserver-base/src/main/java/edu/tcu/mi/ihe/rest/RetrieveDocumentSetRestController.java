package edu.tcu.mi.ihe.rest;

import java.security.Principal;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.model.RetrieveModel;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@RestController
@RequestMapping("/base/RetrieveDocumentSet")
public class RetrieveDocumentSetRestController extends GenericRestController {

	@Autowired
	private XDSDocumentConsumer documentConsumer;
	
    @RequestMapping(value = {"/"}, method = RequestMethod.POST, produces="application/json")
	@ResponseBody
    public ResponseEntity<?> list(@RequestBody RetrieveModel retrieveModel, Principal principal){

    	System.out.println(retrieveModel);
    	
		OMElement response = documentConsumer.retrieveDocumentSet(retrieveModel, new NonBlockCallBack());
		if(response != null){
			AxiomUtil axiom = AxiomUtil.getInstance();
			AdhocQueryResponseType adhocQueryResponse = axiom.fromXML(response, AdhocQueryResponseType.class);
			return new ResponseEntity<>(adhocQueryResponse.toString(), headers, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}