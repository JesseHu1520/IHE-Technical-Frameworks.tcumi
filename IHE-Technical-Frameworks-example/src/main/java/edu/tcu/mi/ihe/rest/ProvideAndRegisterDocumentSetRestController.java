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

import edu.tcu.mi.ihe.actor.XDSDocumentSource;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import edu.tcu.mi.ihe.iti.model.Metadata;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;


@RestController
@RequestMapping("/base/ProvideAndRegisterDocumentSet")
public class ProvideAndRegisterDocumentSetRestController extends GenericRestController {

	@Autowired
	private XDSDocumentSource documentSource;
	
    @RequestMapping(value = {"/"}, method = RequestMethod.POST, produces="application/json")
	@ResponseBody
    public ResponseEntity<?> list(@RequestBody Metadata metadata, Principal principal){

		OMElement response = documentSource.provideAndRegisterDocumentSet(metadata, new NonBlockCallBack());
		if(response != null){
			AxiomUtil axiom = AxiomUtil.getInstance();
	        RegistryResponseType registryResponse = axiom.fromXML(response, RegistryResponseType.class);
			return new ResponseEntity<>(registryResponse.toString(), headers, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
