package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.CompanyDAO;
import com.horizon.model.Company;
import com.horizon.resources.exception.HnException;

@Service ("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyDAO companyDao;

	@Override
	public Company createCompany(Company company) throws HnException {
		Company dbComp = companyDao.getByCompanyName(company.getCompanyName());
		if (dbComp != null) {
			System.out.println("Company already exist");
			throw new HnException("comany.already.exist");
		}

		company.setEnabled(1);
		company.setUserID(1);
		companyDao.create(company);
		dbComp = companyDao.getByCompanyName(company.getCompanyName());
		return dbComp;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAllCompanies();
	}

	@Override
	public Company updateCompany(Company company) {
		Company dbComp;
		companyDao.update(company);
		dbComp = companyDao.getByCompanyName(company.getCompanyName());
		return dbComp;
	}

	@Override
	public void deleteCompany(int companyID) {
		companyDao.delete(companyID);

	}

	@Override
	public List<String> getAllCompanyNames() {
		return companyDao.getAllCompanyNames();
	}

}
