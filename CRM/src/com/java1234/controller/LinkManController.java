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

import com.java1234.entity.LinkMan;
import com.java1234.service.LinkManService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 联系人controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/linkMan")
public class LinkManController
{
	@Resource
	private LinkManService linkManService;

	/**
	 * 查询联系人
	 * @param cusId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(@RequestParam(value = "cusId") String cusId,
			HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusId", cusId);
		List<LinkMan> linkManList = linkManService.find(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "customer" });
		JSONArray jsonArray = JSONArray.fromObject(linkManList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改联系人
	 * 
	 * @param linkMan
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(LinkMan linkMan, HttpServletResponse response)
			throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if(linkMan.getId() != null) 
		{
			modifyNum = linkManService.update(linkMan);
		}else 
		{
			modifyNum = linkManService.add(linkMan);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除联系人
	 * 
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "id") String id,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		linkManService.delete(Integer.parseInt(id));
		result.put("success", true);
		ResponseUtil.write(result, response);
	}

}
