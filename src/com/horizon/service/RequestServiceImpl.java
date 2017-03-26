package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.RequestDAO;
import com.horizon.model.Request;
import com.horizon.resources.exception.HnException;

@Service ("requestService")
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestDAO requestDao;
	
	@Override
	public Request createRequest(Request request) throws HnException {
		Request dbComp = requestDao.get(request.getRequestId());
		if (dbComp != null) {
			System.out.println("Request already exist");
			throw new HnException("Request.already.exist");
		}

		requestDao.create(request);
		dbComp = requestDao.get(request.getRequestId());
		return dbComp;
	}

	@Override
	public List<Request> getAllRequests() {
		return requestDao.getAllRequest();
	}

	@Override
	public Request updateRequest(Request request) {
		Request dbComp;
		requestDao.update(request);
		dbComp = requestDao.get(request.getRequestId());
		return dbComp;
	}

	@Override
	public void deleteRequest(int requestID) {
		requestDao.delete(requestID);

	}

	@Override
	public Request getRequest(int requestID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
