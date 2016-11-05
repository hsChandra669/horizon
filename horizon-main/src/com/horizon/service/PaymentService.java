package com.horizon.service;

import java.util.List;


import com.horizon.model.Payments;

public interface PaymentService {

	 public Payments createPayment(Payments payment);

	 public List<Payments> getAllPayments();

}
