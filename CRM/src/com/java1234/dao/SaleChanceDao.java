package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.SaleChance;

/**
 * 产品Dao接口
 * @author crash
 *
 */
public interface SaleChanceDao
{
	/**
	 * 查询产品集合
	 * @return
	 */
	public List<SaleChance> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加销售机会
	 * @param saleChance
	 * @return
	 */
	public int add(SaleChance saleChance);
	
	/**
	 * 修改销售机会
	 * @param saleChance
	 * @return
	 */
	public int update(SaleChance saleChance);
	
	/**
	 * 删除销售机会
	 * @param saleChanceId
	 * @return
	 */
	public int delete(int saleChanceId);
	
	/**
	 * 通过id查找销售机会
	 * @param saleChanceId
	 * @return
	 */
	public SaleChance findById(int saleChanceId);
	
}
