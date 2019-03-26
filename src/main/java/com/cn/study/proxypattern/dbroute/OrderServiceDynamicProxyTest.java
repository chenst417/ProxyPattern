package com.cn.study.proxypattern.dbroute;

import java.util.Date;

import com.cn.study.proxypattern.dbroute.proxy.OrderServiceDynamicProxy;

public class OrderServiceDynamicProxyTest {

	public static void main(String[] args) {
		
		OrderServiceDynamicProxy proxy = new OrderServiceDynamicProxy();
		
		IOrderService service = (IOrderService) proxy.getInstance(new OrderService());
		
		Order order = new Order();
		order.setCreateTime(new Date().getTime());
		
		service.createOrder(order);
	}
	
}
