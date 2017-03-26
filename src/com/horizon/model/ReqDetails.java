package com.horizon.model;

public class ReqDetails {
	private int requestDetailId;
	private int requestId;
	private String submitedDate;
	private String rejectedDate;
	private String resubmittedDate;
	private int resubmttedCount;
	private String contactPersonName;
	private String contactPersonNumber;
	private int enabled;
	private int createdBy;
	private String createdOn;
	private String lastUpdatedOn;
	private String estimatedFdaApprovalDate;
	private String fdaApprovalDate;
	
	public ReqDetails() {
		super();
	}

	public ReqDetails(int requestDetailId, int requestId, String submitedDate, String rejectedDate,
			String resubmittedDate, int resubmttedCount, String contactPersonName, String contactPersonNumber,
			int enabled, int createdBy, String createdOn, String lastUpdatedOn, String estimatedFdaApprovalDate,
			String fdaApprovalDate) {
		super();
		this.requestDetailId = requestDetailId;
		this.requestId = requestId;
		this.submitedDate = submitedDate;
		this.rejectedDate = rejectedDate;
		this.resubmittedDate = resubmittedDate;
		this.resubmttedCount = resubmttedCount;
		this.contactPersonName = contactPersonName;
		this.contactPersonNumber = contactPersonNumber;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.lastUpdatedOn = lastUpdatedOn;
		this.estimatedFdaApprovalDate = estimatedFdaApprovalDate;
		this.fdaApprovalDate = fdaApprovalDate;
	}

	public int getRequestDetailId() {
		return requestDetailId;
	}

	public void setRequestDetailId(int requestDetailId) {
		this.requestDetailId = requestDetailId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getSubmitedDate() {
		return submitedDate;
	}

	public void setSubmitedDate(String submitedDate) {
		this.submitedDate = submitedDate;
	}

	public String getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(String rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public String getResubmittedDate() {
		return resubmittedDate;
	}

	public void setResubmittedDate(String resubmittedDate) {
		this.resubmittedDate = resubmittedDate;
	}

	public int getResubmttedCount() {
		return resubmttedCount;
	}

	public void setResubmttedCount(int resubmttedCount) {
		this.resubmttedCount = resubmttedCount;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonNumber() {
		return contactPersonNumber;
	}

	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getEstimatedFdaApprovalDate() {
		return estimatedFdaApprovalDate;
	}

	public void setEstimatedFdaApprovalDate(String estimatedFdaApprovalDate) {
		this.estimatedFdaApprovalDate = estimatedFdaApprovalDate;
	}

	public String getFdaApprovalDate() {
		return fdaApprovalDate;
	}

	public void setFdaApprovalDate(String fdaApprovalDate) {
		this.fdaApprovalDate = fdaApprovalDate;
	}

	@Override
	public String toString() {
		return "ReqDetails [requestDetailId=" + requestDetailId + ", requestId=" + requestId + ", submitedDate="
				+ submitedDate + ", rejectedDate=" + rejectedDate + ", resubmittedDate=" + resubmittedDate
				+ ", resubmttedCount=" + resubmttedCount + ", contactPersonName=" + contactPersonName
				+ ", contactPersonNumber=" + contactPersonNumber + ", enabled=" + enabled + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", lastUpdatedOn=" + lastUpdatedOn + ", estimatedFdaApprovalDate="
				+ estimatedFdaApprovalDate + ", fdaApprovalDate=" + fdaApprovalDate + "]";
	}
	
	
	
}
