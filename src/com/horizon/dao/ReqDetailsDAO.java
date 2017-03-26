package com.horizon.dao;

import java.util.List;

import com.horizon.model.ReqDetails;

public interface ReqDetailsDAO {
	
	public void create(ReqDetails reqDetails);

	public void update(ReqDetails reqDetails);

    public void delete(int reqestId);

    public void deleteReqDetailsByrequestId(int reqestId);

    public ReqDetails get(int reqestId);

    public List<ReqDetails> getAllRequests();

    public List<ReqDetails> getAllReqDetailsByRequestId(int reqestId);
}
