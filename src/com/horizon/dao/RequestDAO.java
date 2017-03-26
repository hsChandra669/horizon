package com.horizon.dao;

import java.util.List;

import com.horizon.model.Request;

public interface RequestDAO {
	public void create(Request request);

	public void update(Request request);

    public void delete(int requestId);

    public Request get(int requestId);

    public List<Request> getAllRequest();
}
