package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.CompanyDAO;
import com.horizon.dao.ProductDAO;
import com.horizon.model.Company;
import com.horizon.model.Product;
import com.horizon.resources.exception.HnException;

@Service ("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;


	@Override
	public Product createProduct(Product product) throws HnException{
		Product dbProduct = productDao.getByProductName(product.getProductName());
		if (dbProduct != null) {
			System.out.println("product already exist");
			throw new HnException("product.already.exist");
		}

		product.setEnabled(1);
		product.setUserID(1);
		productDao.create(product);
		dbProduct = productDao.getByProductName(product.getProductName());
		return dbProduct;

	}


	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}


	@Override
	public List<Product> getAllProductsByCompanyID(int comapnyID) {
		return productDao.getAllProductsByCompanyID(comapnyID);
	}

}
