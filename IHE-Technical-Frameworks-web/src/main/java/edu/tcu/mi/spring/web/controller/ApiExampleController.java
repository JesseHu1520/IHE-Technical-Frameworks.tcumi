package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ApiExample")
public class ApiExampleController extends GenericController {

	
	public ApiExampleController(){
        title = "Api Example";
        viewPath = "apiexample";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	
	@RequestMapping(value = {"/pnr/"}, method = RequestMethod.GET)
	public String pnr(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/pnr";
	}
	
	@RequestMapping(value = {"/sq/"}, method = RequestMethod.GET)
	public String sq(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/sq";
	}
	
	@RequestMapping(value = {"/ret/"}, method = RequestMethod.GET)
	public String ret(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/ret";
	}
	

}
