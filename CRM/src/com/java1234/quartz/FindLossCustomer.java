package com.java1234.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.java1234.service.CustomerService;

@Component
public class FindLossCustomer
{

	@Resource
	private CustomerService customerService;
	
	// 每天凌晨两点扫描一次
	@Scheduled(cron="0 0 2 * * ?")
	public void work() 
	{
		customerService.addLossCustomer();
	}
	
}
