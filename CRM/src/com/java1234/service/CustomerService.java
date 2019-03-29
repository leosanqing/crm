package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Customer;
import com.java1234.entity.CustomerFw;
import com.java1234.entity.CustomerGc;
import com.java1234.entity.CustomerGx;

/**
 * 客户service接口
 * @author crash
 *
 */
public interface CustomerService
{
	
	/**
	 * 查询客户集合
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加客户
	 * @param customer
	 * @return
	 */
	public int add(Customer customer);
	
	/**
	 * 修改客户
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * 删除客户
	 * @param customerId
	 * @return
	 */
	public int delete(int customerId);
	
	/**
	 * 通过id查找客户
	 * @param id
	 * @return
	 */
	public Customer findById(int id);
	
	/**
	 * 添加流失客户记录
	 */
	public void addLossCustomer();
	
	/**
	 * 统计客户贡献
	 * @param map
	 * @return
	 */
	public List<CustomerGx> findCustomerGx(Map<String,Object> map);
	
	/**
	 * 统计客户构成
	 * @return
	 */
	public List<CustomerGc> findCustomerGc();
	
	/**
	 * 统计客户服务类型
	 * @return
	 */
	public List<CustomerFw> findCustomerFw();
	
}
