package com.horizon.service;

import java.util.List;


import com.horizon.model.Product;

public interface ProductService {

	 public Product createProduct(Product product);

	 public List<Product> getAllProducts();

}
