package com.horizon.model;

import java.io.InputStream;

public class Document {

	private int documentID;
	private String documentTypeID;
	private String documentName;
	private int companyID;
	private InputStream document;
	private String mimeType;
	private String extensionType;
	private int enabled;
	private int userID;
	private String createTS;
	private String lastUpdateTS;
	private int fileSize;

	public String getExtensionType() {
		return extensionType;
	}
	public void setExtensionType(String extensionType) {
		this.extensionType = extensionType;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getDocumentID() {
		return documentID;
	}
	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}
	public String getDocumentTypeID() {
		return documentTypeID;
	}
	public void setDocumentTypeID(String documentTypeID) {
		this.documentTypeID = documentTypeID;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public InputStream getDocument() {
		return document;
	}
	public void setDocument(InputStream document) {
		this.document = document;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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
		return "Document [documentID=" + documentID + ", documentTypeID=" + documentTypeID + ", documentName="
				+ documentName + ", companyID=" + companyID + ", document=" + document + ", extensionTYpe="
				+ mimeType + ", enabled=" + enabled + ", userID=" + userID + ", createTS=" + createTS
				+ ", lastUpdateTS=" + lastUpdateTS + "]";
	}


}
