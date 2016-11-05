package com.horizon.dao;

import java.util.List;

import com.horizon.model.Company;
import com.horizon.model.Payments;

public interface PaymentDAO {

	public void create(Payments payment);

	public void update(Payments payment);

    public void delete(int paymentID);

    public Payments get(int paymentID);

    public List<Payments> getAllPayments();

    public Payments getByPaymentType(String paymentType);
}
