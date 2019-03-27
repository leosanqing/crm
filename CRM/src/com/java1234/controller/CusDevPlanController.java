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

import com.java1234.entity.CusDevPlan;
import com.java1234.entity.SaleChance;
import com.java1234.service.CusDevPlanService;
import com.java1234.service.SaleChanceService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 客户开发计划controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController
{
	@Resource
	private CusDevPlanService cusDevPlanService;

	@Resource
	private SaleChanceService saleChanceService;

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
	 * 查询客户开发计划
	 * @param saleChanceId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(@RequestParam(value = "saleChanceId") String saleChanceId,
			HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("saleChanceId", saleChanceId);
		List<CusDevPlan> cusDevPlanList = cusDevPlanService.find(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "saleChance" });
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(cusDevPlanList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改客户开发计划
	 * 
	 * @param cusDevPlan
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(CusDevPlan cusDevPlan, HttpServletResponse response)
			throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if (cusDevPlan.getId() != null)
		{
			modifyNum = cusDevPlanService.update(cusDevPlan);
		} else
		{
			SaleChance saleChance = new SaleChance();
			saleChance.setId(cusDevPlan.getSaleChance().getId());
			saleChance.setDevResult(1);
			saleChanceService.update(saleChance);
			modifyNum = cusDevPlanService.add(cusDevPlan);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除客户开发计划
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
		cusDevPlanService.delete(Integer.parseInt(id));
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 更改销售机会开发状态
	 * @param id
	 * @param devResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/updateSaleChanceDevResult")
	public void updateSaleChanceDevResult(@RequestParam(value = "id") String id,
			@RequestParam(value = "devResult") String devResult,
			HttpServletResponse response) throws IOException
	{
		SaleChance saleChance = new SaleChance();
		saleChance.setId(Integer.parseInt(id));
		saleChance.setDevResult(Integer.parseInt(devResult));
		saleChanceService.update(saleChance);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, response);

	}

}
