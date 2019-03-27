package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.CustomerLoss;

/**
 * 客户流失Dao接口
 * @author crash
 *
 */
public interface CustomerLossDao
{
	/**
	 * 查询客户流失集合
	 * @return
	 */
	public List<CustomerLoss> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加客户流失记录
	 * @param customer
	 * @return
	 */
	public int add(CustomerLoss customerLoss);
	
	/**
	 * 更新客户流失记录
	 * @param customerLoss
	 * @return
	 */
	public int update(CustomerLoss customerLoss);
	
	/**
	 * 通过Id查找客户流失记录
	 * @param lossId
	 * @return
	 */
	public CustomerLoss findById(int lossId);
}
