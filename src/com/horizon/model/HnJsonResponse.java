package com.horizon.model;

import java.util.Map;

public class HnJsonResponse{

    private String status;
    private Map<String,String> errorsMap;
    private Object object;

    public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Map<String,String> getErrorsMap() {
        return errorsMap;
    }
    public void setErrorsMap(Map<String,String> errorsMap) {
        this.errorsMap = errorsMap;
    }

}