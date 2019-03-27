package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.DataDic;

/**
 * 数据字典service接口
 * @author crash
 *
 */
public interface DataDicService
{
	
	/**
	 * 查询数据字典集合
	 * @return
	 */
	public List<DataDic> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 查询数据字典名集合
	 * @return
	 */
	public List<DataDic> findDataDicName();
	
	/**
	 * 添加数据字典
	 * @param dataDic
	 * @return
	 */
	public int add(DataDic dataDic);
	
	/**
	 * 修改数据字典
	 * @param dataDic
	 * @return
	 */
	public int update(DataDic dataDic);
	
	/**
	 * 删除数据字典
	 * @param dataDicId
	 * @return
	 */
	public int delete(int dataDicId);
	
}
