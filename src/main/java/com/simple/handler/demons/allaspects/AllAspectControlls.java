package com.simple.handler.demons.allaspects;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AllAspectControlls {
	
	private static final Logger logger = LogManager.getLogger(AllAspectControlls.class);
	
	
	@Before("execution(* com.simple.handler.demons.service.AllRequestExecServices.serviceSectionA(String, String))")
	public void callBeforeSectionAControl() {
		logger.info(" Before the execution of AllSections with execution syntax ---> executed only for sectionA");
	}
	
	@Before("within(com.simple.handler.demons.service.*) && args(String, String)")
	public void callBeforeSectionExecBeforeWithIn(JoinPoint joinPoint) {
		logger.info(" Within call execution to be executed for all method ");
		Object[] argumentObjects = joinPoint.getArgs();
		if (argumentObjects.length > 3) {
			throw new IllegalArgumentException("length cannot be null ");
		}
		Arrays.stream(argumentObjects).forEach(objectValue -> {
			logger.info("objectValuePassed ===> {}", objectValue.toString());
			});
		
		logger.info(" values passed for for JoinPoint cut execution {} and {} ", joinPoint);
	}
	
	@After("within(com.simple.handler.demons.service.*)")
	public void callBeforeSectionAExecution() {
		logger.info(" After CallExecution trying with syntax ");
	}
	
	@AfterThrowing("execution(* com.simple.handler.demons.service.AllRequestExecServices.serviceSectionOthers(String, String)) ")
	public void callAfterThrowingAspectServiceSectionOthers(JoinPoint joinPoint) {
		logger.info(" message exepression {} ", joinPoint.getSignature().getName());
		logger.info(" simple message to be captured after throwing expression ");
	}
	
	@AfterThrowing("execution(* com.simple.handler.demons.service.*.*(String,String))")
	public void callAfterAllServiceMethodExeception(JoinPoint joinPoint) {
		logger.info("message exepression for all method {} ",joinPoint.getSignature().getName());
		logger.info("File location information {} ", joinPoint.getTarget().getClass().getName());
	}
	
	@AfterThrowing(pointcut = "execution(* com.simple.handler.demons.service.AllRequestExecServices.serviceSectionB(..))", 
			throwing="excep")
	public void callAfterServicePointCutServices(JoinPoint joinPoint, Throwable excep) {
		logger.info("Inside the callAfterServicePointCutServices ===> {} ", joinPoint.getSignature().getName());
		logger.info(" exception type thrown ===> {} ",  excep.getMessage());
	}
	
	@AfterThrowing(pointcut = "execution(* com.simple.handler.demons.service.*.*(..))", 
			throwing="excep")
	public void callAfterServicePointCutGenericHandler(JoinPoint joinPoint, Throwable excep) {
		logger.info("from inside more Generic Handler method  callAfterServicePointCutGenericHandler ===> {} ",
				joinPoint.getSignature().getName());
		Object[] argumentValues = joinPoint.getArgs();
		Arrays.stream(argumentValues).filter(argument -> argument instanceof String).findFirst()
				.ifPresent(requestParam -> logger.info("logging requestParam values {}", requestParam));
		logger.info(" exception type thrown ===> {} ",  excep.getMessage());
	}
	
	@Around("execution(* com.simple.handler.demons.service.AllRequestExecServices.serviceSectionSpecialCond(String, String)) ")
	public String aroundValidationProcess(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object[] argumentValues = joinPoint.getArgs();
		String requestParam = String.valueOf(argumentValues[0]);
		String requestSubString = String.valueOf(argumentValues[1]);
		
		if ("HelloA".equals(requestParam) && "HelloHello".equals(requestSubString)) {
			String result = (String) joinPoint.proceed();
			logger.info("Completed the service execution trying to get the return value ");
			logger.info("Return result ===> {} ", result);
			return result;
		} else {
			logger.info("ErrorCondition not calling with right value please check {} and {} ", requestParam,
					requestSubString);
			return "input param is invalid ";
		}
		
	}


}
