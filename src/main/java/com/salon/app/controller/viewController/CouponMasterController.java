package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.CouponMaster;
import com.salon.app.repository.CouponMasterRepo;
import com.salon.app.utils.Constants;

@Controller
public class CouponMasterController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;
	
	
	@Autowired
	CouponMasterRepo couponMasterRepo;
	
	@GetMapping("addCouponData")
	public String addCouponData(Model model) {
		return "addCouponData";
	}
	
	@GetMapping("couponDataList")
	public String couponDataList(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = couponMasterRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<CouponMaster> headerPage = couponMasterRepo.getAllListCouponMaster(pageable);
		List<CouponMaster> listUploadHeader = headerPage.getContent();
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
		
		
		return "couponDataList";
	}

	
	//saveCouponData
	@PostMapping("saveCouponData")
	public String saveCouponData(@RequestParam("couponImage") MultipartFile file,
			@RequestParam("couponCode") String couponCode,
			@RequestParam("description") String description,Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		if (!file.isEmpty()) {
			CouponMaster catlistData = new CouponMaster();
			catlistData.setCouponCode(couponCode);
			catlistData.setDescription(description);
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
			CouponMaster savedCatListData = couponMasterRepo.save(catlistData);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Coupon  Added Successfully...");
			}
		}
		return "addCouponData";
	}
	
	@PostMapping("deleteCouponPage")
	@ResponseBody
	public Response deleteCouponPage(@RequestParam("id") String id, Model model) {
		//System.err.println("id = "+id);
		Response response = new Response();
		couponMasterRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}
	
	@CrossOrigin(origins = { "*" })
	@GetMapping("/getCouponApi")
	@ResponseBody
	public Response<?> getCouponApi() {
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		List<CouponMaster> listOfHeader = couponMasterRepo.getAllListCouponData();
		if (listOfHeader != null) {
			response.setStatus("SUCCESS");
			response.setMessage(Constants.LIST_OF_HEADERS);
			response.setData(listOfHeader);
		}
		return response;
	}
	
	@CrossOrigin(origins = { "*" })
	@GetMapping("/getCoupounCount")
	@ResponseBody
	public Response<?> getCoupounCount() {
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		int listOfHeader = couponMasterRepo.getCountOfCouponData();
		
			response.setStatus("SUCCESS");
			response.setMessage(Constants.LIST_OF_HEADERS);
			response.setData(listOfHeader);
		
		return response;
	}
}
