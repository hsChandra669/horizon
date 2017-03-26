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
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.horizon.dao.rowmapper.CompanyRowMapper;
import com.horizon.dao.rowmapper.GetProductRowMapper;
import com.horizon.dao.rowmapper.ProductRowMapper;
import com.horizon.model.Company;
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
	private static final String GET_ALL_QUERY = "SELECT  C.NAME,  P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_TYPE, P.COMPANY_ID, P.ENABLED, "
			+ "P.CREATED_BY, P.CREATED_ON,  P.LAST_UPDATED_ON FROM PRODUCT P, COMPANY C WHERE P.COMPANY_ID=C.COMPANY_ID";

	private static final String GET_BY_NAME_QUERY = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_TYPE, COMPANY_ID, ENABLED, "
			+ " CREATED_BY, CREATED_ON,  LAST_UPDATED_ON FROM PRODUCT WHERE PRODUCT_NAME = :productName";

	private static final String INSERT_QUERY = "INSERT INTO PRODUCT "
			+ " (PRODUCT_NAME, PRODUCT_TYPE, COMPANY_ID, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:productName, :productType, :companyID, :enabled, :createdBy, :createTS, :lastUpdateTS)";

	private static final String GET_BY_COMPANYID_QUERY = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_TYPE, COMPANY_ID, ENABLED, "
			+ " CREATED_BY, CREATED_ON,  LAST_UPDATED_ON FROM PRODUCT WHERE COMPANY_ID = :companyID";


	private static final String UPDATE_QUERY = "UPDATE PRODUCT SET PRODUCT_NAME = :productName, PRODUCT_TYPE = :productType, "
			+ " LAST_UPDATED_ON = :lastUpdateTS where PRODUCT_ID = :productId";

	private static final String DELETE_QUERY = "DELETE FROM  PRODUCT  WHERE PRODUCT_ID= :productId";

	private static final String DELETE_BY_COMANYID_QUERY = "DELETE FROM  PRODUCT  WHERE COMPANY_ID= :companyID";

	@Override
	public void create(Product product) {
		String methodName = "create ";
		logger.info(methodName + product);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("productName", product.getProductName());
		parameters.put("productType", product.getProductType());
		parameters.put("companyID", product.getCompanyID());
		parameters.put("enabled", product.getEnabled());
		parameters.put("createdBy", product.getUserID());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}

	@Override
	public void update(Product product) {
		String methodName = "update ";
		logger.info(methodName + product);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("productName", product.getProductName());
		parameters.put("productType", product.getProductType());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("lastUpdateTS", new Timestamp(milisec));
		parameters.put("productId", product.getProductID());

		namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters);

	}

	@Override
	public void delete(int productID) {
		String methodName = "delete(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("productId", productID);
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParams);
	}

	@Override
	public void deleteProductsByCompanyID(int companyID) {
		String methodName = "deleteProductsByCompanyID(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("companyID", companyID);
		this.namedParameterJdbcTemplate.update(DELETE_BY_COMANYID_QUERY, namedParams);
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
		GetProductRowMapper mapper = new GetProductRowMapper();
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
		try {
			Map<String, String> namedParams = Collections.singletonMap("productName", name);
			ProductRowMapper mapper = new ProductRowMapper();
			logger.debug(methodName, "mapper - ", namedParams);
			product = this.namedParameterJdbcTemplate.queryForObject(GET_BY_NAME_QUERY, namedParams, mapper);
			logger.debug(methodName, "QUERY: Executed ", product);

		} catch (IncorrectResultSizeDataAccessException e) {
			product = null;
		}
		return product;
	}

	@Override
	public List<Product> getAllProductsByCompanyID(int companyID) {
		String methodName = "getAllProductsByCompanyID(companyID) - ";
		List<Product> productList = new ArrayList<Product>();
		try {
			logger.entry(methodName + "DAO" + companyID);
			Map<String, Integer> namedParams = Collections.singletonMap("companyID", companyID);
			ProductRowMapper mapper = new ProductRowMapper();
			logger.debug(methodName, "mapper - ", namedParams);
			productList = this.namedParameterJdbcTemplate.query(GET_BY_COMPANYID_QUERY, namedParams, mapper);
			logger.debug(methodName, "QUERY: Executed ", productList);

		} catch (IncorrectResultSizeDataAccessException e) {
		}
		return productList;
	}

}
