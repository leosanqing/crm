package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.CusDevPlanDao;
import com.java1234.entity.CusDevPlan;
import com.java1234.service.CusDevPlanService;

/**
 * 客户开发计划service实现类
 * @author crash
 *
 */
@Service("/cusDevPlanService")
public class CusDevPlanServiceImpl implements CusDevPlanService
{
	
	@Resource
	private CusDevPlanDao cusDevPlanDao;

	@Override
	public List<CusDevPlan> find(Map<String, Object> map)
	{
		return cusDevPlanDao.find(map);
	}

	@Override
	public int add(CusDevPlan cusDevPlan)
	{
		return cusDevPlanDao.add(cusDevPlan);
	}

	@Override
	public int update(CusDevPlan cusDevPlan)
	{
		return cusDevPlanDao.update(cusDevPlan);
	}

	@Override
	public int delete(int cusDevPlanId)
	{
		return cusDevPlanDao.delete(cusDevPlanId);
	}

}
