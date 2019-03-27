package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.CustomerServiceDao;
import com.java1234.entity.CustomerService;
import com.java1234.service.CustomerServiceService;

/**
 * 客户服务service实现类
 * @author crash
 *
 */
@Service("customerServiceService")
public class CustomerServiceServiceImpl implements CustomerServiceService
{

	@Resource
	private CustomerServiceDao customerServiceDao;
	
	@Override
	public List<CustomerService> find(Map<String,Object> map)
	{
		return customerServiceDao.find(map);
	}
	
	@Override
	public long getTotal(Map<String, Object> map)
	{
		return customerServiceDao.getTotal(map);
	}
	
	@Override
	public int add(CustomerService customerService)
	{
		return customerServiceDao.add(customerService);
	}

	@Override
	public int update(CustomerService customerService)
	{
		return customerServiceDao.update(customerService);
	}

}
