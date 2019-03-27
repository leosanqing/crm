package com.java1234.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.PageBean;
import com.java1234.entity.SaleChance;
import com.java1234.service.SaleChanceService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 销售机会controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/saleChance")
public class SaleChanceController
{
	@Resource
	private SaleChanceService saleChanceService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 设置严格的数据匹配
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}
	
	/**
	 * 分页查询销售机会，以销售机会名模糊搜索销售机会
	 * @param page
	 * @param rows
	 * @param s_saleChance
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			SaleChance s_saleChance, HttpServletResponse response) throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerName", StringUtil.formatLike(s_saleChance.getCustomerName()));
		map.put("overView", StringUtil.formatLike(s_saleChance.getOverView()));
		map.put("createMan", StringUtil.formatLike(s_saleChance.getCreateMan()));
		map.put("state", s_saleChance.getState());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", s_saleChance.getState());
		map.put("devResult", s_saleChance.getDevResult());
		List<SaleChance> saleChanceList = saleChanceService.find(map);
		Long total = saleChanceService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		JSONArray jsonArray = JSONArray.fromObject(saleChanceList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 添加或修改销售机会
	 * @param saleChance
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(SaleChance saleChance, HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		if(StringUtil.isNotEmpty(saleChance.getAssignMan())) 
		{
			saleChance.setState(1);
		}else 
		{
			saleChance.setState(0);
		}
		saleChance.setDevResult(0);
		int modifyNum;
		if(saleChance.getId() != null) 
		{
			modifyNum = saleChanceService.update(saleChance);
		}else 
		{
			modifyNum = saleChanceService.add(saleChance);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 删除销售机会
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
			saleChanceService.delete(Integer.parseInt(i));
		}
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/findById")
	public void findById(@RequestParam(value = "id", required = true) String id,
			HttpServletResponse response) throws IOException
	{
		SaleChance saleChance = saleChanceService.findById(Integer.parseInt(id));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		JSONObject result = JSONObject.fromObject(saleChance, jsonConfig);
		ResponseUtil.write(result, response);
	}
	
}
