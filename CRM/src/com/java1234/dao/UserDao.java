package com.java1234.dao;

import java.util.List;
import java.util.Map;

import com.java1234.entity.User;

/**
 * 用户Dao接口
 * @author crash
 *
 */
public interface UserDao
{
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 查询用户集合
	 * @return
	 */
	public List<User> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int add(User user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public int delete(int userId);
	
	/**
	 * 通过Id查找用户
	 * @param userId
	 * @return
	 */
	public User findById(int userId);
	
}
