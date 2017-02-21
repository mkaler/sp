package com.pdfProject.pro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TemplateController {


	private static final Logger logger = LoggerFactory.getLogger(SplitController.class);
	
	@RequestMapping(value = "/template", method = { RequestMethod.GET, RequestMethod.POST })
	public String template() {
		logger.info("request mapping for /template.jsp");
		
		return "template";
	}
}
