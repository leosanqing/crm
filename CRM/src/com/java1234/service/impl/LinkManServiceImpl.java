package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.LinkManDao;
import com.java1234.entity.LinkMan;
import com.java1234.service.LinkManService;

/**
 * 联系人service实现类
 * @author crash
 *
 */
@Service("linkManService")
public class LinkManServiceImpl implements LinkManService
{

	@Resource
	private LinkManDao linkManDao;
	
	@Override
	public List<LinkMan> find(Map<String, Object> map)
	{
		return linkManDao.find(map);
	}

	@Override
	public int add(LinkMan linkMan)
	{
		return linkManDao.add(linkMan);
	}

	@Override
	public int update(LinkMan linkMan)
	{
		return linkManDao.update(linkMan);
	}

	@Override
	public int delete(int linkManId)
	{
		return linkManDao.delete(linkManId);
	}

}
