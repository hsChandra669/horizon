package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.ServicesDAO;
import com.horizon.model.Services;
import com.horizon.resources.exception.HnException;


@Service ("servicesService")
public class ServicesServiceImpl implements ServicesService{

	@Autowired
	ServicesDAO servicesDAO;



	@Override
	public Services createServices(Services service) throws HnException {
		Services dbService = servicesDAO.getByServiceName(service.getServiceName());
		if (dbService != null) {
			System.out.println("Service already exist");
			throw new HnException("Service.already.exist");
		}

		service.setEnabled(1);
		service.setCreatedBy(1);
		servicesDAO.create(service);
		dbService = servicesDAO.getByServiceName(service.getServiceName());

		return dbService;
	}

	@Override
	public List<Services> getAllServices() {
		return servicesDAO.getAllServices();
	}

	@Override
	public Services updateServices(Services service) {
		Services dbService;
		servicesDAO.update(service);
		dbService = servicesDAO.getByServiceName(service.getServiceName());
		return dbService;
	}

	@Override
	public void deleteServices(int serviceID) {
		servicesDAO.delete(serviceID);
	}

}
