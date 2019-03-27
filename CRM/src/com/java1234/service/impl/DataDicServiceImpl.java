package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.DataDicDao;
import com.java1234.entity.DataDic;
import com.java1234.service.DataDicService;

/**
 * 数据字典service实现类
 * @author crash
 *
 */
@Service("dataDicService")
public class DataDicServiceImpl implements DataDicService
{
	@Resource
	private DataDicDao dataDicDao;

	@Override
	public List<DataDic> find(Map<String, Object> map)
	{
		return dataDicDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map)
	{
		return dataDicDao.getTotal(map);
	}

	@Override
	public List<DataDic> findDataDicName()
	{
		return dataDicDao.findDataDicName();
	}

	@Override
	public int add(DataDic dataDic)
	{
		return dataDicDao.add(dataDic);
	}

	@Override
	public int update(DataDic dataDic)
	{
		return dataDicDao.update(dataDic);
	}

	@Override
	public int delete(int dataDicId)
	{
		return dataDicDao.delete(dataDicId);
	}

}
