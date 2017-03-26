package com.horizon.controller;

import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.horizon.db.utils.HnTransactionManagerHelper;
import com.horizon.model.Company;
import com.horizon.model.Document;
import com.horizon.model.HnJsonResponse;
import com.horizon.resources.HnConstants;
import com.horizon.resources.exception.HnException;
import com.horizon.service.FileService;
import com.horizon.validation.ValidationErrorBuilder;

@RestController
public class FileController {
	Logger logger = LogManager.getLogger(FileController.class);

	 /*@Autowired
	 private MessageSource messageSource;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ProductService productService;*/

	@Autowired
	private FileService fileService;

	@Autowired
	HnTransactionManagerHelper transactionManager;

	/*@PostMapping(value = "/file/upload")
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
		String methodName = "uploadFile - ";
		System.out.println(methodName + "inside uploadFile");
		//logger.entry(methodName + company);
		//System.out.println(methodName + company);

		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			if (!file.isEmpty()) {
				System.out.println("original file name - " + file.getOriginalFilename());
				try {
					//byte[] bytes = file.getBytes();
					String fileName = file.getOriginalFilename();
					InputStream inputStream = file.getInputStream();

					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon","root","kod123");
					 String sql = "INSERT INTO DOCUMENTS (DOCUMENT_NAME, DATA) values (?, ?)";

					 System.out.println();
					 PreparedStatement statement = con.prepareStatement(sql);
			            statement.setString(1, fileName);

			            if (inputStream != null) {
			                // fetches input stream of the upload file for the blob column
			                statement.setBlob(2, inputStream);
			            }

			            // sends the statement to the database server
			            int row = statement.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					//return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
				}
			} else {
				//return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
			}

		} catch (Exception e) {
		//	transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
		//return "You successfully uploaded file=" + file.getOriginalFilename();
	}*/


