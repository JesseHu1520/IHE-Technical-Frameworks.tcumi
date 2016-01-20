package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/IHEConnectathon")
public class IHEConnectathonController extends GenericController {

	
	public IHEConnectathonController(){
        title = "IHE Connectathon";
        viewPath = "iheconnectathon";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
	
	@RequestMapping(value = {"/ihec/"}, method = RequestMethod.GET)
	public String ihec(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/ihec";
	}
	
	@RequestMapping(value = {"/preconnect/"}, method = RequestMethod.GET)
	public String preconnect(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/preconnect";
	}
	
	@RequestMapping(value = {"/endpoints/"}, method = RequestMethod.GET)
	public String endpoints(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/endpoints";
	}

}
