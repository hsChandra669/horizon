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
import com.horizon.dao.rowmapper.DocumentRowMapper;
import com.horizon.dao.rowmapper.ProductRowMapper;
import com.horizon.model.Company;
import com.horizon.model.Document;
import com.horizon.model.Product;
import com.horizon.resources.HnConstants;
import com.horizon.resources.exception.HnException;

@Repository("fileDAO")
public class FileDAOImpl implements FileDAO{

	Logger logger = LogManager.getLogger(FileDAOImpl.class);


	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	private static final String INSERT_QUERY = "INSERT INTO DOCUMENTS "
			+ " (COMPANY_ID, DOCUMENT_NAME, EXTENSION_TYPE, DOCUMENT_SIZE, DATA, ENABLED, CREATED_BY, CREATED_ON, LAST_UPDATED_ON) "
			+ "VALUES(:compID, :docName, :docExtType, :docSize, :data, :enabled, :createdBy, :createTS, :lastUpdateTS)";

	private static final String GET_COMPANY_FILES = "SELECT DOCUMENT_ID, DOCUMENT_NAME, EXTENSION_TYPE, DOCUMENT_SIZE, "
			+ " LAST_UPDATED_ON from DOCUMENTS WHERE COMPANY_ID = :id";

	private static final String DELETE_QUERY = "DELETE FROM  DOCUMENTS  WHERE DOCUMENT_ID= :documentid";


	/*private static final String GET_ALL_QUERY = "SELECT COMPANY_ID, NAME, ADDRESS, CITY, ENABLED, CREATED_BY,"
			+ " CREATED_ON, LAST_UPDATED_ON from company";

	private static final String GET_BY_NAME_QUERY = "SELECT COMPANY_ID, NAME, ADDRESS, CITY, CREATED_ON,"
			+ " LAST_UPDATED_ON, ENABLED, CREATED_BY from company"
			+ "  where NAME = :companyName";

	private static final String GET_COMPANY_NAME = "SELECT NAME FROM COMPANY";

	private static final String UPDATE_QUERY = "UPDATE company SET NAME = :name, ADDRESS = :address, CITY = :city, "
			+ " LAST_UPDATED_ON = :lastUpdateTS where COMPANY_ID = :companyid";

	*/

	@Override
	public void create(Document document) {
		//(:docID, :compID, :docName, :docExtType, :data, :enabled, :createdBy, :createTS, :lastUpdateTS)";
		String methodName = "create ";
		logger.info(methodName + document);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("compID", document.getCompanyID());
		parameters.put("docName", document.getDocumentName());
		parameters.put("docExtType", document.getMimeType());
		parameters.put("docSize", document.getFileSize());
		parameters.put("data", document.getDocument());
		parameters.put("enabled", document.getEnabled());
		parameters.put("createdBy", document.getUserID());
		long milisec = Calendar.getInstance().getTimeInMillis();
		parameters.put("createTS", new Timestamp(milisec));
		parameters.put("lastUpdateTS", new Timestamp(milisec));

		namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);

	}
/*
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

	}*/

	@Override
	public void delete(int documentID) {
		String methodName = "delete(int)";
		Map<String, Integer> namedParams = Collections.singletonMap("documentid", documentID);
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParams);
	}

	/*@Override
	public Company get(int companyID) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Document> getAllFiles(String type, int id) {
		String methodName = "getAllFiles(type, id) - ";
		logger.entry(methodName);
		List<Document> documentList = new ArrayList<Document>();
		try {
			String query = null;
			if (HnConstants.ID_TYPE_COMPANY.equalsIgnoreCase(type)) {
				query = GET_COMPANY_FILES;
			}

			Map<String, Integer> namedParams = Collections.singletonMap("id", id);
			DocumentRowMapper mapper = new DocumentRowMapper();
			logger.debug(methodName, "mapper - ", namedParams);
			documentList = this.namedParameterJdbcTemplate.query(query, namedParams, mapper);
			logger.debug(methodName, "QUERY: Executed ", documentList);

		} catch (IncorrectResultSizeDataAccessException e) {
		}
		return documentList;
	}

	/*@Override
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
	}*/

/*	@Override
	public List<String> getAllCompanyNames() {
		String methodName = "getAllCompanyNames() - ";
		logger.entry(methodName);
		List<String> companyList = new ArrayList<>();
		try {
			companyList = this.jdbcTemplate.queryForList(GET_COMPANY_NAME, String.class);
			logger.debug(methodName, "QUERY: Executed ", companyList);

		} catch (IncorrectResultSizeDataAccessException e) {
		}
		return companyList;
	}*/
}
