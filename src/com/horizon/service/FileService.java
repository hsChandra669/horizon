package com.horizon.service;

import java.util.List;

import com.horizon.model.Document;
import com.horizon.resources.exception.HnException;

public interface FileService {

	 public void createDocument(Document document) throws HnException;

	 public List<Document> getAllFiles(String type, int id);

	 //public Company updateCompany(Company company);

	 public void deleteDocument(int documentID);

	  //public List<String> getAllCompanyNames();

}
