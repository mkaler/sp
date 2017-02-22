package com.pdfProject.pro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorPageController.class);
	
	@RequestMapping(value = "/errorPage", method = { RequestMethod.GET, RequestMethod.POST })
	public String split() {
		logger.info("request mapping for /error.jsp");
		
		return "errorPage";
	}
}
