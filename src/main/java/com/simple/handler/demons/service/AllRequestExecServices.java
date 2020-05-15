package com.simple.handler.demons.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AllRequestExecServices {
	
	private static final Logger logger = LogManager.getLogger(AllRequestExecServices.class);
	
	public String serviceSectionA(String requestParamString, String requestSubString) {
		logger.info("method trace serviceSectionA ");
		if (("AA").equals(requestSubString)) {
			throw new IllegalArgumentException("You cannot Pass this requestSubString its invalid serviceSectionA " +requestSubString);
		}

		return new StringBuilder(requestParamString).append(":serviceSectionA:").append(requestSubString).toString();
	}
	
	public String serviceSectionB(String requestParamString, String requestSubString) {
		logger.info("method trace serviceSectionB ");
		if (("BB").equals(requestSubString)) {
			throw new IllegalArgumentException("You cannot Pass this requestSubString its invalid serviceSectionB  " +requestSubString);
		}

		return new StringBuilder(requestParamString).append(":serviceSectionB:").append(requestSubString).toString();
	}

	public String serviceSectionC(String requestParamString, String requestSubString) {
		logger.info("method trace serviceSectionC ");
		if (("CC").equals(requestSubString)) {
			throw new IllegalArgumentException("You cannot Pass this requestSubString its invalid serviceSectionC " +requestSubString);
		}

		return new StringBuilder(requestParamString).append(":serviceSectionC:").append(requestSubString).toString();
	}

	public String serviceSectionOthers(String requestParamString, String requestSubString) {
		logger.info("method trace serviceSectionOthers ");
		if (("XX").equals(requestParamString) || ("AA").equals(requestParamString) || ("BB").equals(requestParamString)
				|| ("CC").equals(requestParamString)) {
			throw new IllegalArgumentException("You cannot Pass this parameter its invalid  " +requestParamString);
		}
		return new StringBuilder(requestParamString).append(":serviceSectionOthers:").append(requestSubString).toString();
	}
	
	public String serviceSectionSpecialCond(String requestParamString, String requestSubString) {
		logger.info("reached special condition method {} and {} ", requestParamString, requestSubString);
		return new StringBuilder(requestSubString).append("<===sub-appended-before-param===>")
				.append(requestParamString).toString();
	}


}
