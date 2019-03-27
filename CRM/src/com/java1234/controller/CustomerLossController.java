package com.java1234.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.CustomerLoss;
import com.java1234.entity.PageBean;
import com.java1234.service.CustomerLossService;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 客户流失controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController
{
	@Resource
	private CustomerLossService customerLossService;

	/**
	 * 分页查询客户流失，以客户流失名模糊搜索客户流失
	 * @param page
	 * @param rows
	 * @param s_customerLoss
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			CustomerLoss s_customerLoss, HttpServletResponse response) throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusName", StringUtil.formatLike(s_customerLoss.getCusName()));
		map.put("cusManager", StringUtil.formatLike(s_customerLoss.getCusManager()));
		map.put("state",s_customerLoss.getState());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<CustomerLoss> customerLossList = customerLossService.find(map);
		Long total = customerLossService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(customerLossList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 通过Id查找客户流失记录
	 * @param lossId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findById")
	public void findById(@RequestParam(value="lossId") String lossId, HttpServletResponse response) throws IOException 
	{
		CustomerLoss customerLoss = customerLossService.findById(Integer.parseInt(lossId));
		JSONObject result = new JSONObject();
		result.put("cusNo", customerLoss.getCusNo());
		result.put("cusName", customerLoss.getCusName());
		result.put("cusManager", customerLoss.getCusManager());
		result.put("lastOrderTime", DateUtil.formatDate(customerLoss.getLastOrderTime(), "yyyy-MM-dd"));
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/confirmLoss")
	public void confirmLoss(@RequestParam(value="lossId") String lossId, @RequestParam(value="lossReason") String lossReason, HttpServletResponse response) throws IOException  
	{
		CustomerLoss customerLoss = customerLossService.findById(Integer.parseInt(lossId));
		customerLoss.setConfirmLossTime(new Date());
		customerLoss.setLossReason(lossReason);
		customerLoss.setState(1);
		customerLossService.update(customerLoss);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
}