	@PostMapping(value = "/file/upload")
	public ResponseEntity uploadFile(@RequestParam("type") String idType,
			@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
		String methodName = "uploadFile - ";
		logger.entry(methodName );
		System.out.println(methodName);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {

			System.out.println("id type - " + idType);
			System.out.println("id - " + id);
			txnStatus = transactionManager.getTrasaction();
		//	comp = companyService.createCompany(company);


			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				//String mimeType= URLConnection.guessContentTypeFromName(fileName);
				String mimeType = file.getContentType();
				System.out.println("mimeType - " + mimeType);
				int fileLength = inputStream.available()/1024; // conertinf into kb

				Document document = new Document();
				document.setDocument(inputStream);
				document.setDocumentName(fileName);
				document.setMimeType(mimeType);
				document.setFileSize(fileLength);
				document.setEnabled(1);
				document.setUserID(1);
				if (HnConstants.ID_TYPE_COMPANY.equals(idType)) {
					document.setCompanyID(id);
				}

				fileService.createDocument(document);
				jsonResponse.setStatus("SUCCESS");

			} else  {
				System.out.println("file is empty");
			}
			transactionManager.commit(txnStatus);


		} catch (HnException e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			// e.printStackTrace();
			 System.out.println("HnException occured");
			 System.out.println(e.getMessage());
			 System.out.println(e.getLocalizedMessage());

		}catch (Exception e) {
			transactionManager.rollback(txnStatus);
			 jsonResponse.setStatus("ERROR");
			 status = HttpStatus.INTERNAL_SERVER_ERROR;
			 System.out.println("Exception occured");
			 System.out.println(e.getMessage());
			 System.out.println(e.getLocalizedMessage());
			// e.printStackTrace();
		}
		return new ResponseEntity(jsonResponse, status);
	}



	@GetMapping("/file/list/{type}/{id}")
	public ResponseEntity  getAllFiles(@PathVariable String type, @PathVariable int id) {
		HnJsonResponse jsonResponse = new HnJsonResponse();
		HttpStatus status = HttpStatus.OK;
		 List<Document> fileList = null;
		  try{
			  String methodName = "getAllFiles - ";
			  logger.entry(methodName);
			  System.out.println(methodName);
			  fileList = fileService.getAllFiles(type, id);
			 System.out.println(methodName + fileList.size());

			 for (Document document : fileList) {
				 if (document.getMimeType() == null) {
					 document.setMimeType("application/octet-stream");
				 }
				 String fileName = document.getDocumentName();
				 String[] fileArr = fileName.split("\\.");
				 document.setExtensionType(fileArr[fileArr.length-1]);
			}

			 jsonResponse.setStatus("SUCCESS");
			 jsonResponse.setObject(fileList);
			 logger.exit(methodName + fileList.size());

		  }catch( Exception e){
			  jsonResponse.setStatus("ERROR");
			  status = HttpStatus.INTERNAL_SERVER_ERROR;
			  e.printStackTrace();
          }
		  return new ResponseEntity(jsonResponse, status);
	}


	/* @RequestMapping(value = {"/download" }, method = RequestMethod.GET)
		public ModelAndView product() {
			System.out.println("inside product");
			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Custom Login Form");
			model.addObject("message", "This is download page!");
			model.setViewName("download");
			return model;

		}*/

	@GetMapping(value = "/file/download/attachment/{fileId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable int fileId) {
		String methodName = "downloadFile - ";
		System.out.println(methodName + "inside downloadFile attachment");

		  String fileName = null;
		  HttpHeaders httpHeaders = new HttpHeaders();
		  InputStream inputStream = null;
		  byte[] document = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon","root","kod123");

			 // queries the database
            String sql = "SELECT * FROM DOCUMENTS WHERE DOCUMENT_ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, fileId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // gets file name and file blob data
                fileName = result.getString("DOCUMENT_NAME");
                Blob blob = result.getBlob("DATA");
                inputStream = blob.getBinaryStream();
                document = FileCopyUtils.copyToByteArray(inputStream);

                System.out.println("document = " + document.length);

                // sets MIME type for the file download
             //   String mimeType= URLConnection.guessContentTypeFromName(fileName);

                Path source = Paths.get(fileName);
                String mimeType = Files.probeContentType(source);

                if(mimeType==null){
                    System.out.println("mimetype is not detectable, will take default");
                    mimeType = "application/octet-stream";
                }
                System.out.println("mimetype : "+mimeType);

                String type[]  = mimeType.split("/");
                httpHeaders.setContentType(new MediaType(type[0], type[1]));
                httpHeaders.setContentLength(document.length);
               // httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=" + fileName));
                httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

                System.out.println("returned");
            }

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(document, httpHeaders, HttpStatus.OK);

		/*  return ResponseEntity
          		.ok()
          		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
          		.body(file);*/
	}


	@GetMapping(value = "/file/download/inline/{fileId}")
	public ResponseEntity<byte[]> downloadFileInline(@PathVariable int fileId) {
		String methodName = "downloadFile - ";
		System.out.println(methodName + "inside downloadFile inline");

		  String fileName = null;
		  HttpHeaders httpHeaders = new HttpHeaders();
		  InputStream inputStream = null;
		  byte[] document = null;
		try {


			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon","root","kod123");

			 // queries the database
            String sql = "SELECT * FROM DOCUMENTS WHERE DOCUMENT_ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,fileId);
            ResultSet result = statement.executeQuery();


            if (result.next()) {
                // gets file name and file blob data
                fileName = result.getString("DOCUMENT_NAME");
                Blob blob = result.getBlob("DATA");
                inputStream = blob.getBinaryStream();
                document = FileCopyUtils.copyToByteArray(inputStream);

                int fileLength = inputStream.available();
                System.out.println("document = " + document.length);

                // sets MIME type for the file download
               // String mimeType= URLConnection.guessContentTypeFromName(fileName);

                Path source = Paths.get(fileName);
                String mimeType = Files.probeContentType(source);

                if(mimeType==null){
                    System.out.println("mimetype is not detectable, will take default");
                    mimeType = "application/octet-stream";
                }
                System.out.println("mimetype : "+mimeType);

                String type[]  = mimeType.split("/");
                httpHeaders.setContentType(new MediaType(type[0], type[1]));
                httpHeaders.setContentLength(document.length);
               // httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, String.format("inline; filename=\"" + fileName +"\""));
                httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");


                System.out.println("returned");

            }

		} catch (Exception e) {
			 e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(document, httpHeaders, HttpStatus.OK);
		/*  return ResponseEntity
          		.ok()
          		.headers(httpHeaders)
          		.body(inputStream);*/
	}

	@DeleteMapping("/file/{id}")
	public ResponseEntity deleteDocument(@PathVariable int id) {
		String methodName = "deleteDocument - ";
		logger.entry(methodName + "controller" + id);
		System.out.println(methodName + "controller" + id);
		HttpStatus status = HttpStatus.OK;
		TransactionStatus txnStatus = null;
		HnJsonResponse jsonResponse = new HnJsonResponse();

		try {
			 txnStatus = transactionManager.getTrasaction();
			 fileService.deleteDocument(id);
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