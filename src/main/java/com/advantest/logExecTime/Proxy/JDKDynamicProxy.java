package com.advantest.logExecTime.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.advantest.logExecTime.execTime.LogExecTime;

public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        ClassLoader classLoader = target.getClass().getClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, this);
    }
    

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method tempMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        LogExecTime annotation = tempMethod.getAnnotation(LogExecTime.class);
        
        if (annotation != null) {
            long start = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long end = System.currentTimeMillis();
            System.out.println("Method: " + method.getName() + ", Running Time: " + (end - start) + "ms");
            return result;
        }
        
        return method.invoke(target, args);
    }
}
