package com.java1234.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.DataDic;
import com.java1234.entity.PageBean;
import com.java1234.service.DataDicService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 数据字典controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/dataDic")
public class DateDicController
{
	@Resource
	private DataDicService dataDicService;

	/**
	 * 分页查询数据字典，以数据字典名模糊搜索数据字典
	 * @param page
	 * @param rows
	 * @param s_dataDic
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			DataDic s_dataDic, HttpServletResponse response) throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataDicValue", StringUtil.formatLike(s_dataDic.getDataDicValue()));
		map.put("dataDicName", s_dataDic.getDataDicName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<DataDic> dataDicList = dataDicService.find(map);
		Long total = dataDicService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(dataDicList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 查找数据字典名集合
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findDataDicName")
	public void findDataDicName(HttpServletResponse response) throws IOException 
	{
		List<DataDic> dataDicList = dataDicService.findDataDicName();
		JSONArray jsonArray = JSONArray.fromObject(dataDicList);
		ResponseUtil.write(jsonArray, response);
	}
	
	/**
	 * 添加或修改数据字典
	 * @param dataDic
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(DataDic dataDic, HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if (dataDic.getId() != null)
		{
			modifyNum = dataDicService.update(dataDic);
		} else
		{
			modifyNum = dataDicService.add(dataDic);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除数据字典
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		String[] id = ids.split(",");
		for (String i : id)
		{
			dataDicService.delete(Integer.parseInt(i));
		}
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/dataDicComboList")
	public void dataDicComboList(@RequestParam(value="dataDicName") String dataDicName, HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataDicName", dataDicName);
		List<DataDic> dataDicList = dataDicService.find(map);
		JSONArray jsonArray = JSONArray.fromObject(dataDicList);
		ResponseUtil.write(jsonArray, response);
	}

}
