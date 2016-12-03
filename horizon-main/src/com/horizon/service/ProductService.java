package com.horizon.service;

import java.util.List;


import com.horizon.model.Product;
import com.horizon.resources.exception.HnException;

public interface ProductService {

	 public Product createProduct(Product product) throws HnException;

	 public List<Product> getAllProducts();

	 public List<Product> getAllProductsByCompanyID(int comapnyID);

	 public Product updateProduct(Product product);

	 public void deleteProduct(int productID);

	 public void deleteProductsByCompanyID(int companyID);

}
