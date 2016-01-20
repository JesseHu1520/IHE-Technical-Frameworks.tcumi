package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Contact")
public class ContactController extends GenericController {

	
	public ContactController(){
        title = "Contact";
        routePath = "Contact";
        viewPath = "contact";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	

}
