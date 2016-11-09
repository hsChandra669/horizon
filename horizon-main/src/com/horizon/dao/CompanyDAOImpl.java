package com.horizon.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.horizon.dao.rowmapper.CompanyRowMapper;
import com.horizon.model.Company;
import com.horizon.resources.exception.HnException;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO{

	Logger logger = LogManager.getLogger(CompanyDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	private static final String GET_ALL_QUERY = "SELECT COMPANY_ID, NAME, ADDRESS, CITY, ENABLED, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON from company";

	private static final String GET_BY_NAME_QUERY = "SELECT COMPANY_ID, NAME, ADDRESS, CITY, CREATED_ON,"
			+ " LAST_UPDATED_ON, ENABLED, CREATED_BY from company"
			+ "  where NAME = :companyName";

	private static final String INSERT_QUERY = "INSERT INTO company "
			+ " (ADDRESS, NAME, CITY, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:address, :name, :city, :enabled, :createdBy, :createTS, :lastUpdateTS)";

	private static final String UPDATE_QUERY = "UPDATE company SET NAME = :name, ADDRESS = :address, CITY = :city, "
			+ " LAST_UPDATED_ON = :lastUpdateTS where COMPANY_ID = :companyid";

	private static final String DELETE_QUERY = "DELETE FROM  company  WHERE COMPANY_ID= :companyid";

	@Override
	public void create(Company company) {
		String methodName = "create ";
		logger.info(methodName + company);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", company.getAddress());
		parameters.put("name", company.getCompanyName());
		parameters.put("city", company.getCity());
		parameters.put("enabled", company.getEnabled());
		parameters.put("createdBy", company.getUserID());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}

	@Override
	public void update(Company company) {
		String methodName = "update ";
		logger.info(methodName + company);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", company.getAddress());
		parameters.put("name", company.getCompanyName());
		parameters.put("city", company.getCity());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("lastUpdateTS", new Timestamp(milisec));
		parameters.put("companyid", company.getCompanyID());

		namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters);

	}

	@Override
	public void delete(int companyID) {
		String methodName = "delete(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("companyid", companyID);
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParams);
	}

	@Override
	public Company get(int companyID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		String methodName = "getAllCompanies()";
		logger.debug(methodName, "QUERY: Executing - " + GET_ALL_QUERY);
		CompanyRowMapper mapper = new CompanyRowMapper();
		List<Company> companyList = new ArrayList<Company>();
		companyList = this.jdbcTemplate.query(GET_ALL_QUERY, mapper);
		logger.debug(methodName, "QUERY: Executed - " + companyList);
		return companyList;
	}

	@Override
	public Company getByCompanyName(String name) {
		String methodName = "getByCompanyName(name) - ";
		logger.entry(methodName + name);
		Company company = null;
		try {
			Map<String, String> namedParams = Collections.singletonMap("companyName", name);
			CompanyRowMapper mapper = new CompanyRowMapper();
			logger.debug(methodName, "mapper - ", namedParams);
			company = this.namedParameterJdbcTemplate.queryForObject(GET_BY_NAME_QUERY, namedParams, mapper);
			logger.debug(methodName, "QUERY: Executed ", company);

		} catch (IncorrectResultSizeDataAccessException e) {
			company = null;
			// TODO: handle exception
		}
		return company;
	}
}
