package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CalculatorHandler implements InvocationHandler {
    
    private Object obj; //��������
     
    public CalculatorHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("in calculatorhandler, before invocation");
        Object ret = method.invoke(obj, args);  //ִ�б������෽��
        System.out.println("in calculationhandler, after invocation");
        return ret;
    }
 
}
