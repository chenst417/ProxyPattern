package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.lang.reflect.Method;

public interface MyselfInvocationHandler {
	
	public Object invoke(Object proxy, Method method, Object[] args)
	        throws Throwable;
	
}