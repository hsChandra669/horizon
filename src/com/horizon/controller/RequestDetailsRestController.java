package com.horizon.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.db.utils.HnTransactionManagerHelper;
import com.horizon.model.HnJsonResponse;
import com.horizon.model.ReqDetails;
import com.horizon.service.RequestDetailsService;

@RestController
public class RequestDetailsRestController {

	Logger logger = LogManager.getLogger(RequestDetailsRestController.class);

	 @Autowired
	 private MessageSource messageSource;

	@Autowired
	private RequestDetailsService requestDetailsService;


	@Autowired
	HnTransactionManagerHelper transactionManager;
	
	@GetMapping("/details/list/{id}")
	public ResponseEntity  getProductsByCompanyID(@PathVariable int id) {
		String methodName = "getProductsByCompanyID - ";
		  HnJsonResponse jsonResponse = new HnJsonResponse();
			HttpStatus status = HttpStatus.OK;
			 List<ReqDetails> list = null;
			  try{
				  logger.entry(methodName);
				  System.out.println(methodName  + id);
				 list =  requestDetailsService.getAllReqDetailsByRequestId(id);

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
	
}
