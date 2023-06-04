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
import com.salon.app.model.CategoryImage;
import com.salon.app.repository.CategoryImageRepository;
import com.salon.app.utils.Constants;

@RestController
public class CategoryImageController {
	//private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	CategoryImageRepository categoryImageRepository;
	
	@CrossOrigin(origins = { "*" })
	@GetMapping("/getAllCategory")
	public Response<?> getAllCategory() {
		//logger.info("Request received in getAllCategory");
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		List<CategoryImage> listOfHeader = categoryImageRepository.findAllExcludeIsDelete();
		listOfHeader.forEach(s->{
	        String base64String = Base64.getEncoder().encodeToString(s.getCategoryImage());
	        s.setCategoryFrontEndBase64(base64String);

		});
		if(listOfHeader!=null) {
			response.setStatus("SUCCESS");
			response.setMessage(Constants.LIST_OF_HEADERS);
			response.setData(listOfHeader);
		}
		//logger.info("Request End in Login with getAllCategory " + response);
		return response;
	}

}
