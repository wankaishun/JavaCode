package com.proxy;

import java.lang.reflect.Proxy;

public class TestProxy {
	public static void main(String[] args) {
		CalculatorImpl calculatorImpl = new CalculatorImpl();//被代理类
		CalculatorHandler calculatorHandler = new CalculatorHandler(calculatorImpl);
		Calculator calculator = (Calculator) Proxy.newProxyInstance(calculatorImpl.getClass().getClassLoader(), calculatorImpl.getClass().getInterfaces(), calculatorHandler);
		System.out.println(calculator.add(1,2));
	}
}
