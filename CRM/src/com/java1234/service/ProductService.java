package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Product;

/**
 * 产品service接口
 * @author crash
 *
 */
public interface ProductService
{
	
	/**
	 * 查询产品集合
	 * @return
	 */
	public List<Product> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
}
