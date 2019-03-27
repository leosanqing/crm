package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.CusDevPlan;

public interface CusDevPlanDao
{
	/**
	 * 查询客户开发计划集合
	 * @return
	 */
	public List<CusDevPlan> find(Map<String,Object> map);
	
	/**
	 * 添加客户开发计划
	 * @param cusDevPlan
	 * @return
	 */
	public int add(CusDevPlan cusDevPlan);
	
	/**
	 * 修改客户开发计划
	 * @param cusDevPlan
	 * @return
	 */
	public int update(CusDevPlan cusDevPlan);
	
	/**
	 * 删除客户开发计划
	 * @param cusDevPlanId
	 * @return
	 */
	public int delete(int cusDevPlanId);
}
