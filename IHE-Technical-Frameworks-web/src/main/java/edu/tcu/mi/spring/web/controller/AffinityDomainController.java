package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/AffinityDomain")
public class AffinityDomainController extends GenericController  {

	
	public AffinityDomainController(){
        title = "Affinity Domain";
        viewPath = "affinitydomain";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Principal principal){
		return this.viewPath + "/index";
    }
}
