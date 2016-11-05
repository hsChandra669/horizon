package com.harizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harizon.dao.CompanyDAO;
import com.harizon.dao.ServicesDAO;
import com.horizon.model.Company;
import com.horizon.model.Services;

@Service ("servicesService")
public class ServicesServiceImpl implements ServicesService{

	@Autowired
	ServicesDAO servicesDAO;



	@Override
	public Services createServices(Services service) {
		// TODO Auto-generated method stub
		servicesDAO.create(service);
		return service;
	}

	@Override
	public List<Services> getAllServices() {
		// TODO Auto-generated method stub
		return servicesDAO.getAllServices();
	}

}
