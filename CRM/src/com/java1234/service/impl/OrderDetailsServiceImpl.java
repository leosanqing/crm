package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.OrderDetailsDao;
import com.java1234.entity.OrderDetails;
import com.java1234.service.OrderDetailsService;

/**
 * 订单详情service实现类
 * @author crash
 *
 */
@Service("orderDetailsService")
public class OrderDetailsServiceImpl implements OrderDetailsService
{
	@Resource
	private OrderDetailsDao orderDetailsDao;

	@Override
	public List<OrderDetails> find(Map<String, Object> map)
	{
		return orderDetailsDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return orderDetailsDao.getTotal(map);
	}

	@Override
	public float getTotalPrice(Map<String, Object> map)
	{
		return orderDetailsDao.getTotalPrice(map);
	}

}
