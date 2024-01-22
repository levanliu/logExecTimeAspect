package com.advantest.logExecTime.Proxy;
import java.lang.reflect.Method;

import com.advantest.logExecTime.execTime.LogExecTime;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibDynamicProxy implements MethodInterceptor {

    private Object target;

    public CGLibDynamicProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Method tempMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        LogExecTime annotation = tempMethod.getAnnotation(LogExecTime.class);

        if (annotation != null) {
            long start = System.currentTimeMillis();
            Object result = proxy.invoke(target, args);
            long end = System.currentTimeMillis();
            System.out.println("Method: " + method.getName() + ", Running Time: " + (end - start) + "ms");
            return result;
        }

        return method.invoke(target, args);
    }
}
