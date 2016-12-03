package com.horizon.dao;

import java.util.List;

import com.horizon.model.Product;

public interface ProductDAO {

	public void create(Product Product);

	public void update(Product Product);

    public void delete(int ProductID);

    public void deleteProductsByCompanyID(int companyID);

    public Product get(int ProductID);

    public List<Product> getAllProducts();

    public List<Product> getAllProductsByCompanyID(int companyID);

    public Product getByProductName(String name);
}
