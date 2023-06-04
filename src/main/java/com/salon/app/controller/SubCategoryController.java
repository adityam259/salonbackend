package com.salon.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.SubCategoryMaster;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.SubCategoryMasterRepo;

@RestController
public class SubCategoryController {

	@Autowired
	SubCategoryMasterRepo subCategoryMasterRepo;

	/*
	 * @CrossOrigin(origins = { "*" })
	 * 
	 * @PostMapping("/submitSubCategoryImage")
	 * 
	 * @ResponseBody public Response
	 * saveSubCategory(@RequestParam("subCategoryImage1") MultipartFile
	 * subCategoryImage1,
	 * 
	 * @RequestParam(value ="subCategoryImage2", required = false) MultipartFile
	 * subCategoryImage2,
	 * 
	 * @RequestParam(value ="subCategoryImage3", required = false) MultipartFile
	 * subCategoryImage3,
	 * 
	 * @RequestParam(value ="subCategoryImage4", required = false) MultipartFile
	 * subCategoryImage4,
	 * 
	 * @RequestParam(value ="subCategoryImage5", required = false) MultipartFile
	 * subCategoryImage5, @RequestParam("title") String title,
	 * 
	 * @RequestParam("price") String price, @RequestParam("serviceTime") String
	 * serviceTime,
	 * 
	 * @RequestParam("brand") String brand, @RequestParam("subCatName") String
	 * subCatName,
	 * 
	 * @RequestParam("categoryId") String categoryId, Model model) throws
	 * IOException { Response response = new Response(); response.setStatus("FAIL");
	 * response.setMessage("Unable to save data."); if
	 * (!subCategoryImage1.isEmpty()) { byte[] imageData1 =
	 * subCategoryImage1.getBytes(); byte[] imageData2 = null; byte[] imageData3 =
	 * null; byte[] imageData4 = null; byte[] imageData5 = null; if
	 * (subCategoryImage2!=null) { imageData2 = subCategoryImage2.getBytes(); } if
	 * (subCategoryImage3!=null) { imageData3 = subCategoryImage3.getBytes(); } if
	 * (subCategoryImage4!=null) { imageData4 = subCategoryImage4.getBytes(); } if
	 * (subCategoryImage5!=null) { imageData5 = subCategoryImage5.getBytes(); }
	 * SubCategoryMaster uploadHeader = new SubCategoryMaster();
	 * uploadHeader.setBrand(brand); uploadHeader.setCategoryId(categoryId);
	 * uploadHeader.setPrice(price); uploadHeader.setServiceTime(serviceTime);
	 * uploadHeader.setSubCategoryImage1(imageData1);
	 * uploadHeader.setSubCategoryImage2(imageData2);
	 * uploadHeader.setSubCategoryImage3(imageData3);
	 * uploadHeader.setSubCategoryImage4(imageData4);
	 * uploadHeader.setSubCategoryImage5(imageData5);
	 * //System.err.println("uploadHeader ::"+uploadHeader); SubCategoryMaster
	 * uploadedStatus = subCategoryMasterRepo.save(uploadHeader); if (uploadedStatus
	 * != null) { model.addAttribute("status", "file uploaded");
	 * response.setStatus("SUCCESS");
	 * response.setMessage("file uploaded with data"); } } return response; }
	 */
}
