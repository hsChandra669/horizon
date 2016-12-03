package com.horizon.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Product {

	private int productID;
	@NotEmpty
	private String productName;
	private String productType;
	private int companyID;
	private int enabled;
	private int userID;
	private String createTS;
	private String lastUpdateTS;
	private String companyName;

	public Product () {

	}

	public Product(int productID, String productName, String productType, int companyID, int enabled, int userID, String createTS,
			String lastUpdateTS, String companyName) {
		this.productID = productID;
		this.productName = productName;
		this.productType = productType;
		this.companyID = companyID;
		this.enabled = enabled;
		this.userID = userID;
		this.createTS = createTS;
		this.lastUpdateTS = lastUpdateTS;
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
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

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productType=" + productType
				+ ", companyID=" + companyID + ", enabled=" + enabled + ", userID=" + userID + ", createTS=" + createTS
				+ ", lastUpdateTS=" + lastUpdateTS + ", companyName=" + companyName + "]";
	}



}
