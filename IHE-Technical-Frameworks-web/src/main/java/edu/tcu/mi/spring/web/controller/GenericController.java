package edu.tcu.mi.spring.web.controller;

import edu.tcu.mi.spring.web.controller.rest.GenericRestController;


public abstract class GenericController extends GenericRestController {
	
	protected String routePath = "";
	protected String viewPath = "/";
	
	protected String title = "";
	protected String version = "Ver. 0.3.5";


}
