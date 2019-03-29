package com.java1234.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.CustomerService;
import com.java1234.entity.PageBean;
import com.java1234.service.CustomerServiceService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 客户服务controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/customerService")
public class CustomerServiceController
{
	@Resource
	private CustomerServiceService customerServiceService;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 设置严格的数据匹配
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}

	/**
	 * 查询客户服务集合
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			String createTimefrom, String createTimeto,
			CustomerService s_customerService, HttpServletResponse response, HttpServletRequest request)
			throws Exception
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customer",
				StringUtil.formatLike(s_customerService.getCustomer()));
		map.put("overview",
				StringUtil.formatLike(s_customerService.getOverview()));
		map.put("serveType", s_customerService.getServeType());

		map.put("state", new String(s_customerService.getState().getBytes("ISO8859-1"),"UTF-8"));
		map.put("createTimefrom", createTimefrom);
		map.put("createTimeto", createTimeto);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<CustomerService> customerServiceList = customerServiceService
				.find(map);
		Long total = customerServiceService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(customerServiceList,
				jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
		return null;
	}

	/**
	 * 客户服务删除或修改
	 * @param customerService
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(CustomerService customerService,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if (customerService.getId() != null)
		{
			modifyNum = customerServiceService.update(customerService);
		} else
		{
			modifyNum = customerServiceService.add(customerService);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}
	
}
