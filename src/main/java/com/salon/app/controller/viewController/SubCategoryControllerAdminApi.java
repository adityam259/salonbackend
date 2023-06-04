package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.SubCategoryMaster;
import com.salon.app.repository.CategoryListDataRepo;
import com.salon.app.repository.SubCategoryMasterRepo;
import java.io.File;
@Controller
public class SubCategoryControllerAdminApi {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;

	@Autowired
	SubCategoryMasterRepo subCategoryMasterRepo;

	@Autowired
	CategoryListDataRepo categoryListDataRepo;

	@GetMapping("addSubCategoryApi")
	public String addSubCategoryApi(Model model) {
		List<CategoryListData> listCatData = categoryListDataRepo.getAllCategoryData();
		model.addAttribute("data", listCatData);
		return "addSubCategory";
	}

	// listOfSubCategory
	@GetMapping("listOfSubCategory")
	public String listOfSubCategory(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		// Page<SubCategoryMaster> listCatData =
		// subCategoryMasterRepo.getAllListCategory();
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = categoryListDataRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<SubCategoryMaster> headerPage = subCategoryMasterRepo.getAllListCategory(pageable);
		List<SubCategoryMaster> listUploadHeader = headerPage.getContent();
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("totalPages", headerPage.getTotalPages());
		model.addAttribute("pageSize", size);
		return "listOfSubCategory";
	}

