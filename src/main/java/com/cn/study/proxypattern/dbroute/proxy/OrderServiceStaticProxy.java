package com.cn.study.proxypattern.dbroute.proxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.cn.study.proxypattern.dbroute.IOrderService;
import com.cn.study.proxypattern.dbroute.Order;
import com.cn.study.proxypattern.dbroute.db.DynamicDataSourceEntity;

public class OrderServiceStaticProxy implements IOrderService {

	private IOrderService iOrderService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	public OrderServiceStaticProxy (IOrderService iOrderService) {
		this.iOrderService = iOrderService;
	}


	public int createOrder(Order order) {
		Long createTime = order.getCreateTime();
		Integer dbRouter = Integer.valueOf(sdf.format(new Date(createTime)));
		System.out.println("静态代理类自动分配到【DB_" +  dbRouter + "】数据源处理数据" );

		DynamicDataSourceEntity.set(dbRouter);
		int val = this.iOrderService.createOrder(order);
		DynamicDataSourceEntity.restore();

		return val;
	}

}