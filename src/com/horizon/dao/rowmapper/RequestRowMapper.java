package com.horizon.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.horizon.model.Request;

public class RequestRowMapper implements RowMapper<Request>{
	
	@Override
	public Request mapRow(ResultSet rs, int rowNumb) throws SQLException {
		Request request = new Request();

		request.setRequestId(rs.getInt("REQUEST_ID"));
		request.setDesc(rs.getString("DESCRIPTIONS"));
		request.setServiceId(rs.getInt("SERVICE_ID"));
		request.setCompanyId(rs.getInt("COMPANY_ID"));
		request.setCreatedBy(rs.getInt("CREATED_BY"));
		request.setCreateTS(String.valueOf(rs.getTimestamp("CREATED_ON")));
		request.setLastUpdateTS(String.valueOf(rs.getTimestamp("LAST_UPDATED_ON")));

		return request;
	}

}
