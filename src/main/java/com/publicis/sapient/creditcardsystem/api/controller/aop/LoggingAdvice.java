package com.publicis.sapient.creditcardsystem.api.controller.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class is to facilitate entry and exist
 * level logging based defined point cut
 *
 */
@Aspect
@Component
public class LoggingAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

    /**
     * This method provides point cut for aspect
     * i.e. any package,class or method with any no of arguments
     */
    @Pointcut(value ="execution(* com.publicis.sapient.creditcardsystem.*.*.*.*(..))")
    public void applicationPointCut(){
        LOGGER.info("Executing Application::LoggingAdvice to handle method entry and exist logging");
    }

    /**
     * Add entry and exist level logs
     * based on defined join point
     * @param joinPoint
     * @return Object with className, methodName,
     * list of arguments
     * @throws Throwable : Exception
     */
    @Around("applicationPointCut()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();

        Object[] argumentArray = joinPoint.getArgs();

        LOGGER.info("Method invoked : " + className+ " : " + methodName + "() : arguments : "
                + mapper.writeValueAsString(argumentArray));

        Object obj = joinPoint.proceed();

        LOGGER.info(className+ " : " + methodName + "() Response : "
                + mapper.writeValueAsString(argumentArray));

        return obj;
    }
}
