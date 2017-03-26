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
import org.springframework.jdbc.core.RowMapper;

import com.horizon.model.Product;

public class GetProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNumb) throws SQLException {
		Product product = new Product();

		product.setCompanyName(rs.getString("NAME"));
		product.setProductID(rs.getInt("PRODUCT_ID"));
		product.setProductName(rs.getString("PRODUCT_NAME"));
		product.setProductType(rs.getString("PRODUCT_TYPE"));
		product.setCompanyID(rs.getInt("COMPANY_ID"));
		product.setEnabled(rs.getInt("ENABLED"));
		product.setUserID(rs.getInt("CREATED_BY"));
		product.setCreateTS(String.valueOf(rs.getTimestamp("CREATED_ON")));
		product.setLastUpdateTS(String.valueOf(rs.getTimestamp("LAST_UPDATED_ON")));

		return product;
	}

}
