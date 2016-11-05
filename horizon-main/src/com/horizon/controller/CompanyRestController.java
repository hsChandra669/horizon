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

import com.harizon.db.utils.HnTransactionManagerHelper;
import com.harizon.service.CompanyService;
import com.horizon.model.Company;

@RestController
public class CompanyRestController {
	Logger logger = LogManager.getLogger(CompanyRestController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/companies/list")
	public List  getCustomers() {
		 List<Company> list = null;
		  try{
			  String methodName = "getCustomers - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			 list =  companyService.getAllCompanies();
			  System.out.println(methodName + list.size());
			  logger.exit(methodName + list.size());

		  }catch( Exception e){
			  e.printStackTrace();
          }
		  return list;
	}

	@PostMapping(value = "/companies/create")
	//@RequestMapping(value = "/companies/create", headers= {"content-type=application/json"})
	public ResponseEntity createCustomer(@RequestBody Company company) {
		String methodName = "createCustomer - ";
		logger.entry(methodName + company);
		System.out.println(methodName + company);
		Company comp = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		try {
			txnStatus = transactionManager.getTrasaction();
			comp = companyService.createCompany(company);
			transactionManager.commit(txnStatus);

			System.out.println(methodName   + comp);
			logger.exit(methodName +  comp  );

		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity(comp, HttpStatus.OK);
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