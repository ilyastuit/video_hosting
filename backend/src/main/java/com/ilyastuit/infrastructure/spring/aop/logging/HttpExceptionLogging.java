package com.ilyastuit.infrastructure.spring.aop.logging;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpExceptionLogging {

    private static final Logger log = LoggerFactory.getLogger(HttpExceptionLogging.class);

    @Pointcut("within(com.ilyastuit.http..*) " +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "&& @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "&& @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "&& @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "&& @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void allRequestMappings() {
        // Pointcut for AOP logging. Therefore there is no method body
    }

    @AfterThrowing(value = "allRequestMappings()", throwing = "exception")
    public void beforeExecute(Exception exception) {
        log.error("Exception = {}", exception.getMessage());
    }
}
