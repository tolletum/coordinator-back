package com.cdnator.coordinator.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAspect {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Before("execution(* com.cdnator.coordinator.controller.*.*(..))")
  public void before(JoinPoint joinPoint) {
    logger.debug("--> Entering method {}", joinPoint);
  }

  @After("execution(* com.cdnator.coordinator.controller.*.*(..))")
  public void after(JoinPoint joinPoint) {
    logger.debug("<-- Exiting method {}", joinPoint);
  }
}