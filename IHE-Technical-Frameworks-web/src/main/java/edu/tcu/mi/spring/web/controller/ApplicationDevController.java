package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ApplicationDev")
public class ApplicationDevController extends GenericController {

	
	public ApplicationDevController(){
        title = "Application Dev.";
        viewPath = "applicationdev";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	
	@RequestMapping(value = {"/java/"}, method = RequestMethod.GET)
	public String java(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/java";
	}
	
	@RequestMapping(value = {"/tomcat/"}, method = RequestMethod.GET)
	public String tomcat(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/tomcat";
	}
	
	@RequestMapping(value = {"/maven/"}, method = RequestMethod.GET)
	public String maven(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/maven";
	}
	
	@RequestMapping(value = {"/openxds/"}, method = RequestMethod.GET)
	public String openxds(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/openxds";
	}
	

}
