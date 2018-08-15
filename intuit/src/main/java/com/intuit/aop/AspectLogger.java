package com.intuit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class AspectLogger {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String PACKAGE_SERVICE = "execution(* com.intuit.servicesImpl.*.*(..))";
	public static final String PACKAGE_DAO = "execution(* com.intuit.daoImpl.*.*(..))";
	public static final String PACKAGE_CONTROLLER = "execution(* com.intuit.controller.*.*(..))";
	public static final String OR = " || ";
	
	
	public AspectLogger() {
	}

	@AfterReturning(PACKAGE_SERVICE + OR + PACKAGE_DAO + OR + PACKAGE_CONTROLLER)
    public void logMethodAccessAfter(JoinPoint joinPoint) {
		logger.info(joinPoint.getTarget().getClass().getName() + " : " + joinPoint.getSignature().getName() +  " ***** Completed  *****  ");
	}
       

	 @Before(PACKAGE_SERVICE + OR + PACKAGE_DAO + OR + PACKAGE_CONTROLLER)
	 public void logMethodAccessBefore(JoinPoint joinPoint) {
		 logger.info(joinPoint.getTarget().getClass().getName() + ":"  + joinPoint.getSignature().getName() + " ***** Starting *****");
	 }
	 
	 @AfterThrowing(pointcut = PACKAGE_SERVICE + OR + PACKAGE_DAO + OR + PACKAGE_CONTROLLER, throwing = "exception")
		public void logExceptions(JoinPoint joinPoint, Exception exception){
		 logger.error("AOP reporting execption in \n CLASS --> "+joinPoint.getTarget().getClass().getName() + 
	    			"\n METHOD --> " + joinPoint.getSignature().getName() + "\n MESSAGE -> " + exception );
	    	exception.printStackTrace();
	 }
}
