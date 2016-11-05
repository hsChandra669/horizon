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
import com.horizon.model.Product;
import com.horizon.model.Services;
import com.horizon.service.CompanyService;
import com.horizon.service.ProductService;
import com.horizon.service.ServicesService;

@RestController
public class SrvicesRestController {
	Logger logger = LogManager.getLogger(SrvicesRestController.class);

	@Autowired
	private ServicesService servicesService;
	

	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/services/list")
	public List  getAllServices() {
		 List<Services> list = null;
		  try{
			  String methodName = "getAllServices - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			 list =  servicesService.getAllServices();
			  System.out.println(methodName + list.size());
			  logger.exit(methodName + list.size());

		  }catch( Exception e){
			  e.printStackTrace();
          }
		  return list;
	}

	@PostMapping(value = "/services/create")
	//@RequestMapping(value = "/companies/create", headers= {"content-type=application/json"})
	public ResponseEntity createService(@RequestBody Services services) {
		String methodName = "createService - ";
		logger.entry(methodName + services);
		System.out.println(methodName + services);
		Services serv = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		try {
			txnStatus = transactionManager.getTrasaction();
			serv = servicesService.createServices(services);
			transactionManager.commit(txnStatus);

			System.out.println(methodName   + serv);
			logger.exit(methodName +  serv  );

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			transactionManager.rollback(txnStatus);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity(serv, HttpStatus.OK);
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