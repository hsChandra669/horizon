package com.horizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.dao.FileDAO;
import com.horizon.model.Document;
import com.horizon.resources.exception.HnException;

@Service ("fileService")
public class FileServiceImpl implements FileService{

	@Autowired
	FileDAO fileDAO;

	@Override
	public void createDocument(Document document) throws HnException {

		fileDAO.create(document);
	}

	@Override
	public List<Document> getAllFiles(String type, int id){
		return fileDAO.getAllFiles(type, id);
	}

	/*@Override
	public Company updateCompany(Company company) {
		Company dbComp;
		companyDao.update(company);
		dbComp = companyDao.getByCompanyName(company.getCompanyName());
		return dbComp;
	}*/

	@Override
	public void deleteDocument(int documentID) {
		fileDAO.delete(documentID);

	}

	/*@Override
	public List<String> getAllCompanyNames() {
		return companyDao.getAllCompanyNames();
	}*/

}
