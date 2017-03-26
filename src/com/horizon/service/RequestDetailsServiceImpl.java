package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.ReqDetailsDAO;
import com.horizon.model.ReqDetails;
import com.horizon.resources.exception.HnException;

@Service ("requestDetailsService")
public class RequestDetailsServiceImpl implements RequestDetailsService {

	@Autowired
	ReqDetailsDAO reqDetailsDAO;

	
	@Override
	public ReqDetails createReqDetails(ReqDetails reqDetail) throws HnException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReqDetails> getAllReqDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReqDetails> getAllReqDetailsByRequestId(int requestID) {
		return reqDetailsDAO.getAllReqDetailsByRequestId(requestID);
	}

	@Override
	public ReqDetails updateProduct(ReqDetails reqDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReqDetails(int requestID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReqDetailsByrequestId(int requestID) {
		reqDetailsDAO.deleteReqDetailsByrequestId(requestID);

	}

}
