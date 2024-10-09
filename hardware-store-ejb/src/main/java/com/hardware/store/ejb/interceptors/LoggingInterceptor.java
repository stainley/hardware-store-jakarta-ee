package com.hardware.store.ejb.interceptors;

import com.hardware.store.ejb.interceptors.annotations.LoggingInterceptorBinding;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@LoggingInterceptorBinding
public class LoggingInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        logger.info("Method invocation started: {}", context.getMethod().getName());
        long startTime = System.currentTimeMillis();
        Object result = context.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Method invocation completed in {} ms", (endTime - startTime));
        return result;
    }
}
