package com.horizon.dao;

import java.util.List;

import com.horizon.model.Company;
import com.horizon.model.Services;

public interface ServicesDAO {

	public void create(Services service);

	public void update(Services service);

    public void delete(int serviceID);

    public Services get(int serviceID);

    public List<Services> getAllServices();

    public Services getByServiceName(String serviceName);
}
