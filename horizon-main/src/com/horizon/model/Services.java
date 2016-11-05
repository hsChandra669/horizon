package com.horizon.model;

public class Services {

	private long serviceID;
	private String serviceName;
	private String serviceType;
	private int enabled;
	private int createdBy;
	private String createTS;
	private String lastUpdateTS;

	public Services () {

	}

	public Services(int serviceID, String serviceName, String serviceType, int enabled, int createdBy, String createTS, String lastUpdateTS) {
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createTS = createTS;
		this.lastUpdateTS = lastUpdateTS;
	}

	public long getServiceID() {
		return serviceID;
	}

	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
