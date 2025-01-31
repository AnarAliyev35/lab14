package com.example.secureblog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SecurityLoggingAspect {

    @Around("@annotation(org.springframework.security.access.prepost.PreAuthorize)")
    public Object logSecurityAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "anonymous";
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("User '{}' attempting to access secured method '{}.{}'", username, className, method);

        try {
            Object result = joinPoint.proceed();
            log.info("User '{}' successfully accessed method '{}.{}'", username, className, method);
            return result;
        } catch (Exception e) {
            log.error("User '{}' failed to access method '{}.{}'. Error: {}", 
                    username, className, method, e.getMessage());
            throw e;
        }
    }
}
