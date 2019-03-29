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

import com.java1234.entity.Customer;
import com.java1234.entity.CustomerFw;
import com.java1234.entity.CustomerGc;
import com.java1234.entity.CustomerGx;
import com.java1234.entity.PageBean;
import com.java1234.service.CustomerService;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 客户controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController
{
	@Resource
	private CustomerService customerService;

	/**
	 * 分页查询客户，以客户名或客户编号模糊搜索客户
	 * 
	 * @param page
	 * @param rows
	 * @param s_customer
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			Customer s_customer, HttpServletResponse response)
			throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("khno", StringUtil.formatLike(s_customer.getKhno()));
		map.put("name", StringUtil.formatLike(s_customer.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Customer> customerList = customerService.find(map);
		Long total = customerService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(customerList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改客户
	 * 
	 * @param customer
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(Customer customer, HttpServletResponse response)
			throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if (customer.getId() != null)
		{
			modifyNum = customerService.update(customer);
		} else
		{
			customer.setKhno("KH" + DateUtil.getCurrentDateStr());
			modifyNum = customerService.add(customer);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除客户
	 * 
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
			customerService.delete(Integer.parseInt(i));
		}
		result.put("success", true);
		ResponseUtil.write(result, response);
	}

	/**
	 * 通过id查找客户
	 */
	@RequestMapping("/findById")
	public void findById(@RequestParam(value = "id") String id,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		Customer customer = customerService.findById(Integer.parseInt(id));
		result.put("khno", customer.getKhno());
		result.put("name", customer.getName());
		ResponseUtil.write(result, response);
	}

	/**
	 * 统计客户贡献
	 * 
	 * @param page
	 * @param rows
	 * @param s_customer
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findCustomerGx")
	public void findCustomerGx(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			Customer s_customer, HttpServletResponse response)
			throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", StringUtil.formatLike(s_customer.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<CustomerGx> customerGxList = customerService.findCustomerGx(map);
		Long total = customerService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(customerGxList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}

	/**
	 * 统计客户构成
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findCustomerGc")
	public void findCustomerGc(HttpServletResponse response) throws IOException
	{
		List<CustomerGc> customerGcList = customerService.findCustomerGc();
		JSONArray jsonArray = JSONArray.fromObject(customerGcList);
		ResponseUtil.write(jsonArray, response);
	}
	
	/**
	 * 统计客户服务类型
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/findCustomerFw")
	public void findCustomerFw(HttpServletResponse response) throws IOException
	{
		List<CustomerFw> customerFwList = customerService.findCustomerFw();
		JSONArray jsonArray = JSONArray.fromObject(customerFwList);
		ResponseUtil.write(jsonArray, response);
	}
}
