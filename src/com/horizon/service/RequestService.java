package com.horizon.service;

import java.util.List;

import com.horizon.model.Request;
import com.horizon.resources.exception.HnException;

public interface RequestService {
	public Request createRequest(Request request) throws HnException;

	 public List<Request> getAllRequests();

	 public Request updateRequest(Request request);

	 public void deleteRequest(int requestID);
	 
	 public Request getRequest(int requestID);

}
