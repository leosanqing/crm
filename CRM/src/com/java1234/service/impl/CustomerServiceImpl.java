package com.java1234.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.CustomerDao;
import com.java1234.dao.CustomerLossDao;
import com.java1234.dao.OrderDao;
import com.java1234.entity.Customer;
import com.java1234.entity.CustomerFw;
import com.java1234.entity.CustomerGc;
import com.java1234.entity.CustomerGx;
import com.java1234.entity.CustomerLoss;
import com.java1234.service.CustomerService;

/**
 * 客户service实现类
 * @author crash
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService
{

	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private CustomerLossDao customerLossDao;
	
	@Override
	public List<Customer> find(Map<String, Object> map)
	{
		return customerDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return customerDao.getTotal(map);
	}

	@Override
	public int add(Customer customer)
	{
		return customerDao.add(customer);
	}

	@Override
	public int update(Customer customer)
	{
		return customerDao.update(customer);
	}

	@Override
	public int delete(int customerId)
	{
		return customerDao.delete(customerId);
	}

	@Override
	public Customer findById(int id)
	{
		return customerDao.findById(id);
	}

	@Override
	public void addLossCustomer()
	{
		List<Customer> customerList = customerDao.findLossCustomer();
		for(Customer c : customerList) 
		{
			CustomerLoss customerLoss = new CustomerLoss();
			customerLoss.setCusNo(c.getKhno());
			customerLoss.setCusName(c.getName());
			customerLoss.setCusManager(c.getCusManager());
			Date lastDate = orderDao.getLastOrderDateByCusId(c.getId());
			if(lastDate == null) 
			{
				customerLoss.setLastOrderTime(null);
			}else 
			{
				customerLoss.setLastOrderTime(lastDate);
			}
			c.setState(1);
			customerDao.update(c);
			customerLossDao.add(customerLoss);
		}
	}

	@Override
	public List<CustomerGx> findCustomerGx(Map<String, Object> map)
	{
		return customerDao.findCustomerGx(map);
	}

	@Override
	public List<CustomerGc> findCustomerGc()
	{
		return customerDao.findCustomerGc();
	}

	@Override
	public List<CustomerFw> findCustomerFw()
	{
		return customerDao.findCustomerFw();
	}

}
