package com.horizon.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.horizon.dao.rowmapper.ProductRowMapper;
import com.horizon.dao.rowmapper.ReqDetailsRowMapper;
import com.horizon.model.ReqDetails;

@Repository("reqDetailsDAO")
public class ReqDetailsDAOImpl implements ReqDetailsDAO {

	
	Logger logger = LogManager.getLogger(ReqDetailsDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	private static final String GET_BY_REQUESTID_QUERY = "SELECT REQUEST_DETAIL_ID, REQUEST_ID, SUBMITED_DATE, REJECTED_DATE , RESUBMITTED_DATE , RESUBMTTED_COUNT , CONTACT_PERSON_NAME , CONTACT_PERSON_NUMBER , ENABLED, "
			+ " CREATED_BY , CREATED_ON , LAST_UPDATED_ON , ESTIMATED_FDA_APPROVAL_DATE , FDA_APPROVAL_DATE  FROM REQUESTS_DETAILS WHERE REQUEST_ID = :requestId";
	
	private static final String DELETE_QUERY = "DELETE FROM  REQUESTS_DETAILS  WHERE REQUEST_ID= :requestId";
	
	@Override
	public void create(ReqDetails reqDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ReqDetails reqDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int reqestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReqDetailsByrequestId(int reqestId) {
		String methodName = "deleteReqDetailsByrequestId(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("requestId", reqestId);
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParams);

	}

	@Override
	public ReqDetails get(int reqestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReqDetails> getAllRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReqDetails> getAllReqDetailsByRequestId(int requestId) {
		String methodName = "getAllReqDetailsByRequestId(reqestId) - ";
		List<ReqDetails> reqDetailsList = new ArrayList<ReqDetails>();
		try {
			logger.entry(methodName + "DAO" + requestId);
			Map<String, Integer> namedParams = Collections.singletonMap("requestId", requestId);
			ReqDetailsRowMapper mapper = new ReqDetailsRowMapper();
			logger.debug(methodName, "mapper - ", namedParams);
			reqDetailsList = this.namedParameterJdbcTemplate.query(GET_BY_REQUESTID_QUERY, namedParams, mapper);
			logger.debug(methodName, "QUERY: Executed ", reqDetailsList);

		} catch (IncorrectResultSizeDataAccessException e) {
		}
		return reqDetailsList;
	}

}
