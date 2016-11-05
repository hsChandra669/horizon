package com.horizon.model;

public class Payments {

	private long paymentID;
	private long paymentBy; //companyId
	private String paymentType;
	private long instalmentCount;
	private long amount;
	private String currencyType;
	private String status;
	private int enabled;
	private int createdBy;
	private String createTS;
	private String lastUpdateTS;

	public Payments () {

	}

	public Payments(long paymentID, long paymentBy, String paymentType,
			long instalmentCount, long amount, String currencyType,
			String status, int enabled, int createdBy, String createTS,
			String lastUpdateTS) {
		super();
		this.paymentID = paymentID;
		this.paymentBy = paymentBy;
		this.paymentType = paymentType;
		this.instalmentCount = instalmentCount;
		this.amount = amount;
		this.currencyType = currencyType;
		this.status = status;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createTS = createTS;
		this.lastUpdateTS = lastUpdateTS;
	}

	public long getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}

	public long getPaymentBy() {
		return paymentBy;
	}

	public void setPaymentBy(long paymentBy) {
		this.paymentBy = paymentBy;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public long getInstalmentCount() {
		return instalmentCount;
	}

	public void setInstalmentCount(long instalmentCount) {
		this.instalmentCount = instalmentCount;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
