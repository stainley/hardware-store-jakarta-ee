package com.hardware.store.ejb.interceptors;

import com.hardware.store.ejb.interceptors.annotations.SecurityInterceptorBinding;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@SecurityInterceptorBinding
public class SecurityInterceptor {

    @AroundInvoke
    public Object checkPermissions(InvocationContext context) throws Exception {
        if (context.getMethod().getName().startsWith("admin")) {
            if (!isAdmin()) {
                throw new SecurityException("Access denied");
            } else {
                return context.proceed();
            }
        }
        return null;
    }

    public boolean isAdmin() {
        return true;
    }
}
