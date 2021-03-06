package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Order;

/**
 * 历史订单service接口
 * @author crash
 *
 */
public interface OrderService
{
	/**
	 * 查询历史订单集合
	 * @return
	 */
	public List<Order> find(Map<String,Object> map);
	
	/**
	 * 通过id查找订单
	 * @param id
	 * @return
	 */
	public Order findById(int id);
}
