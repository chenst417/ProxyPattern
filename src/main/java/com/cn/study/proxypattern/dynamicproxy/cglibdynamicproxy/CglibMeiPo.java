package com.cn.study.proxypattern.dynamicproxy.cglibdynamicproxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMeiPo implements MethodInterceptor {

	public Object getInstance(Class<?> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}


	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object invokeSuper = proxy.invokeSuper(obj, args);
		after();
		return invokeSuper;
	}

	public void before() {
		System.out.println("确认女孩找对象的标准，以及女孩自己的条件。。。。。。");
	}

	public void after() {
		System.out.println("找到合适的男孩，见面。。。。。。");
	}

}
