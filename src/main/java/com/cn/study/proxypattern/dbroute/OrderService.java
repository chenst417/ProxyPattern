package com.cn.study.proxypattern.dbroute;

public class OrderService implements IOrderService {

	private OrderDao orderDao;

	public OrderService() {
		orderDao = new OrderDao();
	}

	public int createOrder(Order order) {
		System.out.println("OrderService调用orderDao创建订单");
		return orderDao.insert(order);
	}

}
