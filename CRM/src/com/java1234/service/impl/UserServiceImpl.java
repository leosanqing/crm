package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.UserDao;
import com.java1234.entity.User;
import com.java1234.service.UserService;

/**
 * 用户service实现类
 * @author crash
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao userDao;

	@Override
	public User login(User user)
	{
		return userDao.login(user);
	}

	@Override
	public List<User> find(Map<String, Object> map)
	{
		return userDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return userDao.getTotal(map);
	}

	@Override
	public int add(User user)
	{
		return userDao.add(user);
	}

	@Override
	public int update(User user)
	{
		return userDao.update(user);
	}

	@Override
	public int delete(int userId)
	{
		return userDao.delete(userId);
	}

	@Override
	public User findById(int userId)
	{
		return userDao.findById(userId);
	}
}
