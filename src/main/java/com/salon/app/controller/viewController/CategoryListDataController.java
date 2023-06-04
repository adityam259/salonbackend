package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.File;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryImage;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.CategoryListDataRepo;
import com.salon.app.utils.Constants;

@Controller
public class CategoryListDataController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;

	@Autowired
	CategoryListDataRepo categoryListDataRepo;

	@GetMapping("addCategoryView")
	public String addCategoryView() {
		return "addCategoryView";
	}

	@PostMapping("editCategoryView")
	@ResponseBody
	public Map<String, String> updateHeaderPage(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}

	@PostMapping("updateCategoryPageView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<CategoryListData> objData = categoryListDataRepo.findById(id);
		model.addAttribute("data", objData.get());
		return "editCategoryView";
	}

	@PostMapping("deleteCatPage")
	@ResponseBody
	public Response deleteCatPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		categoryListDataRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@GetMapping("listCategoryView")
	public String listCategoryView(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = categoryListDataRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<CategoryListData> headerPage = categoryListDataRepo.getAllListCategory(pageable);
		List<CategoryListData> listUploadHeader = headerPage.getContent();
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

		return "listCategoryView";
	}

	@PostMapping("saveCatListData")
	public String saveCatListData(@RequestParam("categoryImage") MultipartFile file,
			@RequestParam("categoryName") String categoryName, Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		if (!file.isEmpty()) {
			CategoryListData catlistData = new CategoryListData();
			catlistData.setCategoryName(categoryName);
			catlistData.setIsDelete("0");
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            catlistData.setImageUploadDirectory(serverUrl);
	            catlistData.setImageName(fileName);
	        }
			CategoryListData savedCatListData = categoryListDataRepo.save(catlistData);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Category Added Successfully...");
			}
		}
		return "addCategoryView";
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateCategoryImageData")
	public String updateCategoryImageData(@RequestParam("categoryImage") MultipartFile file,
			@RequestParam("categoryName") String categoryName, @RequestParam("id") String id, Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<CategoryListData> objData = categoryListDataRepo.findById(Long.parseLong(id));
		if (objData != null) {
			byte[] imageData = file.getBytes();
			CategoryListData uploadHeader = new CategoryListData();
			if (file != null && !file.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = file.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            file.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory(serverUrl);
		            uploadHeader.setImageName(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory(objData.get().getImageUploadDirectory());
	            uploadHeader.setImageName(objData.get().getImageName());
			}
			uploadHeader.setCategoryName(categoryName);
			uploadHeader.setId(Long.parseLong(id));
			uploadHeader.setIsDelete("0");
			CategoryListData uploadedStatus = categoryListDataRepo.save(uploadHeader);
			if (uploadedStatus != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Data updated Successfully...");
				response.setData(uploadedStatus);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editCategoryView";
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getCategoryApi")
	@ResponseBody
	public Response<?> getAllCategory() {
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		List<CategoryListData> listOfHeader = categoryListDataRepo.getAllListCategoryData();
		if (listOfHeader != null) {
			response.setStatus("SUCCESS");
			response.setMessage(Constants.LIST_OF_HEADERS);
			response.setData(listOfHeader);
		}
		return response;
	}

}
