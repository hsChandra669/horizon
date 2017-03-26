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

import com.horizon.dao.rowmapper.CompanyRowMapper;
import com.horizon.dao.rowmapper.RequestRowMapper;
import com.horizon.model.Company;
import com.horizon.model.Request;
@Repository("requestDAO")
public class RequestDAOImple implements RequestDAO {

	Logger logger = LogManager.getLogger(RequestDAOImple.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	private static final String GET_ALL_QUERY = "SELECT REQUEST_ID, DESCRIPTIONS, SERVICE_ID, COMPANY_ID, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON from REQUESTS";
	
	private static final String GET_REQUEST = "SELECT REQUEST_ID, DESCRIPTIONS, SERVICE_ID, COMPANY_ID, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON from REQUESTS where REQUEST_ID= :requestId";
	
	private static final String INSERT_QUERY = "INSERT INTO REQUESTS "
			+ " (REQUEST_ID, DESCRIPTIONS, SERVICE_ID, COMPANY_ID, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:requestId, :desc, :serviceId, :companyId, :createdBy, :createTS, :lastUpdateTS)";

	private static final String UPDATE_QUERY = "UPDATE REQUESTS SET SERVICE_ID = :serviceId, COMPANY_ID = :companyId, DESCRIPTIONS = :desc, "
			+ " LAST_UPDATED_ON = :lastUpdateTS where REQUEST_ID = :requestId";

	private static final String DELETE_QUERY = "DELETE FROM  REQUESTS  WHERE REQUEST_ID= :requestId";
	
	@Override
	public void create(Request request) {
		String methodName = "create ";
		logger.info(methodName + request);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("requestId", request.getRequestId());
		parameters.put("desc", request.getDesc());
		parameters.put("serviceId", request.getServiceId());
		parameters.put("companyId", request.getCompanyId());		
		parameters.put("createdBy", request.getCreatedBy());		
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}

	@Override
	public void update(Request request) {
		String methodName = "update ";
		logger.info(methodName + request);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("desc", request.getDesc());
		parameters.put("serviceId", request.getServiceId());
		parameters.put("companyId", request.getCompanyId());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("lastUpdateTS", new Timestamp(milisec));
		parameters.put("requestId", request.getRequestId());

		namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters);

	}

	@Override
	public void delete(int requestId) {
		String methodName = "delete(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("requestId", requestId);
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParams);

	}

	@Override
	public Request get(int requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> getAllRequest() {
		String methodName = "getAllRequest()";
		logger.debug(methodName, "QUERY: Executing - " + GET_ALL_QUERY);
		RequestRowMapper mapper = new RequestRowMapper();
		List<Request> requestList = new ArrayList<Request>();
		requestList = this.jdbcTemplate.query(GET_ALL_QUERY, mapper);
		logger.debug(methodName, "QUERY: Executed - " + requestList);
		return requestList;
	}

}
