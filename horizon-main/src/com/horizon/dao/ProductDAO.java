package com.horizon.dao;

import java.util.List;

import com.horizon.model.Product;

public interface ProductDAO {

	public void create(Product Product);

	public void update(Product Product);

    public void delete(int ProductID);

    public Product get(int ProductID);

    public List<Product> getAllProducts();

    public Product getByProductName(String name);
}
