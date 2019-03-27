package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.SaleChanceDao;
import com.java1234.entity.SaleChance;
import com.java1234.service.SaleChanceService;

/**
 * 销售机会service实现类
 * @author crash
 *
 */
@Service("saleChanceService")
public class SaleChanceServiceImpl implements SaleChanceService
{
	@Resource
	private SaleChanceDao saleChanceDao;

	@Override
	public List<SaleChance> find(Map<String, Object> map)
	{
		return saleChanceDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return saleChanceDao.getTotal(map);
	}

	@Override
	public int add(SaleChance saleChance)
	{
		return saleChanceDao.add(saleChance);
	}

	@Override
	public int update(SaleChance saleChance)
	{
		return saleChanceDao.update(saleChance);
	}

	@Override
	public int delete(int saleChanceId)
	{
		return saleChanceDao.delete(saleChanceId);
	}

	@Override
	public SaleChance findById(int saleChanceId)
	{
		return saleChanceDao.findById(saleChanceId);
	}

}
