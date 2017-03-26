package com.horizon.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.horizon.model.Product;
import com.horizon.model.ReqDetails;


public class ReqDetailsRowMapper implements RowMapper<ReqDetails>{

	@Override
	public ReqDetails mapRow(ResultSet rs, int rowNumb) throws SQLException {
		ReqDetails reqDetails = new ReqDetails();

		reqDetails.setRequestId(rs.getInt("REQUEST_ID"));
		reqDetails.setRequestDetailId(rs.getInt("REQUEST_DETAIL_ID"));
		reqDetails.setSubmitedDate(String.valueOf(rs.getTimestamp("SUBMITED_DATE")));
		reqDetails.setRejectedDate(String.valueOf(rs.getTimestamp("REJECTED_DATE")));
		reqDetails.setResubmittedDate(String.valueOf(rs.getTimestamp("RESUBMITTED_DATE")));
		reqDetails.setResubmttedCount(rs.getInt("RESUBMTTED_COUNT"));
		reqDetails.setContactPersonName(rs.getString("CONTACT_PERSON_NAME"));
		reqDetails.setContactPersonNumber(rs.getString("CONTACT_PERSON_NUMBER"));
		reqDetails.setEnabled(rs.getInt("ENABLED"));
		reqDetails.setCreatedBy(rs.getInt("CREATED_BY"));
		reqDetails.setCreatedOn(String.valueOf(rs.getTimestamp("CREATED_ON")));
		reqDetails.setLastUpdatedOn(String.valueOf(rs.getTimestamp("LAST_UPDATED_ON")));
		reqDetails.setEstimatedFdaApprovalDate(String.valueOf(rs.getTimestamp("ESTIMATED_FDA_APPROVAL_DATE")));
		reqDetails.setFdaApprovalDate(String.valueOf(rs.getTimestamp("FDA_APPROVAL_DATE")));

		return reqDetails;
	}

	
}
