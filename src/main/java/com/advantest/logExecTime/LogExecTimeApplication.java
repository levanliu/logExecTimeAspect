package com.advantest.logExecTime;

import com.advantest.logExecTime.Proxy.CGLibDynamicProxy;
import com.advantest.logExecTime.Proxy.JDKDynamicProxy;
import com.advantest.logExecTime.services.Calculate;
import com.advantest.logExecTime.services.CalculateImpl;


public class LogExecTimeApplication {
	public static void main(String[] args) throws InterruptedException {
		Calculate calculate = (Calculate) new JDKDynamicProxy(new CalculateImpl()).getProxy() ;
        calculate.addTwoArrayList();
		calculate.subTwoArrayList();

		CalculateImpl calculateImpl = new CGLibDynamicProxy(new CalculateImpl()).getProxy() ;
        calculateImpl.addTwoArrayList();
		calculateImpl.subTwoArrayList();
	}
}