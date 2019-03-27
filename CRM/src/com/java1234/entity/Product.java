package com.java1234.entity;

/**
 * 产品实体
 * @author crash
 *
 */
public class Product
{
	private Integer id;
	private String productName;
	private String model;
	private String unit;
	private float price;
	private Integer store;
	private String remark;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public Integer getStore()
	{
		return store;
	}

	public void setStore(Integer store)
	{
		this.store = store;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}
