package com.horizon.dao;

import java.util.List;

import com.horizon.model.Company;

public interface CompanyDAO {

	public void create(Company company);

	public void update(Company company);

    public void delete(int companyID);

    public Company get(int companyID);

    public List<Company> getAllCompanies();

    public Company getByCompanyName(String name);
}
