package com.simple.handler.demons.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.handler.demons.service.AllRequestExecServices;

@RestController
public class AspectDemonsController {
	
	private static final Logger logger = LogManager.getLogger(AspectDemonsController.class);
	
	@Autowired
	private AllRequestExecServices allRequestService;
	
	@GetMapping("/hello-demon")
	public String triggerTheDemons(@RequestParam String requestString, @RequestParam String requestSubString) {
		
		
		String returnString = null;
		switch (requestString) {
		case "A":
			logger.info(" inside section A {} and {} ", requestString, requestSubString);
			returnString = allRequestService.serviceSectionA(requestString, requestSubString);
			break;
		case "B":
			logger.info(" inside section B {} and {} ", requestString, requestSubString);
			returnString = allRequestService.serviceSectionB(requestString, requestSubString);
			break;
		case "C":
			logger.info(" inside section C {} and {} ", requestString, requestSubString);
			returnString = allRequestService.serviceSectionC(requestString, requestSubString);
			break;
		case "HelloA":
			logger.info(" inside section Special cond {} and {} ", requestString, requestSubString);
			returnString = allRequestService.serviceSectionSpecialCond(requestString, requestSubString);
			break;
		default :
			logger.info(" inside default section {} and {} ", requestString, requestSubString);
			returnString = allRequestService.serviceSectionOthers(requestString, requestSubString);
		}
		
		logger.info("ending demon methods execution {} ALL {} ", requestString, requestSubString);
		return "successfully_triggered<===>" + returnString;
	}

}
