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

import com.java1234.entity.CustomerReprieve;
import com.java1234.service.CustomerReprieveService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 客户流失暂缓措施controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/customerReprieve")
public class CustomerReprieveController
{
	@Resource
	private CustomerReprieveService customerReprieveService;

	/**
	 * 查询客户流失暂缓措施
	 * @param lossId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(@RequestParam(value = "lossId") String lossId,
			HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lossId", lossId);
		List<CustomerReprieve> customerReprieveList = customerReprieveService.find(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "customerLoss" });
		JSONArray jsonArray = JSONArray.fromObject(customerReprieveList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改客户流失暂缓措施集合
	 * 
	 * @param customerReprieve
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(CustomerReprieve customerReprieve, HttpServletResponse response)
			throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if(customerReprieve.getId() != null) 
		{
			modifyNum = customerReprieveService.update(customerReprieve);
		}else 
		{
			modifyNum = customerReprieveService.add(customerReprieve);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除客户流失暂缓措施
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
		customerReprieveService.delete(Integer.parseInt(id));
		result.put("success", true);
		ResponseUtil.write(result, response);
	}

}
