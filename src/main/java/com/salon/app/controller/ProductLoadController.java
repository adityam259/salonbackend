package com.salon.app.controller;

import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.ProductResponse;
import com.salon.app.dto.Response;
import com.salon.app.model.CategoryImage;
import com.salon.app.model.ProductImages;
import com.salon.app.model.SubCategoryMaster;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.CategoryImageRepository;
import com.salon.app.repository.ProductImagesRepository;
import com.salon.app.repository.SubCategoryMasterRepo;
import com.salon.app.repository.UploadHeaderRepository;
import com.salon.app.utils.Constants;

@RestController
public class ProductLoadController {
	//private static final Logger logger = LoggerFactory.getLogger(ProductLoadController.class);
	@Autowired
	UploadHeaderRepository uploadHeaderRepository;
	@Autowired
	SubCategoryMasterRepo categoryImageRepository;
	@Autowired
	ProductImagesRepository productImagesRepository;

	@CrossOrigin(origins = { "*" })
	@PostMapping("/getProductDetails")
	public Response getProduct(@RequestParam("id") String id, 
			@RequestParam("productType") String productType,Model model) {
		//logger.info("Request received in getProduct");
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage("Error in getting product deatils");
		if (productType.equals("header")) {
			Optional<UploadHeader> uploadHeader = uploadHeaderRepository.findById(Long.parseLong(id));
			if (uploadHeader != null && uploadHeader.get().getTitle()!=null) {
				response.setStatus("SUCCESS");
				response.setMessage("Product Info");
				response.setData(setProductResponse(uploadHeader.get(),"header"));
				
			}
		}
		if (productType.equals("category")) {
			Optional<SubCategoryMaster> catHeader = categoryImageRepository.findById(Long.parseLong(id));
			if (catHeader != null && catHeader.get().getTitle()!=null) {
				response.setStatus("SUCCESS");
				response.setMessage("Product Info");
				response.setData(setProductResponse(catHeader.get(),"category"));
			}
		}
		if (productType.equals("product")) {
			Optional<ProductImages> productHeader = productImagesRepository.findById(Long.parseLong(id));
			if (productHeader != null && productHeader.get().getTitle()!=null) {
				//model.addAttribute("response", setProductResponse(productHeader.get(),"product"));
				response.setStatus("SUCCESS");
				response.setMessage("Product Info");
				response.setData(setProductResponse(productHeader.get(),"product"));
			}
		}
		
		//logger.info("Request End in Login with getProduct ");
		return response;
	}
	
	private ProductResponse setProductResponse(Object object,String productType) {
		ProductResponse productResponse= new ProductResponse();
		if (productType.equals("header")) {
			UploadHeader uploadHeader = (UploadHeader) object;
			productResponse.setId(uploadHeader.getId());
			productResponse.setType("header");
			productResponse.setDescription(uploadHeader.getDescription());
			productResponse.setImage(uploadHeader.getImageUploadDirectory());
			productResponse.setRate(uploadHeader.getRate());
			productResponse.setTime(uploadHeader.getTime());
			productResponse.setTitle(uploadHeader.getTitle());
			productResponse.setOriginalPrice(uploadHeader.getOriginalPrice());
			productResponse.setMembershipDiscount(uploadHeader.getMembershipDiscount());
			productResponse.setMembershipDiscount2(uploadHeader.getMembershipDiscount2());
			productResponse.setStatus("Pending");
		}
		if (productType.equals("category")) {
			SubCategoryMaster uploadHeader = (SubCategoryMaster) object;
			productResponse.setId(uploadHeader.getId());
			productResponse.setType("category");
			productResponse.setDescription(uploadHeader.getDescription());
			productResponse.setImage(uploadHeader.getImageUploadDirectory());

			
			productResponse.setRate(uploadHeader.getPrice());
			productResponse.setTime(uploadHeader.getServiceTime());
			productResponse.setTitle(uploadHeader.getTitle());
			productResponse.setOriginalPrice(uploadHeader.getOriginalPrice());
			productResponse.setStatus("Pending");
		}
		if (productType.equals("product")) {
			ProductImages uploadHeader = (ProductImages) object;
			productResponse.setId(uploadHeader.getId());
			productResponse.setType("product");
			productResponse.setDescription(uploadHeader.getDescription());
			productResponse.setImage(uploadHeader.getImageUploadDirectory());
			productResponse.setRate(uploadHeader.getRate());
			productResponse.setTime(uploadHeader.getTime());
			productResponse.setTitle(uploadHeader.getTitle());
			productResponse.setOriginalPrice(uploadHeader.getOriginalPrice());
			productResponse.setMembershipDiscount(uploadHeader.getMembershipDiscount());
			productResponse.setMembershipDiscount2(uploadHeader.getMembershipDiscount2());
			productResponse.setStatus("Pending");
		}
		
		return productResponse;
	}
	
}
