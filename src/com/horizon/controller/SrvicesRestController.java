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
import com.horizon.model.HnJsonResponse;
import com.horizon.model.Services;
import com.horizon.resources.exception.HnException;
import com.horizon.service.ServicesService;
import com.horizon.validation.ValidationErrorBuilder;

@RestController
public class SrvicesRestController {
	Logger logger = LogManager.getLogger(SrvicesRestController.class);

	@Autowired
	private ServicesService servicesService;

	 @Autowired
	 private MessageSource messageSource;

	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/services/list")
	public ResponseEntity  getAllServices() {
		HnJsonResponse jsonResponse = new HnJsonResponse();
		HttpStatus status = HttpStatus.OK;
		 List<Services> list = null;
		  try{
			  String methodName = "getAllServices - ";
			  logger.entry(methodName);
			 list =  servicesService.getAllServices();
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

	@PostMapping(value = "/services/create")
	public ResponseEntity createService(@Validated @RequestBody Services service, BindingResult bindingResult) {
		String methodName = "createService - ";
		logger.entry(methodName + service);
		Services serv = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 serv = servicesService.createServices(service);
				 transactionManager.commit(txnStatus);

				System.out.println(methodName   + serv);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(serv);
				logger.exit(methodName +  serv  );
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


	@PostMapping(value = "/services/update")
	public ResponseEntity updateService(@Validated @RequestBody Services services, BindingResult bindingResult) {
		String methodName = "updateCustomer - ";
		logger.entry(methodName + services);
		System.out.println(methodName + services);
		Services serv = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 serv = servicesService.updateServices(services);
				transactionManager.commit(txnStatus);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(serv);
				logger.exit(methodName +  serv  );
			 }


		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}

	@DeleteMapping("/services/{id}")
	public ResponseEntity deleteService(@PathVariable int id) {
		String methodName = "deleteService - ";
		logger.entry(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {
			 txnStatus = transactionManager.getTrasaction();
			 servicesService.deleteServices(id);
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