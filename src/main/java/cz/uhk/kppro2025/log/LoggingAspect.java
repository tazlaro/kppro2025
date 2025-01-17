package cz.uhk.kppro2025.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* cz.uhk.kppro2025.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        // String className = joinPoint.getTarget().getClass().getName();    // vrati jmeno tridy vcetne balicku v nichz je obsazena
        String className = joinPoint.getTarget().getClass().getSimpleName(); // vrati jmeno tridy
        System.out.println("Calling method: " + className + "." + methodName);
    }
}