	// addSubCatData
	@PostMapping("addSubCatData")
	public String saveCatListData(@RequestParam("subCategoryImage1") MultipartFile subCategoryImage1,
			@RequestParam(value = "subCategoryImage2", required = false) MultipartFile subCategoryImage2,
			@RequestParam(value = "subCategoryImage3", required = false) MultipartFile subCategoryImage3,
			@RequestParam(value = "subCategoryImage4", required = false) MultipartFile subCategoryImage4,
			@RequestParam(value = "subCategoryImage5", required = false) MultipartFile subCategoryImage5,
			@RequestParam("title") String title, @RequestParam("categoryId") String categoryId,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam("serviceTime") String serviceTime, @RequestParam("price") String price,
			@RequestParam("originalPrice") String originalPrice, 
			@RequestParam("description") String description,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,
			Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Sub Category Addition Failed..");
		if (!subCategoryImage1.isEmpty()) {
			byte[] imageData1 = subCategoryImage1.getBytes();
			byte[] imageData2 = null;
			byte[] imageData3 = null;
			byte[] imageData4 = null;
			byte[] imageData5 = null;
			SubCategoryMaster subCatlistData = new SubCategoryMaster();
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = subCategoryImage1.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            subCategoryImage1.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            subCatlistData.setImageUploadDirectory(serverUrl);
	            subCatlistData.setImageName(fileName);
	        }
			
			if (subCategoryImage2 != null && !subCategoryImage2.isEmpty()) {
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage2.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage2.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            subCatlistData.setImageUploadDirectory2(serverUrl);
		            subCatlistData.setImageName2(fileName);
		        }
			}
			if (subCategoryImage3 != null && !subCategoryImage3.isEmpty()) {
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage3.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage3.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            subCatlistData.setImageUploadDirectory3(serverUrl);
		            subCatlistData.setImageName3(fileName);
		        }
			}
			if (subCategoryImage4 != null && !subCategoryImage4.isEmpty()) {
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage4.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage4.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            subCatlistData.setImageUploadDirectory4(serverUrl);
		            subCatlistData.setImageName4(fileName);
		        }
			}
			if (subCategoryImage5 != null && !subCategoryImage5.isEmpty()) {
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage5.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage5.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            subCatlistData.setImageUploadDirectory5(serverUrl);
		            subCatlistData.setImageName5(fileName);
		        }
			}
			
			subCatlistData.setBrand(brand);
			subCatlistData.setCategoryId(categoryId);
			subCatlistData.setDescription(description);
			subCatlistData.setOriginalPrice(originalPrice);
			subCatlistData.setPrice(price);
			subCatlistData.setServiceTime(serviceTime);
			subCatlistData.setTitle(title);
			subCatlistData.setIsDelete("0");
			subCatlistData.setMembershipDiscount(membershipDiscount);
			subCatlistData.setMembershipDiscount2(membershipDiscount2);
			SubCategoryMaster savedCatListData = subCategoryMasterRepo.save(subCatlistData);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Sub Category Added Successfully...");
				List<CategoryListData> listCatData = categoryListDataRepo.getAllCategoryData();
				model.addAttribute("data", listCatData);
			}
		}
		return "addSubCategory";
	}

	@PostMapping("deleteSubCatPage")
	@ResponseBody
	public Response deleteSubCatPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		subCategoryMasterRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@PostMapping("updateSubCategoryPageView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<SubCategoryMaster> objData = subCategoryMasterRepo.findById(id);
		model.addAttribute("data", objData.get());
		List<CategoryListData> listCatData = categoryListDataRepo.getAllCategoryData();
		model.addAttribute("dataCatList", listCatData);
		return "editSubCategoryView";
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateSubCategoryImageData")
	public String updateSubCategoryImageData(
			@RequestParam(value = "subCategoryImage1", required = false) MultipartFile subCategoryImage1,
			@RequestParam(value = "subCategoryImage2", required = false) MultipartFile subCategoryImage2,
			@RequestParam(value = "subCategoryImage3", required = false) MultipartFile subCategoryImage3,
			@RequestParam(value = "subCategoryImage4", required = false) MultipartFile subCategoryImage4,
			@RequestParam(value = "subCategoryImage5", required = false) MultipartFile subCategoryImage5,
			@RequestParam("title") String title, @RequestParam("id") String id,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("originalPrice") String originalPrice, @RequestParam("serviceTime") String serviceTime,
			@RequestParam("brand") String brand, @RequestParam("categoryId") String categoryId, 
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,
			Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<SubCategoryMaster> objData = subCategoryMasterRepo.findById(Long.parseLong(id));
		if (objData != null) {
			SubCategoryMaster uploadHeader = new SubCategoryMaster();
			// image 1
			//byte[] imageData = subCategoryImage1.getBytes();
			if (subCategoryImage1 != null && !subCategoryImage1.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage1.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage1.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory(serverUrl);
		            uploadHeader.setImageName(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory(objData.get().getImageUploadDirectory());
				uploadHeader.setImageName(objData.get().getImageName());
			}
			// image 2
			if (subCategoryImage2 != null && !subCategoryImage2.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage2.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage2.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory(serverUrl);
		            uploadHeader.setImageName(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory2(objData.get().getImageUploadDirectory2());
				uploadHeader.setImageName2(objData.get().getImageName2());
			}
			// image 3
			if (subCategoryImage3 != null && !subCategoryImage3.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage3.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage3.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory3(serverUrl);
		            uploadHeader.setImageName3(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory3(objData.get().getImageUploadDirectory3());
				uploadHeader.setImageName3(objData.get().getImageName3());			}
			// image 4
			if (subCategoryImage4 != null && !subCategoryImage4.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage4.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage4.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory4(serverUrl);
		            uploadHeader.setImageName4(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory4(objData.get().getImageUploadDirectory4());
				uploadHeader.setImageName4(objData.get().getImageName4());		
			}
			// image 5
			if (subCategoryImage5 != null && !subCategoryImage5.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = subCategoryImage5.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            subCategoryImage5.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory5(serverUrl);
		            uploadHeader.setImageName5(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory5(objData.get().getImageUploadDirectory5());
				uploadHeader.setImageName5(objData.get().getImageName5());					}

			uploadHeader.setCategoryId(categoryId);
			uploadHeader.setBrand(brand);
			uploadHeader.setDescription(description);
			uploadHeader.setPrice(price);
			uploadHeader.setOriginalPrice(originalPrice);
			uploadHeader.setServiceTime(serviceTime);
			uploadHeader.setTitle(title);
			uploadHeader.setId(Long.parseLong(id));
			uploadHeader.setIsDelete("0");
			uploadHeader.setMembershipDiscount(membershipDiscount);
			uploadHeader.setMembershipDiscount2(membershipDiscount2);
			SubCategoryMaster uploadedStatus = subCategoryMasterRepo.save(uploadHeader);
			if (uploadedStatus != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Data updated Successfully...");
				response.setData(uploadedStatus);
				List<CategoryListData> listCatData = categoryListDataRepo.getAllCategoryData();
				model.addAttribute("dataCatList", listCatData);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editSubCategoryView";
	}
	
	//find By Category id
	@CrossOrigin(origins = { "*" })
	@PostMapping("/getDataByCategory")
	@ResponseBody
	public Response getDataByCategoryId(
			@RequestParam("id") String id)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<CategoryListData> catData = categoryListDataRepo.findById(Long.parseLong(id));		
		List<SubCategoryMaster> objData = subCategoryMasterRepo.getAllCategoryDataAPI(id);
		
		if (objData != null) {
			for(SubCategoryMaster obj : objData) {
				obj.setCatName(catData.get().getCategoryName());
				obj.setType("category");
				obj.setQuantity("1");
			}
			
		}
		response.setData(objData);
		response.setStatus("SUCCESS");
		response.setMessage(catData.get().getCategoryName());
		return response;
	}
	
	//find By subCategory id
			@CrossOrigin(origins = { "*" })
			@PostMapping("/getDataBySubCategory")
			@ResponseBody
			public Response getDataBySubCategoryId(
					@RequestParam("id") String id)
					throws IOException {
				Response response = new Response();
				response.setStatus("FAIL");
				response.setMessage("Unable to update data.");
				Optional<SubCategoryMaster> objData = subCategoryMasterRepo.findById(Long.parseLong(id));
				response.setData(objData);
				response.setStatus("SUCCESS");
				response.setMessage("");
				return response;
			}
	
}
