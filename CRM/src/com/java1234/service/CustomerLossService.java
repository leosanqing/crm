package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.CustomerLoss;

/**
 * 客户流失service接口
 * @author crash
 *
 */
public interface CustomerLossService
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
	 * 通过Id查找客户流失记录
	 * @param lossId
	 * @return
	 */
	public CustomerLoss findById(int lossId);
	
	/**
	 * 修改客户流失记录
	 * @param customerLoss
	 * @return
	 */
	public int update(CustomerLoss customerLoss);
	
}
