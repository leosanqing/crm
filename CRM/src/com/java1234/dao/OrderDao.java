package com.java1234.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.java1234.entity.Order;

/**
 * 历史订单Dao接口
 * @author crash
 *
 */
public interface OrderDao
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
	
	/**
	 * 获取通过客户id获取客户最后一次下单时间
	 * @return
	 */
	public Date getLastOrderDateByCusId(int cusId);
	
}
