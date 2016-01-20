package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WelcomeController extends GenericController {

	
	public WelcomeController(){
        title = "Welcome";
        viewPath = "welcome";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	

}
