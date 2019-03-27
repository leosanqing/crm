package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Product;

/**
 * 销售机会Dao接口
 * @author crash
 *
 */
public interface ProductDao
{
	/**
	 * 查询销售机会集合
	 * @return
	 */
	public List<Product> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
}
