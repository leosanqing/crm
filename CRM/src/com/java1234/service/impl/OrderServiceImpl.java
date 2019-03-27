package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.OrderDao;
import com.java1234.entity.Order;
import com.java1234.service.OrderService;

/**
 * 历史订单service实现类
 * @author crash
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService
{

	@Resource
	private OrderDao orderDao;
	
	@Override
	public List<Order> find(Map<String, Object> map)
	{
		return orderDao.find(map);
	}

	@Override
	public Order findById(int id)
	{
		return orderDao.findById(id);
	}

}
