package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.CompanyDAO;
import com.horizon.dao.ProductDAO;
import com.horizon.model.Product;

@Service ("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;


	@Override
	public Product createProduct(Product product) {

		productDao.create(product);
		return product;
	}
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}

}
