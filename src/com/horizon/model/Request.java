package com.horizon.model;

public class Request {
	
	private int requestId;
	private String desc;
	private int serviceId;
	private int companyId;
	private int createdBy;
	private String createTS;
	private String lastUpdateTS;
	
	public Request() {
		super();
	}
	public Request(int requestId, String desc, int serviceId, int companyId, int createdBy, String createTS,
			String lastUpdateTS) {
		super();
		this.requestId = requestId;
		this.desc = desc;
		this.serviceId = serviceId;
		this.companyId = companyId;
		this.createdBy = createdBy;
		this.createTS = createTS;
		this.lastUpdateTS = lastUpdateTS;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	
	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", desc=" + desc + ", serviceId=" + serviceId + ", companyId="
				+ companyId + ", createdBy=" + createdBy + ", createTS=" + createTS + ", lastUpdateTS=" + lastUpdateTS
				+ "]";
	}
	
	
}
