package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.CustomerReprieve;

/**
 * 客户流失暂缓措施service接口
 * @author crash
 *
 */
public interface CustomerReprieveService
{
	/**
	 * 查询客户流失暂缓措施集合
	 * @return
	 */
	public List<CustomerReprieve> find(Map<String,Object> map);
	
	/**
	 * 添加客户流失暂缓措施
	 * @param customerReprieve
	 * @return
	 */
	public int add(CustomerReprieve customerReprieve);
	
	/**
	 * 修改客户流失暂缓措施
	 * @param customerReprieve
	 * @return
	 */
	public int update(CustomerReprieve customerReprieve);
	
	/**
	 * 删除客户流失暂缓措施
	 * @param customerReprieveId
	 * @return
	 */
	public int delete(int customerReprieveId);
}
