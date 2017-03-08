package com.horizon.service;

import java.util.List;

import com.horizon.model.Services;
import com.horizon.resources.exception.HnException;

public interface ServicesService {

	 public Services createServices(Services service) throws HnException;

	 public List<Services> getAllServices();

	 public Services updateServices(Services service);

	 public void deleteServices(int serviceID);

}
