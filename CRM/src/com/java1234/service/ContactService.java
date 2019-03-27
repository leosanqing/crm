package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Contact;

/**
 * 交往记录service接口
 * @author crash
 *
 */
public interface ContactService
{
	/**
	 * 查询交往记录集合
	 * @return
	 */
	public List<Contact> find(Map<String,Object> map);
	
	/**
	 * 添加交往记录
	 * @param contact
	 * @return
	 */
	public int add(Contact contact);
	
	/**
	 * 修改交往记录
	 * @param contact
	 * @return
	 */
	public int update(Contact contact);
	
	/**
	 * 删除交往记录
	 * @param contactId
	 * @return
	 */
	public int delete(int contactId);
}
