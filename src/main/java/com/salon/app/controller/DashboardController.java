package com.salon.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.repository.LoginRepository;
import com.salon.app.repository.OrderDetailsRepository;

@Controller
public class DashboardController {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	//
	@CrossOrigin(origins = { "*" })
	@GetMapping("signout")
	public String signout() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		return "login";
	}
	
	

	@CrossOrigin(origins = { "*" })
	@GetMapping("countUser")
	public Response getCountUser() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("count users");
		response.setData(loginRepository.selectCountUsers());
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("countEmployee")
	public Response getCountEmployee() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Employee users");
		response.setData(loginRepository.selectCountEmployee());
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("totalOrderStatusCompleted")
	public Response totalOrderStatusCompleted() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Completed Order Count");
		response.setData(orderDetailsRepository.orderCompleted());
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("totalOrderStatusPending")
	public Response totalOrderStatusPending() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Pending Order Count");
		response.setData(orderDetailsRepository.orderPending());
		return response;
	}
}
