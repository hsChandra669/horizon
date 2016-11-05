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

import com.horizon.model.Payments;


public class PaymentRowMapper implements RowMapper<Payments> {

	@Override
	public Payments mapRow(ResultSet rs, int rowNumb) throws SQLException {
		Payments payment = new Payments();

		payment.setPaymentID(rs.getInt("PAYMENT_ID"));
		payment.setPaymentBy(rs.getInt("PAYMENT_BY"));
		payment.setPaymentType(rs.getString("PAYMENT_TYPE"));
		payment.setAmount(rs.getLong("PAYMENT_AMOUNT"));
		payment.setInstalmentCount(rs.getInt("NO_OF_INSTALMENTS"));
		payment.setStatus(rs.getString("STATUS"));
		payment.setEnabled(rs.getInt("ENABLED"));
		payment.setCreatedBy(rs.getInt("CREATED_BY"));
		payment.setCreateTS(String.valueOf(rs.getTimestamp("CREATED_ON")));
		payment.setLastUpdateTS(String.valueOf(rs.getTimestamp("LAST_UPDATED_ON")));

		return payment;
	}

}
