package com.pdfProject.pro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SplitController {
	
	private static final Logger logger = LoggerFactory.getLogger(SplitController.class);
	
	@RequestMapping(value = "/split", method = RequestMethod.GET)
	public String split() {
		logger.info("request mapping for /split.jsp");
		
		return "split";
	}
}
