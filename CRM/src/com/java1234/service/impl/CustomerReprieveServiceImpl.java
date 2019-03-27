package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.CustomerReprieveDao;
import com.java1234.entity.CustomerReprieve;
import com.java1234.service.CustomerReprieveService;

/**
 * 客户流失暂缓措施service实现类
 * 
 * @author crash
 *
 */
@Service("customerReprieveService")
public class CustomerReprieveServiceImpl implements CustomerReprieveService
{

	@Resource
	private CustomerReprieveDao customerReprieveDao;

	@Override
	public List<CustomerReprieve> find(Map<String, Object> map)
	{
		return customerReprieveDao.find(map);
	}

	@Override
	public int add(CustomerReprieve customerReprieve)
	{
		return customerReprieveDao.add(customerReprieve);
	}

	@Override
	public int update(CustomerReprieve customerReprieve)
	{
		return customerReprieveDao.update(customerReprieve);
	}

	@Override
	public int delete(int customerReprieveId)
	{
		return customerReprieveDao.delete(customerReprieveId);
	}

}
