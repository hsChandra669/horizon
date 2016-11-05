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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.horizon.dao.rowmapper.ProductRowMapper;
import com.horizon.dao.rowmapper.ServicesRowMapper;
import com.horizon.model.Product;
import com.horizon.model.Services;

@Repository("servicesDAO")
public class ServicesDAOImpl implements ServicesDAO{

	Logger logger = LogManager.getLogger(ServicesDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	private static final String GET_ALL_QUERY = "SELECT SERVICE_ID, SERVICE_NAME, SERVICE_TYPE, ENABLED, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON FROM SERVICES";

	private static final String GET_BY_NAME_QUERY = "SELECT SERVICE_ID, SERVICE_TYPE,CREATED_BY, CREATED_ON,  LAST_UPDATED_ON FROM SERVICES"
			+ "  WHERE SERVICE_NAME = :serviceName";

	private static final String INSERT_QUERY = "INSERT INTO SERVICES "
			+ " (SERVICE_NAME, SERVICE_TYPE, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:serviceName, :serviceType, :enabled, :createdBy, :createTS, :lastUpdateTS)";

	@Override
	public void create(Services service) {
		String methodName = "create ";
		logger.info(methodName + service);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("serviceName", service.getServiceName());
		parameters.put("serviceType", service.getServiceType());
		parameters.put("enabled", service.getEnabled());
		parameters.put("createdBy", service.getCreatedBy());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}

	@Override
	public void update(Services product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int productID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Services get(int productID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Services> getAllServices() {
		String methodName = "getAllCompanies()";
		logger.debug(methodName, "QUERY: Executing - " + GET_ALL_QUERY);
		ServicesRowMapper mapper = new ServicesRowMapper();
		List<Services> servicesList = new ArrayList<Services>();
		servicesList = this.jdbcTemplate.query(GET_ALL_QUERY, mapper);
		logger.debug(methodName, "QUERY: Executed - " + servicesList);
		return servicesList;
	}

	@Override
	public Services getByServiceName(String name) {
		String methodName = "getByProductName(name) - ";
		logger.entry(methodName + name);
		Services service = null;
		Map<String, String> namedParams = Collections.singletonMap("productName", name);
		ServicesRowMapper mapper = new ServicesRowMapper();
		logger.debug(methodName, "mapper - ", namedParams);
		service = this.namedParameterJdbcTemplate.queryForObject(GET_BY_NAME_QUERY, namedParams, mapper);
		logger.debug(methodName, "QUERY: Executed ", service);
		return service;
	}


	
}
