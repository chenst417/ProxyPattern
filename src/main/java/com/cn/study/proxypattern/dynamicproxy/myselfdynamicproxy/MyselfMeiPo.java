package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.lang.reflect.Method;

public class MyselfMeiPo implements MyselfInvocationHandler {

	private Object object;

	public Object getInstance(Object object) {
		this.object = object;

		return MyselfProxy.newProxyInstance(new MyselfClassLoader(),
				object.getClass().getInterfaces(), this);
	}

	@Override
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
