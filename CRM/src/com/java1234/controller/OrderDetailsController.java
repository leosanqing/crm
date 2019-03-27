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

import com.java1234.entity.OrderDetails;
import com.java1234.entity.PageBean;
import com.java1234.service.OrderDetailsService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单详情controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/orderDetails")
public class OrderDetailsController
{
	@Resource
	private OrderDetailsService orderDetailsService;

	/**
	 * 分页查询订单详情
	 * @param page
	 * @param rows
	 * @param orderId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "orderId") String orderId, HttpServletResponse response) throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", Integer.parseInt(orderId));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<OrderDetails> orderDetailsList = orderDetailsService.find(map);
		Long total = orderDetailsService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(orderDetailsList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 通过订单id获取订单总金额
	 * @param orderId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTotalPrice")
	public void getTotalPrice(@RequestParam(value = "orderId") String orderId, HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", Integer.parseInt(orderId));
		float totalMoney = orderDetailsService.getTotal(map);
		JSONObject result = new JSONObject();
		result.put("totalMoney", totalMoney);
		ResponseUtil.write(result, response);
	}
	
}
