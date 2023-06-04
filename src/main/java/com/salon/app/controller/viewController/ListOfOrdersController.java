package com.salon.app.controller.viewController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryImage;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.Login;
import com.salon.app.model.OrderDetails;
import com.salon.app.model.ProductImages;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.CategoryImageRepository;
import com.salon.app.repository.LoginRepository;
import com.salon.app.repository.OrderDetailsRepository;
import com.salon.app.repository.ProductImagesRepository;
import com.salon.app.repository.UploadHeaderRepository;

@Controller
public class ListOfOrdersController {
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	UploadHeaderRepository uploadHeaderRepository;

	@Autowired
	CategoryImageRepository categoryImageRepository;

	@Autowired
	ProductImagesRepository productImagesRepository;
	
	@GetMapping("/ordersSearch")
	public String searchOrders(@RequestParam("query") String query, Model model,@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		long totalHeaders = orderDetailsRepository.count();
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.getBytype("admin");
		if (loginObject != null) {
			Pageable pageable = PageRequest.of(page, size);
			Page<OrderDetails> headerPage = orderDetailsRepository.findByOrderNumberContaining(query,pageable);
			List<OrderDetails> listUploadHeader = headerPage.getContent();
			//List<OrderDetails> ordersList = orderDetailsRepository.findAll();
			for (OrderDetails orders : listUploadHeader) {
				//System.err.println("orders ="+orders);
				Optional<Login> loginObject1 = loginRepository.findById(Long.parseLong(orders.getUserId()));
				orders.setFullName(loginObject1.get().getFirstName() + " " + loginObject1.get().getLastName());
				orders.setUserAddress(loginObject1.get().getAddress());
				orders.setUserEmail(loginObject1.get().getEmailId());
				orders.setUserId(loginObject1.get().getUserName());
				orders.setUserMobile(loginObject1.get().getPhoneNo());
				orders.setUserName(loginObject1.get().getUserName());
				if (orders.getType().equals("header")) {
					try {
						Optional<UploadHeader> uploadHeader = uploadHeaderRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(uploadHeader.get().getTitle());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (orders.getType().equals("category")) {
					try {
						Optional<CategoryImage> catHeader = categoryImageRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(catHeader.get().getTitle());
						String image = Base64.getEncoder().encodeToString(catHeader.get().getCategoryImage());

						orders.setFrontEndBase64(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (orders.getType().equals("product")) {
					try {
						Optional<ProductImages> productHeader = productImagesRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(productHeader.get().getTitle());
						//String image = Base64.getEncoder().encodeToString(productHeader.get().getProductImage());

						//orders.setFrontEndBase64(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			response.setStatus("SUCCESS");
			response.setMessage("List of orders..");
			response.setData(listUploadHeader);
			model.addAttribute("totalPages", headerPage.getTotalPages());

		}
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("pageSize", size);
		model.addAttribute("data", response.getData());
		
		
	    //List<OrderDetails> orders = orderDetailsRepository.findByOrderNumberContaining(query);
	    //model.addAttribute("orders", orders);
	    return "listOfOrders";
	}

	@GetMapping("listOfOrdersData")
	public String listOfOrdersData(Model model,@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		//Response response = allOrderDetails(page,size);
		long totalHeaders = orderDetailsRepository.count();
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.getBytype("admin");
		if (loginObject != null) {
			Pageable pageable = PageRequest.of(page, size);
			Page<OrderDetails> headerPage = orderDetailsRepository.getAllListOrders(pageable);
			List<OrderDetails> listUploadHeader = headerPage.getContent();
			//List<OrderDetails> ordersList = orderDetailsRepository.findAll();
			for (OrderDetails orders : listUploadHeader) {
				Optional<Login> loginObject1 = loginRepository.findById(Long.parseLong(orders.getUserId()));
				orders.setFullName(loginObject1.get().getFirstName() + " " + loginObject1.get().getLastName());
				orders.setUserAddress(loginObject1.get().getAddress());
				orders.setUserEmail(loginObject1.get().getEmailId());
				orders.setUserId(loginObject1.get().getUserName());
				orders.setUserMobile(loginObject1.get().getPhoneNo());
				orders.setUserName(loginObject1.get().getUserName());
				if (orders.getType().equals("header")) {
					try {
						Optional<UploadHeader> uploadHeader = uploadHeaderRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(uploadHeader.get().getTitle());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (orders.getType().equals("category")) {
					try {
						Optional<CategoryImage> catHeader = categoryImageRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(catHeader.get().getTitle());
						String image = Base64.getEncoder().encodeToString(catHeader.get().getCategoryImage());

						orders.setFrontEndBase64(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (orders.getType().equals("product")) {
					try {
						Optional<ProductImages> productHeader = productImagesRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(productHeader.get().getTitle());
						//String image = Base64.getEncoder().encodeToString(productHeader.get().getProductImage());

						//orders.setFrontEndBase64(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			response.setStatus("SUCCESS");
			response.setMessage("List of orders..");
			response.setData(listUploadHeader);
			model.addAttribute("totalPages", headerPage.getTotalPages());

		}
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("pageSize", size);
		model.addAttribute("data", response.getData());
		return "listOfOrders";
	}
	
	

}
