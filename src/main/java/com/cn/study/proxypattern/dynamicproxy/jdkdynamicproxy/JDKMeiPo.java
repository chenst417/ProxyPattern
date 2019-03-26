package com.cn.study.proxypattern.dynamicproxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeiPo implements InvocationHandler {

	private Object object;

	public Object getInstance (Object object) {
		this.object = object;
		return Proxy.newProxyInstance(object.getClass().getClassLoader(),
				object.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object object2 = method.invoke(this.object, args);
		after();
		return object2;
	}


	public void before() {
		System.out.println("确认女孩找对象的标准，以及女孩自己的条件。。。。。。");
	}

	public void after() {
		System.out.println("找到合适的男孩，见面。。。。。。");
	}

}
