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
import com.horizon.model.Product;
import com.horizon.resources.exception.HnException;
import com.horizon.service.ProductService;
import com.horizon.validation.ValidationErrorBuilder;

@RestController
public class ProductRestController {
	Logger logger = LogManager.getLogger(ProductRestController.class);

	 @Autowired
	 private MessageSource messageSource;

	@Autowired
	private ProductService productService;


	@Autowired
	HnTransactionManagerHelper transactionManager;

	@GetMapping("/products/list")
	public ResponseEntity  getProducts() {
		  HnJsonResponse jsonResponse = new HnJsonResponse();
			HttpStatus status = HttpStatus.OK;
			 List<Product> list = null;
			  try{
				  String methodName = "getProducts - ";
				  logger.entry(methodName);
				  System.out.println(methodName);
				 list =  productService.getAllProducts();
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

	@GetMapping("/products/list/{id}")
	public ResponseEntity  getProductsByCompanyID(@PathVariable int id) {
		String methodName = "getProductsByCompanyID - ";
		  HnJsonResponse jsonResponse = new HnJsonResponse();
			HttpStatus status = HttpStatus.OK;
			 List<Product> list = null;
			  try{
				  logger.entry(methodName);
				  System.out.println(methodName  + id);
				 list =  productService.getAllProductsByCompanyID(id);

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


	@PostMapping(value = "/products/create")
	public ResponseEntity createProduct(@Validated @RequestBody Product product, BindingResult bindingResult) {
		String methodName = "createProduct - ";
		logger.entry(methodName + product);
		System.out.println(methodName + product);
		Product prod = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				prod = productService.createProduct(product);
				transactionManager.commit(txnStatus);

				System.out.println(methodName   + prod);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(prod);
				logger.exit(methodName +  prod  );
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


	@PostMapping(value = "/product/update")
	public ResponseEntity updateProduct(@Validated @RequestBody Product product, BindingResult bindingResult) {
		String methodName = "updateCustomer - ";
		logger.entry(methodName + product);
		System.out.println(methodName + product);
		Product prod = null;
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			 if(bindingResult.hasErrors()){
		         jsonResponse.setErrorsMap(ValidationErrorBuilder.populateErrorMap(bindingResult, messageSource));
		         jsonResponse.setStatus("ERROR");

			 } else {
				 txnStatus = transactionManager.getTrasaction();
				 prod = productService.updateProduct(product);
				transactionManager.commit(txnStatus);

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setObject(prod);
				logger.exit(methodName +  prod  );
			 }


		} catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		String methodName = "deleteCustomer - ";
		logger.entry(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {
			 txnStatus = transactionManager.getTrasaction();
			 productService.deleteProduct(id);
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