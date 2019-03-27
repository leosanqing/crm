package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.CustomerService;

/**
 * 客户服务Dao
 * @author crash
 *
 */
public interface CustomerServiceDao
{
	/**
	 * 查询客户服务集合
	 * @return
	 */
	public List<CustomerService> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String,Object> map);
	
	/**
	 * 客户服务添加
	 * @param customerService
	 * @return
	 */
	public int add(CustomerService customerService);
	
	/**
	 * 客户服务修改
	 * @param customerService
	 * @return
	 */
	public int update(CustomerService customerService);
	
}
