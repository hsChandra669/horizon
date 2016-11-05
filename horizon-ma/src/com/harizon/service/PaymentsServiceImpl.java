package com.harizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harizon.dao.CompanyDAO;
import com.harizon.dao.PaymentDAO;
import com.horizon.model.Company;
import com.horizon.model.Payments;

@Service ("paymentService")
public class PaymentsServiceImpl implements PaymentService{

	@Autowired
	PaymentDAO paymentDAO;



	@Override
	public Payments createPayment(Payments payment) {
		// TODO Auto-generated method stub
		paymentDAO.create(payment);
		return payment;
	}

	@Override
	public List<Payments> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentDAO.getAllPayments();
	}

}
