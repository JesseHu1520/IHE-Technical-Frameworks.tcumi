package edu.tcu.mi.spring.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Download")
public class DownloadController extends GenericController {

	public DownloadController(){
        title = "Download";
        routePath = "Download";
        viewPath = "download";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String jardownload(Model model, Principal principal){
		model.addAttribute("title", title);
		return viewPath + "/index";
	}
}
