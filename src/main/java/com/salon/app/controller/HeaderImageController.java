package com.salon.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.UploadHeaderRepository;
import com.salon.app.utils.Constants;

@RestController
public class HeaderImageController {
	@Autowired
	UploadHeaderRepository uploadHeaderRepository;

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getAllHeader")
	public Response<?> getAllHeader() {
		Response response = new Response();
		response.setStatus(Constants.ERROR);
		response.setMessage(Constants.GET_ALL_HEADER_ERROR);
		List<UploadHeader> listOfHeader = uploadHeaderRepository.getAllListHeaderData();
		if (listOfHeader != null) {
			response.setStatus("SUCCESS");
			response.setMessage(Constants.LIST_OF_HEADERS);
			response.setData(listOfHeader);
		}
		return response;
	}
}
