package com.harizon.service;

import java.util.List;

import com.horizon.model.Company;

public interface CompanyService {

	 public Company createCompany(Company company);

	 public List<Company> getAllCompanies();

}
