package com.hardware.store.ejb.interceptors;

import com.hardware.store.ejb.interceptors.annotations.LoggingInterceptorBinding;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@LoggingInterceptorBinding
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        System.out.println("Method invocation started: " + context.getMethod().getName());
        long startTime = System.currentTimeMillis();
        Object result = context.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Method invocation completed in " + (endTime - startTime) + "ms");
        return result;
    }
}
