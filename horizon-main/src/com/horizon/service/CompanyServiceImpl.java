package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.CompanyDAO;
import com.horizon.model.Company;

@Service ("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyDAO companyDao;

	@Override
	public Company createCompany(Company company) {
		company.setEnabled(1);
		company.setUserID(1);
		companyDao.create(company);
		//Company dbComp = companyDao.getByCompanyName(company.getCompanyName());
		return company;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAllCompanies();
	}

}
