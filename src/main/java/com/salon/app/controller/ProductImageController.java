package com.salon.app.controller;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.model.ProductImages;
import com.salon.app.repository.ProductImagesRepository;
import com.salon.app.utils.Constants;

@RestController
public class ProductImageController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ProductImageController.class);
	
	@Autowired
	ProductImagesRepository productImagesRepository;

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getAllProduct")
	public Response<?> getAllProduct() {
		//logger.info("Request received in getAllHeader");
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		List<ProductImages> listOfHeader = productImagesRepository.getAllListProductData();
		/*
		 * listOfHeader.forEach(s -> { String base64String =
		 * Base64.getEncoder().encodeToString(s.getProductImage());
		 * s.setProductFrontEndBase64(base64String);
		 * 
		 * });
		 */
		if (listOfHeader != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List Of Products");
			response.setData(listOfHeader);
		}
		//logger.info("Request End in Login with getAllHeader " + response);
		return response;
	}
	
	
	
}
