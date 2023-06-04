package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.salon.app.model.ProductImages;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.ProductImagesRepository;
import java.io.File;
@Controller
public class ProductController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;
	
	@Autowired
	ProductImagesRepository productImagesRepository; 
	
	@GetMapping("addProductView")
	public String addProductView() {
		return "addProductView";
	}
	
	//listOfProductsView.jsp
	@GetMapping("listOfProductsView")
	public String listOfProductsView(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = productImagesRepository.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<ProductImages> headerPage = productImagesRepository.getAllListProduct(pageable);
		List<ProductImages> listUploadHeader = headerPage.getContent();
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

		return "listOfProductsView";
	}

	
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("/submitProductImageView")
	public String submitProductImage(@RequestParam("productImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, 
			@RequestParam("time") String time,
			@RequestParam("originalPrice") String originalPrice,
			@RequestParam("description") String description,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,
			Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			ProductImages uploadCategory = new ProductImages();
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            uploadCategory.setImageUploadDirectory(serverUrl);
	            uploadCategory.setImageName(fileName);
	        }
			uploadCategory.setProductName(file.getOriginalFilename());
			uploadCategory.setDescription(description);
			uploadCategory.setRate(rate);
			uploadCategory.setTime(time);
			uploadCategory.setTitle(title);
			uploadCategory.setOriginalPrice(originalPrice);
			uploadCategory.setIsDelete("0");
			uploadCategory.setMembershipDiscount(membershipDiscount);
			uploadCategory.setMembershipDiscount2(membershipDiscount2);
			ProductImages uploadedStatus = productImagesRepository.save(uploadCategory);
			if (uploadedStatus != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "file uploaded");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}
		return "addProductView";
	}
	
	@PostMapping("deleteProductPage")
	@ResponseBody
	public Response deleteProductPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		productImagesRepository.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}
	
	@PostMapping("editProductView")
	@ResponseBody
	public Map<String, String> updateHeaderPage(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}
	
	@PostMapping("updateProductView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<ProductImages> objData = productImagesRepository.findById(id);
		model.addAttribute("data", objData.get());
		return "editProductView";
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateProductImageData")
	public String updateHeaderImageData(@RequestParam(value="productImage",required = false) MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, @RequestParam("originalPrice") String originalPrice,
			@RequestParam("id") String id,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<ProductImages> objData = productImagesRepository.findById(Long.parseLong(id));
		if(objData!=null) {
		//byte[] imageData = file.getBytes();
		ProductImages uploadHeader = new ProductImages();
		if (file != null && !file.isEmpty()) {
			//uploadHeader.setProductImage(imageData);
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
		}else {
			uploadHeader.setImageUploadDirectory(objData.get().getImageUploadDirectory());
			uploadHeader.setImageName(objData.get().getImageName());

		}
		uploadHeader.setProductName(file.getOriginalFilename());
		uploadHeader.setDescription(description);
		uploadHeader.setRate(rate);
		uploadHeader.setTime(time);
		uploadHeader.setTitle(title);
		uploadHeader.setOriginalPrice(originalPrice);
		uploadHeader.setId(Long.parseLong(id));
		uploadHeader.setIsDelete("0");
		uploadHeader.setMembershipDiscount(membershipDiscount);
		uploadHeader.setMembershipDiscount2(membershipDiscount2);
		ProductImages uploadedStatus = productImagesRepository.save(uploadHeader);
		if (uploadedStatus != null) {
			response.setStatus("SUCCESS");
			response.setMessage("Data updated Successfully...");
			response.setData(uploadedStatus);
		}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editProductView";
	}
}
