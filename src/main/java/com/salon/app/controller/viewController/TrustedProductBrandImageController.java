package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.salon.app.model.TrustedProduct;
import com.salon.app.repository.TrustedProductRepository;
import java.io.File;
@Controller
public class TrustedProductBrandImageController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;

	@Autowired
	TrustedProductRepository trustedProductRepository;

	@GetMapping("addtrustedproduct")
	public String openAddtrustedproduct() {
		return "trustedProductAndBrand";
	}
	
	//editTrustedProducts
	@GetMapping("editTrustedProducts")
	public String editTrustedProducts(Model model) {
		List<TrustedProduct> getTrustedProduct = trustedProductRepository.findAll();		
		model.addAttribute("data", getTrustedProduct.get(0));
		return "editTrustedProducts";
	}
	

	// saveYoutubeLinkData
	@PostMapping("saveTrustedProductData")
	public String saveTrustedProductData(@RequestParam("trustedProductImage") MultipartFile productLink,
			@RequestParam("title") String title, Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		if (!productLink.isEmpty()) {
			//byte[] imageData = productLink.getBytes();
			TrustedProduct data = new TrustedProduct();
			data.setTitle(title);
			//data.setTrustedProductImage(imageData);
			data.setIsDelete("0");
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = productLink.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            productLink.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            data.setImageUploadDirectory(serverUrl);
	            data.setImageName(fileName);
	        }
			
			TrustedProduct savedCatListData = trustedProductRepository.save(data);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Trusted Data Added Successfully...");
			}
		}
		return "trustedProductAndBrand";
	}

	// updateTrustedImageData
	@PostMapping("updateTrustedImageData")
	public String updateTrustedImageData(
			@RequestParam(value = "trustedProductImage", required = false) MultipartFile productLink,
			@RequestParam("title") String title, @RequestParam("id") String id, Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		TrustedProduct data = new TrustedProduct();
		Optional<TrustedProduct> getCatListData = trustedProductRepository.findById(Long.parseLong(id));
		if (getCatListData.get() != null) {
			//byte[] imageData = null;
			if (!productLink.isEmpty()) {
				//imageData = productLink.getBytes();
				//data.setTrustedProductImage(imageData);
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = productLink.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            productLink.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            data.setImageUploadDirectory(serverUrl);
		            data.setImageName(fileName);
		        }
			} else {
				data.setImageUploadDirectory(getCatListData.get().getImageUploadDirectory());
	            data.setImageName(getCatListData.get().getImageName());
				
			}
			data.setId(getCatListData.get().getId());
			data.setTitle(title);
			data.setIsDelete("0");
			TrustedProduct savedCatListData = trustedProductRepository.save(data);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Trusted Data Updated Successfully...");
				model.addAttribute("data", savedCatListData);

			}
		}
		return "editTrustedProducts";
	}

	// api call to get
	@CrossOrigin(origins = { "*" })
	@GetMapping("getTrustedProduct")
	@ResponseBody
	public Response getTrustedProduct() {
		Response response = new Response();
		List<TrustedProduct> getTrustedProduct = trustedProductRepository.findAll();
		response.setData(getTrustedProduct.get(0).getImageUploadDirectory());
		response.setStatus("SUCCESS");
		return response;
	}

}
