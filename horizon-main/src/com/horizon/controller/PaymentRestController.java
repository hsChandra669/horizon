package com.horizon.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.db.utils.HnTransactionManagerHelper;
import com.horizon.model.Payments;
import com.horizon.model.Product;
import com.horizon.service.CompanyService;
import com.horizon.service.PaymentService;
import com.horizon.service.ProductService;

@RestController
public class PaymentRestController {
	Logger logger = LogManager.getLogger(PaymentRestController.class);

	@Autowired
	private PaymentService paymentService;
	

	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/payments/list")
	public List  getAllPayments() {
		 List<Payments> list = null;
		  try{
			  String methodName = "getPayments - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			 list =  paymentService.getAllPayments();
			  System.out.println(methodName + list.size());
			  logger.exit(methodName + list.size());

		  }catch( Exception e){
			  e.printStackTrace();
          }
		  return list;
	}

	@PostMapping(value = "/payments/create")
	//@RequestMapping(value = "/companies/create", headers= {"content-type=application/json"})
	public ResponseEntity createPayments(@RequestBody Payments payment) {
		String methodName = "createPayments - ";
		logger.entry(methodName + payment);
		System.out.println(methodName + payment);
		Payments paym = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		try {
			txnStatus = transactionManager.getTrasaction();
			paym = paymentService.createPayment(payment);
			transactionManager.commit(txnStatus);

			System.out.println(methodName   + paym);
			logger.exit(methodName +  paym  );

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			transactionManager.rollback(txnStatus);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity(paym, HttpStatus.OK);
	}
	


	/*@DeleteMapping("/companies/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == companyDAO.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}*/

	/*@PutMapping("/companies/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Company customer) {

		customer = companyDAO.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
*/


}