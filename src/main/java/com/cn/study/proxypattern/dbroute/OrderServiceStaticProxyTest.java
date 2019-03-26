package com.cn.study.proxypattern.dbroute;

import java.util.Date;
import com.cn.study.proxypattern.dbroute.proxy.OrderServiceStaticProxy;

public class OrderServiceStaticProxyTest {

	public static void main(String[] args) {
		
		IOrderService iOrderService = new OrderServiceStaticProxy(new OrderService());
		Order order = new Order();
		order.setCreateTime(new Date().getTime());
		iOrderService.createOrder(order);
		
	}
	
}
