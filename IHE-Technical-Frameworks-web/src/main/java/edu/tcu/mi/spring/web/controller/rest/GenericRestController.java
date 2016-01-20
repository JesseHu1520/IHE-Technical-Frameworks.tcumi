package edu.tcu.mi.spring.web.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.gson.Gson;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;


public abstract class GenericRestController {
	// http://racksburg.com/choosing-an-http-status-code/
	
	protected static Logger logger = Logger.getLogger(GenericRestController.class);
	protected static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	protected HttpHeaders headers = new HttpHeaders();
	protected Gson gson = new Gson();
	
	public GenericRestController(){
        BasicEtmConfigurator.configure();
		headers.add("Content-Type", "application/json; charset=utf-8");
	}
	
	protected Map<String, String> getErrorsInASaneFormat(final BindingResult result) {
		return new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				for (FieldError error : result.getFieldErrors()) {
					put(error.getField(), error.getDefaultMessage());
				}
			}
		};
	}
	
}
