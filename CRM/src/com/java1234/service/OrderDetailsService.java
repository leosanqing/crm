package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.OrderDetails;

/**
 * 订单详情service接口
 * @author crash
 *
 */
public interface OrderDetailsService
{
	
	/**
	 * 查询订单详情集合
	 * @return
	 */
	public List<OrderDetails> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 获取该订单总价格
	 * @param map
	 * @return
	 */
	public float getTotalPrice(Map<String,Object> map);
	
}
