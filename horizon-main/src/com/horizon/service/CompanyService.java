package com.horizon.service;

import java.util.List;

import com.horizon.model.Company;
import com.horizon.resources.exception.HnException;

public interface CompanyService {

	 public Company createCompany(Company company) throws HnException;

	 public List<Company> getAllCompanies();

	 public Company updateCompany(Company company);

	 public void deleteCompany(int companyID);

}
