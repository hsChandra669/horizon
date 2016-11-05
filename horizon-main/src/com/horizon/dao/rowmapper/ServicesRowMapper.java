/**
 * *********************************************************************
 * File name:   KnBillingInfoRowMapper.java
 * Subsystem:   PAM PROV
 * <p/>
 * Name                  Date         Release
 * -----------------    -----------   -------
 * Ravi Shanker .P       12-Feb-2013      7.4
 * <p/>
 * <p/>
 * Copyright (c) 2013  Kodiak Networks (India) Pvt. Ltd.
 * #401, 4th Floor, 'Prestige Sigma'
 * No.3, Vittal Mallya Road
 * Bangalore - 560 001
 * www.kodiaknetworks.com
 * All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of Kodiak
 * Networks, Inc. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with Kodiak Networks.
 * *************************************************************************
 */

package com.horizon.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.horizon.model.Services;

public class ServicesRowMapper implements RowMapper<Services> {

	@Override
	public Services mapRow(ResultSet rs, int rowNumb) throws SQLException {
		Services service = new Services();

		service.setServiceID(rs.getInt("SERVICE_ID"));
		service.setServiceName(rs.getString("SERVICE_NAME"));
		service.setServiceType(rs.getString("SERVICE_TYPE"));
		service.setEnabled(rs.getInt("ENABLED"));
		service.setCreatedBy(rs.getInt("CREATED_BY"));
		service.setCreateTS(String.valueOf(rs.getTimestamp("CREATED_ON")));
		service.setLastUpdateTS(String.valueOf(rs.getTimestamp("LAST_UPDATED_ON")));

		return service;
	}

}
