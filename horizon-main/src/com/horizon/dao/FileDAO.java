package com.horizon.dao;

import java.util.List;

import com.horizon.model.Document;

public interface FileDAO {

	public void create(Document document);

	//public void update(Company company);

    public void delete(int documentID);

    //public Company get(int companyID);

	public List<Document> getAllFiles(String type, int id);

    //public Company getByCompanyName(String name);

    //public List<String> getAllCompanyNames();


}
