package com.harizon.dao;

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
import com.horizon.model.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO{

	Logger logger = LogManager.getLogger(ProductDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	private static final String GET_ALL_QUERY = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_TYPE, ENABLED, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON FROM PRODUCT";

	private static final String GET_BY_NAME_QUERY = "SELECT PRODUCT_ID, PRODUCT_TYPE,CREATED_BY, CREATED_ON,  LAST_UPDATED_ON FROM PRODUCT"
			+ "  WHERE PRODUCT_NAME = :productName";

	private static final String INSERT_QUERY = "INSERT INTO PRODUCT "
			+ " (NAME, PRODUCT_TYPE, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:name, :productType, :enabled, :createdBy, :createTS, :lastUpdateTS)";

	@Override
	public void create(Product product) {
		String methodName = "create ";
		logger.info(methodName + product);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", product.getname());
		parameters.put("productType", product.getproductType());
		parameters.put("enabled", product.getEnabled());
		parameters.put("createdBy", product.getUserID());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int productID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product get(int productID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		String methodName = "getAllCompanies()";
		logger.debug(methodName, "QUERY: Executing - " + GET_ALL_QUERY);
		ProductRowMapper mapper = new ProductRowMapper();
		List<Product> productList = new ArrayList<Product>();
		productList = this.jdbcTemplate.query(GET_ALL_QUERY, mapper);
		logger.debug(methodName, "QUERY: Executed - " + productList);
		return productList;
	}

	@Override
	public Product getByProductName(String name) {
		String methodName = "getByProductName(name) - ";
		logger.entry(methodName + name);
		Product product = null;
		Map<String, String> namedParams = Collections.singletonMap("productName", name);
		ProductRowMapper mapper = new ProductRowMapper();
		logger.debug(methodName, "mapper - ", namedParams);
		product = this.namedParameterJdbcTemplate.queryForObject(GET_BY_NAME_QUERY, namedParams, mapper);
		logger.debug(methodName, "QUERY: Executed ", product);
		return product;
	}
}
