package com.horizon.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.db.utils.HnTransactionManagerHelper;
import com.horizon.model.Company;
import com.horizon.model.HnJsonResponse;
import com.horizon.resources.exception.HnException;
import com.horizon.service.CompanyService;
import com.horizon.validation.ValidationErrorBuilder;

@RestController
public class CompanyRestController {
	Logger logger = LogManager.getLogger(CompanyRestController.class);

	 @Autowired
	 private MessageSource messageSource;

	@Autowired
	private CompanyService companyService;

	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/company/list")
	public ResponseEntity  getCustomers() {
		HnJsonResponse jsonResponse = new HnJsonResponse();
		HttpStatus status = HttpStatus.OK;
		 List<Company> list = null;
		  try{
			  String methodName = "getCustomers - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			 list =  companyService.getAllCompanies();
			 System.out.println(methodName + list.size());

			 jsonResponse.setStatus("SUCCESS");
			 jsonResponse.setObject(list);
			 logger.exit(methodName + list.size());

		  }catch( Exception e){
			  jsonResponse.setStatus("ERROR");
			  status = HttpStatus.INTERNAL_SERVER_ERROR;
			  e.printStackTrace();
          }
		  return new ResponseEntity(jsonResponse, status);
	}

	@PostMapping(value = "/company/create")
	//@RequestMapping(value = "/company/create", headers= {"content-type=application/json"})
	public ResponseEntity createCustomer(@Validated @RequestBody Company company, BindingResult bindingResult) {
		String methodName = "createCustomer - ";
		logger.entry(methodName + company);
		System.out.println(methodName + company);
		Company comp = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
					comp = companyService.createCompany(company);
					transactionManager.commit(txnStatus);

				System.out.println(methodName   + comp);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(comp);
				logger.exit(methodName +  comp  );
			 }


		} catch (HnException e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();

		}catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}

	@PostMapping(value = "/company/update")
	public ResponseEntity updateCustomer(@Validated @RequestBody Company company, BindingResult bindingResult) {
		String methodName = "updateCustomer - ";
		logger.entry(methodName + company);
		System.out.println(methodName + company);
		Company comp = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 comp = companyService.updateCompany(company);
				transactionManager.commit(txnStatus);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(comp);
				logger.exit(methodName +  comp  );
			 }


		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		String methodName = "deleteCustomer - ";
		logger.entry(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {
			 txnStatus = transactionManager.getTrasaction();
			 companyService.deleteCompany(id);
			 transactionManager.commit(txnStatus);

			 jsonResponse.setStatus("SUCCESS");
		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}
}