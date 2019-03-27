package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.CustomerLossDao;
import com.java1234.entity.CustomerLoss;
import com.java1234.service.CustomerLossService;

/**
 * 客户流失service实现类
 * @author crash
 *
 */
@Service("customerLossService")
public class CustomerLossServiceImpl implements CustomerLossService
{
	@Resource
	private CustomerLossDao customerLossDao;

	@Override
	public List<CustomerLoss> find(Map<String, Object> map)
	{
		return customerLossDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return customerLossDao.getTotal(map);
	}

	@Override
	public CustomerLoss findById(int lossId)
	{
		return customerLossDao.findById(lossId);
	}

	@Override
	public int update(CustomerLoss customerLoss)
	{
		return customerLossDao.update(customerLoss);
	}

}
