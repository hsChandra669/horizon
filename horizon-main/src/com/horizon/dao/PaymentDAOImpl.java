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

import com.horizon.dao.rowmapper.PaymentRowMapper;
import com.horizon.dao.rowmapper.ProductRowMapper;
import com.horizon.dao.rowmapper.ServicesRowMapper;
import com.horizon.model.Payments;
import com.horizon.model.Product;
import com.horizon.model.Services;

@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO{

	Logger logger = LogManager.getLogger(PaymentDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	private static final String GET_ALL_QUERY = "SELECT PAYMENT_ID, PAYMENT_TYPE, NO_OF_INSTALMENTS, PAYMENT_BY, PAYMENT_AMOUNT,"
			+ "CURRENCY_TYPE,STATUS,  ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON FROM PAYMENTS";

	private static final String GET_BY_TYPE_QUERY = "SELECT SERVICE_ID, SERVICE_TYPE,CREATED_BY, CREATED_ON,  LAST_UPDATED_ON FROM PAYMENTS"
			+ "  WHERE SERVICE_NAME = :serviceName";

	private static final String INSERT_QUERY = "INSERT INTO PAYMENTS "
			+ " (PAYMENT_TYPE, NO_OF_INSTALMENTS, PAYMENT_BY,  PAYMENT_AMOUNT, CURRENCY_TYPE, STATUS, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:paymentType, :instalmentCount, :paymentBy, :paymentAmt, :currencyType, :status, :enabled, :createdBy, :createTS, :lastUpdateTS)";




	@Override
	public void create(Payments payment) {
		String methodName = "create ";
		logger.info(methodName + payment);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("paymentType", payment.getPaymentType());
		parameters.put("instalmentCount", payment.getInstalmentCount());
		parameters.put("paymentBy", payment.getPaymentBy());
		parameters.put("paymentAmt", payment.getAmount());
		parameters.put("currencyType", payment.getCurrencyType());
		parameters.put("status", payment.getStatus());
		parameters.put("enabled", payment.getEnabled());
		parameters.put("createdBy", payment.getCreatedBy());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);
		
	}

	@Override
	public void update(Payments payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Payments> getAllPayments() {
		String methodName = "getAllPayments()";
		logger.debug(methodName, "QUERY: Executing - " + GET_ALL_QUERY);
		PaymentRowMapper mapper = new PaymentRowMapper();
		List<Payments> paymentsList = new ArrayList<Payments>();
		paymentsList = this.jdbcTemplate.query(GET_ALL_QUERY, mapper);
		logger.debug(methodName, "QUERY: Executed - " + paymentsList);
		return paymentsList;
	}

	@Override
	public Payments getByPaymentType(String paymentType) {
		String methodName = "getByPaymentType(paymentType) - ";
		logger.entry(methodName + paymentType);
		Payments payment = null;
		Map<String, String> namedParams = Collections.singletonMap("paymentType", paymentType);
		PaymentRowMapper mapper = new PaymentRowMapper();
		logger.debug(methodName, "mapper - ", namedParams);
		payment = this.namedParameterJdbcTemplate.queryForObject(GET_BY_TYPE_QUERY, namedParams, mapper);
		logger.debug(methodName, "QUERY: Executed ", payment);
		return payment;
	}

	@Override
	public void delete(int paymentID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payments get(int paymentID) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
