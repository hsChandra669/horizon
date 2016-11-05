package com.horizon.model;

public class Product {

	private long productID;
	private String name;
	private String productType;
	private int enabled;
	private int userID;
	private String createTS;
	private String lastUpdateTS;

	public Product () {

	}

	public Product(int productID, String name, String productType, int enabled, int userID, String createTS, String lastUpdateTS) {
		this.productID = productID;
		this.name = name;
		this.productType = productType;
		this.enabled = enabled;
		this.userID = userID;
		this.createTS = createTS;
		this.lastUpdateTS = lastUpdateTS;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getproductType() {
		return productType;
	}

	public void setproductType(String productType) {
		this.productType = productType;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public long getproductID() {
		return productID;
	}

	public void setproductID(long productID) {
		this.productID = productID;
	}

	public String getCreateTS() {
		return createTS;
	}

	public void setCreateTS(String createTS) {
		this.createTS = createTS;
	}

	public String getLastUpdateTS() {
		return lastUpdateTS;
	}

	public void setLastUpdateTS(String lastUpdateTS) {
		this.lastUpdateTS = lastUpdateTS;
	}


}
