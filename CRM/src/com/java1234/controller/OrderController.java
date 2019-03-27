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

import com.java1234.entity.Order;
import com.java1234.service.OrderService;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 历史订单controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController
{
	@Resource
	private OrderService orderService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 设置严格的数据匹配
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}
	
	/**
	 * 查询历史订单
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
		List<Order> orderList = orderService.find(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "customer" });
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(orderList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 通过id查找订单
	 */
	@RequestMapping("/findById")
	public void findById(@RequestParam(value="id") String id, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		Order order = orderService.findById(Integer.parseInt(id));
		result.put("state", order.getState());
		result.put("orderNo", order.getOrderNo());
		result.put("orderDate", DateUtil.formatDate(order.getOrderDate(), "yyyy-MM-dd"));
		result.put("address", order.getAddress());
		ResponseUtil.write(result, response);
	}

}
