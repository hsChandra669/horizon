package com.horizon.service;

import java.util.List;

import com.horizon.model.ReqDetails;
import com.horizon.resources.exception.HnException;

public interface RequestDetailsService {

	public ReqDetails createReqDetails(ReqDetails reqDetail) throws HnException;

	 public List<ReqDetails> getAllReqDetails();

	 public List<ReqDetails> getAllReqDetailsByRequestId(int requestID);

	 public ReqDetails updateProduct(ReqDetails reqDetails);

	 public void deleteReqDetails(int requestID);

	 public void deleteReqDetailsByrequestId(int requestID);
	
}
