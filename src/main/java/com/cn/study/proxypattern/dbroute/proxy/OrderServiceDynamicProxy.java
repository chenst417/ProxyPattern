package com.cn.study.proxypattern.dbroute.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.cn.study.proxypattern.dbroute.IOrderService;
import com.cn.study.proxypattern.dbroute.db.DynamicDataSourceEntity;

public class OrderServiceDynamicProxy implements InvocationHandler {

	private IOrderService iOrderService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	public Object getInstance (IOrderService iOrderService) {
		this.iOrderService = iOrderService;
		return Proxy.newProxyInstance(iOrderService.getClass().getClassLoader(),
				iOrderService.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		before(args[0]);
		Object obj = method.invoke(this.iOrderService, args);
		after();
		return obj;
	}

	private void before(Object object) {
		try {
			Long createTime = (Long) object.getClass().getMethod("getCreateTime").invoke(object);
			Integer dbRouter = Integer.valueOf(sdf.format(new Date(createTime)));
			System.out.println("动态代理类自动分配到【DB_" +  dbRouter + "】数据源处理数据" );
			DynamicDataSourceEntity.set(dbRouter);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void after() {
		DynamicDataSourceEntity.restore();
	}


}