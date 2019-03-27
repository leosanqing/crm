package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.LinkMan;

/**
 * 联系人Dao接口
 * @author crash
 *
 */
public interface LinkManDao
{
	/**
	 * 查询联系人集合
	 * @return
	 */
	public List<LinkMan> find(Map<String,Object> map);
	
	/**
	 * 添加联系人
	 * @param linkMan
	 * @return
	 */
	public int add(LinkMan linkMan);
	
	/**
	 * 修改联系人
	 * @param linkMan
	 * @return
	 */
	public int update(LinkMan linkMan);
	
	/**
	 * 删除联系人
	 * @param linkManId
	 * @return
	 */
	public int delete(int linkManId);
	
}
