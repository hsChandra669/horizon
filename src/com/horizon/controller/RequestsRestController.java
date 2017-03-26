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
import com.horizon.model.Request;
import com.horizon.resources.exception.HnException;
import com.horizon.service.CompanyService;
import com.horizon.service.ProductService;
import com.horizon.service.RequestDetailsService;
import com.horizon.service.RequestService;
import com.horizon.validation.ValidationErrorBuilder;

@RestController
public class RequestsRestController {
	Logger logger = LogManager.getLogger(CompanyRestController.class);

	 @Autowired
	 private MessageSource messageSource;

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestDetailsService reqDetailsService;
	
	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/request/list")
	public ResponseEntity  getRequests() {
		HnJsonResponse jsonResponse = new HnJsonResponse();
		HttpStatus status = HttpStatus.OK;
		 List<Request> list = null;
		  try{
			  String methodName = "getCustomers - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			 list =  requestService.getAllRequests();
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

	@PostMapping(value = "/request/create")
	//@RequestMapping(value = "/company/create", headers= {"content-type=application/json"})
	public ResponseEntity createRequest(@Validated @RequestBody Request request, BindingResult bindingResult) {
		String methodName = "createRequest  - ";
		logger.entry(methodName + request);
		System.out.println(methodName + request);
		Request req = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 req = requestService.createRequest(request);
					transactionManager.commit(txnStatus);

				System.out.println(methodName   + req);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(req);
				logger.exit(methodName +  req  );
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

	@PostMapping(value = "/request/update")
	public ResponseEntity updateRequest(@Validated @RequestBody Request request, BindingResult bindingResult) {
		String methodName = "updateRequest - ";
		logger.entry(methodName + request);
		System.out.println(methodName + request);
		Request req = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 req = requestService.updateRequest(request);
				transactionManager.commit(txnStatus);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(req);
				logger.exit(methodName +  req  );
			 }


		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}

	@DeleteMapping("/request/{id}")
	public ResponseEntity deleteRequest(@PathVariable int id) {
		String methodName = "deleteRequest - ";
		logger.entry(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {
			 txnStatus = transactionManager.getTrasaction();
			 reqDetailsService.deleteReqDetailsByrequestId(id);
			 requestService.deleteRequest(id);
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