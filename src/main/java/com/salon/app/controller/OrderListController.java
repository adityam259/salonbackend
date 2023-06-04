package com.salon.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryImage;
import com.salon.app.model.Login;
import com.salon.app.model.MembershipMaster;
import com.salon.app.model.OrderDetails;
import com.salon.app.model.ProductId;
import com.salon.app.model.ProductImages;
import com.salon.app.model.SubCategoryMaster;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.CategoryImageRepository;
import com.salon.app.repository.LoginRepository;
import com.salon.app.repository.MembershipMasterRepo;
import com.salon.app.repository.OrderDetailsRepository;
import com.salon.app.repository.ProductImagesRepository;
import com.salon.app.repository.SubCategoryMasterRepo;
import com.salon.app.repository.UploadHeaderRepository;

@RestController
public class OrderListController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(OrderListController.class);

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

	@Autowired
	SubCategoryMasterRepo subCategoryMasterRepo;
	
	@Autowired
	MembershipMasterRepo membershipMasterRepo;
	
	private static String imageMembership = "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"; 

	@CrossOrigin(origins = { "*" })
	@PostMapping("getOrderById")
	public Response getOrderById(@RequestParam("id") String id) {
		// LOGGER.info("Request received in getOrderList ");
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");

		Optional<OrderDetails> ordersList = orderDetailsRepository.findById(Long.parseLong(id));

		if (ordersList.get().getType().equals("header")) {
			Optional<UploadHeader> uploadHeader = uploadHeaderRepository
					.findById(Long.parseLong(ordersList.get().getProductId()));
			ordersList.get().setTitle(uploadHeader.get().getTitle());
		}
		if (ordersList.get().getType().equals("category")) {
			Optional<CategoryImage> catHeader = categoryImageRepository
					.findById(Long.parseLong(ordersList.get().getProductId()));
			ordersList.get().setTitle(catHeader.get().getTitle());
		}
		if (ordersList.get().getType().equals("product")) {
			Optional<ProductImages> productHeader = productImagesRepository
					.findById(Long.parseLong(ordersList.get().getProductId()));
			ordersList.get().setTitle(productHeader.get().getTitle());
		}

		response.setStatus("SUCCESS");
		response.setMessage("List of orders..");
		response.setData(ordersList);

		// LOGGER.info("Request completed in getOrderList ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("orderDetails")
	public Response getOrderList(@RequestBody Login login) {
		// LOGGER.info("Request received in getOrderList ");
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.findByphoneNo(login.getPhoneNo());
			if (loginObject != null) {
				List<OrderDetails> ordersList = orderDetailsRepository.orderList(String.valueOf(loginObject.getId()));
				for (OrderDetails orders : ordersList) {
					orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
					orders.setUserAddress(loginObject.getAddress());
					orders.setUserEmail(loginObject.getEmailId());
					orders.setUserId(loginObject.getUserName());
					orders.setUserMobile(loginObject.getPhoneNo());
					orders.setUserName(loginObject.getUserName());
					if (orders.getType().equals("header")) {
						Optional<UploadHeader> uploadHeader = uploadHeaderRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(uploadHeader.get().getTitle());
					}
					if (orders.getType().equals("category")) {
						Optional<CategoryImage> catHeader = categoryImageRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(catHeader.get().getTitle());
					}
					if (orders.getType().equals("product")) {
						Optional<ProductImages> productHeader = productImagesRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(productHeader.get().getTitle());
					}
				}

				response.setStatus("SUCCESS");
				response.setMessage("List of orders..");
				response.setData(ordersList);
			}

		}
		// LOGGER.info("Request completed in getOrderList ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("allOrderDetails")
	public Response allOrderDetails() {
		// LOGGER.info("Request received in getOrderList ");
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.getBytype("admin");
		if (loginObject != null) {
			List<OrderDetails> ordersList = orderDetailsRepository.findAll();
			for (OrderDetails orders : ordersList) {
				orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
				orders.setUserAddress(loginObject.getAddress());
				orders.setUserEmail(loginObject.getEmailId());
				orders.setUserId(loginObject.getUserName());
				orders.setUserMobile(loginObject.getPhoneNo());
				orders.setUserName(loginObject.getUserName());
				if (orders.getType().equals("header")) {
					try {
						Optional<UploadHeader> uploadHeader = uploadHeaderRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(uploadHeader.get().getTitle());
						//String image = Base64.getEncoder().encodeToString(uploadHeader.get().getHeaderImage());

						//orders.setFrontEndBase64(image);
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
			response.setData(ordersList);
		}
		// LOGGER.info("Request completed in getOrderList ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("assignUserWithStatusOrder")
	public Response assignUserWithStatusOrder(@RequestParam("status") String status,
			@RequestParam("userId") String userId, @RequestParam("id") String id) {
		// LOGGER.info("Request received in getOrderList ");
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing fields");

		Optional<OrderDetails> ordersList = orderDetailsRepository.findById(Long.parseLong(id));
		ordersList.get().setStatus(status);
		ordersList.get().setUserId(userId);
		OrderDetails savedData = orderDetailsRepository.save(ordersList.get());

		response.setStatus("SUCCESS");
		response.setMessage("Order Updated ..");
		response.setData(savedData);

		// LOGGER.info("Request completed in getOrderList ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("confirmOrder")
	@ResponseBody
	public Response confirmOrder(@RequestParam("userId") String userId, 
			@RequestParam("totalAmount") String totalAmount,
			@RequestParam("status") String status, @RequestParam("productId") String productId,
			@RequestParam("dateSeleted") String dateSeleted,
			@RequestParam("timeSlot") String timeSlot,
			@RequestParam("paymentMode") String paymentMode,
			@RequestParam("deliverNotify") String deliverNotify) {
		//System.err.println("userId = "+userId);
		//System.err.println("totalAmount = "+totalAmount);
		//System.err.println("status = "+status);
		//System.err.println("productId = "+productId);
		//System.err.println("dateSeleted = "+dateSeleted);
		//System.err.println("timeSlot = "+timeSlot);
		//System.err.println("paymentMode = "+paymentMode);
		//System.err.println("deliverNotify = "+deliverNotify);
		Response response = new Response();
		List<ProductId> listProd = new ArrayList<>();
		try {
			JSONArray jsonArray = new JSONArray(productId);
			for (int i = 0; i < jsonArray.length(); i++) {
				ProductId prodId = new ProductId();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int id = jsonObject.getInt("id");
				prodId.setId(id);
				String title = jsonObject.getString("title");
				prodId.setTitle(title);
				Double price = jsonObject.getDouble("price");
				prodId.setPrice(price);
				Integer quantity = jsonObject.getInt("quantity");
				prodId.setQuantity(String.valueOf(quantity));
				String type = jsonObject.getString("type");
				prodId.setType(type);
				listProd.add(prodId);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// System.err.println("listProd = " + listProd);
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		for (ProductId prod : listProd) {
			OrderDetails orderDetails = new OrderDetails();
			Random random = new Random();
			int randomNumber = random.nextInt(900000000) + 100000000;
			orderDetails.setOrderTrackingId(String.valueOf(randomNumber));
			orderDetails.setUserId(userId);
			orderDetails.setStatus(status);
			if (prod.getType().equals("header")) {
				orderDetails.setType("header");
				orderDetails.setTotalAmount(String.valueOf(prod.getPrice()));
				orderDetails.setQuantity(prod.getQuantity());
				orderDetails.setProductId(String.valueOf(prod.getId()));
				orderDetails.setDate(dateSeleted);
				orderDetails.setDeliverNotify(deliverNotify);
				orderDetails.setTimeSlot(timeSlot);
				orderDetails.setPaymentMode(paymentMode);
				orderDetailsRepository.save(orderDetails);
			}
			if (prod.getType().equals("category")) {
				orderDetails.setType("category");
				orderDetails.setTotalAmount(String.valueOf(prod.getPrice()));
				orderDetails.setDate(dateSeleted);
				orderDetails.setDeliverNotify(deliverNotify);
				orderDetails.setTimeSlot(timeSlot);
				orderDetails.setPaymentMode(paymentMode);
				orderDetails.setQuantity(prod.getQuantity());
				orderDetails.setProductId(String.valueOf(prod.getId()));
				orderDetailsRepository.save(orderDetails);
			}
			if (prod.getType().equals("product")) {
				orderDetails.setType("product");
				orderDetails.setTotalAmount(String.valueOf(prod.getPrice()));
				orderDetails.setDate(dateSeleted);
				orderDetails.setDeliverNotify(deliverNotify);
				orderDetails.setTimeSlot(timeSlot);
				orderDetails.setPaymentMode(paymentMode);
				orderDetails.setQuantity(prod.getQuantity());
				orderDetails.setProductId(String.valueOf(prod.getId()));
				orderDetailsRepository.save(orderDetails);
			}
			if (prod.getType().equals("membership")) {
				orderDetails.setStatus("completed");
				orderDetails.setType("membership");
				orderDetails.setTotalAmount(String.valueOf(prod.getPrice()));
				orderDetails.setDate(dateSeleted);
				orderDetails.setDeliverNotify(deliverNotify);
				orderDetails.setTimeSlot(timeSlot);
				orderDetails.setPaymentMode(paymentMode);
				orderDetails.setQuantity(prod.getQuantity());
				orderDetails.setProductId(String.valueOf(prod.getId()));
				OrderDetails orderSaved = orderDetailsRepository.save(orderDetails);
				if(orderSaved!=null) {
					//save to user membership details
					Optional<Login> loginUser = loginRepository.findById(Long.parseLong(userId));
					if(loginUser.isPresent()) {
						loginUser.get().setIsMember("0");
						loginUser.get().setMemberPlanId(String.valueOf(prod.getId()));
						LocalDate currentDate = LocalDate.now();
				        String memberStartDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				        
				        LocalDate startDate = LocalDate.parse(memberStartDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				        LocalDate memberEndDate = startDate.plusYears(1);
				        String formattedEndDate = memberEndDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				        loginUser.get().setMemberStartDate(memberStartDate);
				        loginUser.get().setMemberEndDate(formattedEndDate);
						loginRepository.save(loginUser.get());
					}
				}
			}
			orderDetailsList.add(orderDetails);
		}
		if (orderDetailsList != null && !orderDetailsList.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("Order Updated .. Please refer order history to track order");
			response.setData(orderDetailsList);
		} else {
			response.setStatus("ERROR");
			response.setMessage("Order list is empty ..");
			response.setData(null);
		}
		// LOGGER.info("Request completed in getOrderList ");
		return response;
	}

	// getOrderHistory
	@CrossOrigin(origins = { "*" })
	@PostMapping("getOrderHistory")
	public Response getOrderHistory(@RequestParam("mobileNumber") String mobileNumber) {
		// LOGGER.info("Request received in getOrderHistory ");

		//System.err.println("mobileNumber == " + mobileNumber);
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.findByphoneNo(mobileNumber);
		//System.err.println("loginObject == " + loginObject);
		if (loginObject != null) {
			List<OrderDetails> ordersList = orderDetailsRepository.orderList(String.valueOf(loginObject.getId()));
			for (OrderDetails orders : ordersList) {
				orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
				orders.setUserAddress(loginObject.getAddress());
				orders.setUserEmail(loginObject.getEmailId());
				orders.setUserId(loginObject.getUserName());
				orders.setUserMobile(loginObject.getPhoneNo());
				orders.setUserName(loginObject.getUserName());
				if (orders.getType().equals("header")) {
					try {
						Optional<UploadHeader> uploadHeader = uploadHeaderRepository
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(uploadHeader.get().getTitle());
						//String image = Base64.getEncoder().encodeToString(uploadHeader.get().getHeaderImage());

						//orders.setFrontEndBase64(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (orders.getType().equals("category")) {
					try {
						Optional<SubCategoryMaster> catHeader = subCategoryMasterRepo
								.findById(Long.parseLong(orders.getProductId()));
						orders.setTitle(catHeader.get().getTitle());
						//String image = Base64.getEncoder().encodeToString(catHeader.get().getSubCategoryImage1());

						//orders.setFrontEndBase64(image);
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
			response.setData(ordersList);
		}
		// LOGGER.info("Request completed in getOrderHistory ");
		return response;
	}

	// employee App pending orders
	@CrossOrigin(origins = { "*" })
	@PostMapping("getPendingOrdersForEmployee")
	public Response getPendingOrdersForEmployee(@RequestParam("mobileNumber") String mobileNumber) {
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.findByphoneNo(mobileNumber);
		if (loginObject != null) {
			List<OrderDetails> ordersList = orderDetailsRepository.orderListPendingData(String.valueOf(loginObject.getId()));
			for (OrderDetails orders : ordersList) {
				if (orders.getStatus().equals("pending")) {
					orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
					orders.setUserAddress(loginObject.getAddress());
					orders.setUserEmail(loginObject.getEmailId());
					orders.setUserId(loginObject.getUserName());
					orders.setUserMobile(loginObject.getPhoneNo());
					orders.setUserName(loginObject.getUserName());
				}
				response.setStatus("SUCCESS");
				response.setMessage("List of orders..");
				response.setData(ordersList);
			}
		}
		// LOGGER.info("Request completed in getOrderHistory ");
		return response;
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("getCompletedOrdersForEmployee")
	public Response getCompletedOrdersForEmployee(@RequestParam("mobileNumber") String mobileNumber) {
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		Login loginObject = loginRepository.findByphoneNo(mobileNumber);
		if (loginObject != null) {
			List<OrderDetails> ordersList = orderDetailsRepository.orderListCompletedData(String.valueOf(loginObject.getId()));
			for (OrderDetails orders : ordersList) {
				if (orders.getStatus().equals("pending")) {
					orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
					orders.setUserAddress(loginObject.getAddress());
					orders.setUserEmail(loginObject.getEmailId());
					orders.setUserId(loginObject.getUserName());
					orders.setUserMobile(loginObject.getPhoneNo());
					orders.setUserName(loginObject.getUserName());
				}
				response.setStatus("SUCCESS");
				response.setMessage("List of orders..");
				response.setData(ordersList);
			}
		}
		// LOGGER.info("Request completed in getOrderHistory ");
		return response;
	}

	// employeeApp Get Order by its Id
	@CrossOrigin(origins = { "*" })
	@PostMapping("getOrderDetailsData")
	public Response getOrderDetailsData(@RequestParam("orderId") String orderId) {
		//System.err.println("orderId ==" + orderId);

		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		OrderDetails order = new OrderDetails();
		OrderDetails ordersData = orderDetailsRepository.orderDataOrderTracking(orderId);
		//System.err.println("ordersData ==" + ordersData);
		Optional<Login> loginObject = loginRepository.findById(Long.parseLong(ordersData.getUserId()));
		order.setFullName(loginObject.get().getFirstName() + " " + loginObject.get().getFirstName());
		order.setUserAddress(loginObject.get().getAddress());
		order.setUserEmail(loginObject.get().getEmailId());
		order.setUserMobile(loginObject.get().getPhoneNo());
		order.setQuantity(ordersData.getQuantity());
		order.setStatus(ordersData.getStatus());
		order.setType(ordersData.getType());
		order.setOrderTrackingId(ordersData.getOrderTrackingId());
		order.setTotalAmount(ordersData.getTotalAmount());
		if (order.getType().equals("header")) {
			try {
				Optional<UploadHeader> uploadHeader = uploadHeaderRepository
						.findById(Long.parseLong(ordersData.getProductId()));
				order.setTitle(uploadHeader.get().getTitle());
				//String image = Base64.getEncoder().encodeToString(uploadHeader.get().getHeaderImage());
				//order.setFrontEndBase64(image);
				order.setDescription(uploadHeader.get().getDescription());
				order.setOriginalPirce(uploadHeader.get().getOriginalPrice());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (order.getType().equals("category")) {
			try {
				Optional<SubCategoryMaster> catHeader = subCategoryMasterRepo
						.findById(Long.parseLong(ordersData.getProductId()));
				order.setTitle(catHeader.get().getTitle());
				
				order.setDescription(catHeader.get().getDescription());
				order.setOriginalPirce(catHeader.get().getOriginalPrice());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (order.getType().equals("product")) {
			try {
				Optional<ProductImages> productHeader = productImagesRepository
						.findById(Long.parseLong(ordersData.getProductId()));
				order.setTitle(productHeader.get().getTitle());
				//String image = Base64.getEncoder().encodeToString(productHeader.get().getProductImage());
				//order.setFrontEndBase64(image);
				order.setDescription(productHeader.get().getDescription());
				order.setOriginalPirce(productHeader.get().getOriginalPrice());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		response.setStatus("SUCCESS");
		response.setMessage("List of orders..");
		response.setData(order);
		return response;
	}

	// employeeApp Change the status
	@CrossOrigin(origins = { "*" })
	@PostMapping("changeStatus")
	public Response changeStatus(@RequestParam("orderId") String orderId) {
		//System.err.println("orderId ==" + orderId);
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
		orderDetailsRepository.updateStatusByOrderTrackingId(orderId,"completed");		
		response.setStatus("SUCCESS");
		response.setMessage("Updated..");
		return response;
	}
	
	// getOrderHistory
		@CrossOrigin(origins = { "*" })
		@PostMapping("getOrderHistoryCompleted")
		public Response getOrderHistoryCompleted(@RequestParam("mobileNumber") String mobileNumber) {
			// LOGGER.info("Request received in getOrderHistory ");

			//System.err.println("mobileNumber == " + mobileNumber);
			Response response = new Response();
			List<OrderDetails> listOrders = new ArrayList<>();
			response.setStatus("FAIL");
			response.setMessage("Request Failed due to missing mobileNo");
			Login loginObject = loginRepository.findByphoneNo(mobileNumber);
			//System.err.println("loginObject == " + loginObject);
			if (loginObject != null) {
				List<OrderDetails> ordersList = orderDetailsRepository.orderListCompleted(String.valueOf(loginObject.getId()));
				for (OrderDetails orders : ordersList) {
					orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
					orders.setUserAddress(loginObject.getAddress());
					orders.setUserEmail(loginObject.getEmailId());
					orders.setUserId(loginObject.getUserName());
					orders.setUserMobile(loginObject.getPhoneNo());
					orders.setUserName(loginObject.getUserName());
					if (orders.getType().equals("header")) {
						try {
							Optional<UploadHeader> uploadHeader = uploadHeaderRepository
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(uploadHeader.get().getTitle());
							//String image = Base64.getEncoder().encodeToString(uploadHeader.get().getHeaderImage());

							orders.setFrontEndBase64(uploadHeader.get().getImageUploadDirectory());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (orders.getType().equals("category")) {
						try {
							Optional<SubCategoryMaster> catHeader = subCategoryMasterRepo
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(catHeader.get().getTitle());
							orders.setFrontEndBase64(catHeader.get().getImageUploadDirectory());

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
							orders.setFrontEndBase64(productHeader.get().getImageUploadDirectory());

							//orders.setFrontEndBase64(image);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					//membership
					if (orders.getType().equals("membership")) {
						try {
							Optional<MembershipMaster> productHeader = membershipMasterRepo
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(productHeader.get().getHeading());
							
							orders.setFrontEndBase64(imageMembership);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				response.setStatus("SUCCESS");
				response.setMessage("List of orders..");
				response.setData(ordersList);
			}
			// LOGGER.info("Request completed in getOrderHistory ");
			return response;
		}
		
		@CrossOrigin(origins = { "*" })
		@PostMapping("getOrderHistoryPending")
		public Response getOrderHistoryPending(@RequestParam("mobileNumber") String mobileNumber) {
			// LOGGER.info("Request received in getOrderHistory ");

			//System.err.println("mobileNumber == " + mobileNumber);
			Response response = new Response();
			List<OrderDetails> listOrders = new ArrayList<>();
			response.setStatus("FAIL");
			response.setMessage("Request Failed due to missing mobileNo");
			Login loginObject = loginRepository.findByphoneNo(mobileNumber);
			//System.err.println("loginObject == " + loginObject);
			if (loginObject != null) {
				List<OrderDetails> ordersList = orderDetailsRepository.orderListPending(String.valueOf(loginObject.getId()));
				for (OrderDetails orders : ordersList) {
					orders.setFullName(loginObject.getFirstName() + " " + loginObject.getFirstName());
					orders.setUserAddress(loginObject.getAddress());
					orders.setUserEmail(loginObject.getEmailId());
					orders.setUserId(loginObject.getUserName());
					orders.setUserMobile(loginObject.getPhoneNo());
					orders.setUserName(loginObject.getUserName());
					if (orders.getType().equals("header")) {
						try {
							Optional<UploadHeader> uploadHeader = uploadHeaderRepository
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(uploadHeader.get().getTitle());
							orders.setFrontEndBase64(uploadHeader.get().getImageUploadDirectory());
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (orders.getType().equals("category")) {
						try {
							Optional<SubCategoryMaster> catHeader = subCategoryMasterRepo
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(catHeader.get().getTitle());
							orders.setFrontEndBase64(catHeader.get().getImageUploadDirectory());

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (orders.getType().equals("product")) {
						try {
							Optional<ProductImages> productHeader = productImagesRepository
									.findById(Long.parseLong(orders.getProductId()));
							orders.setTitle(productHeader.get().getTitle());
							orders.setFrontEndBase64(productHeader.get().getImageUploadDirectory());

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				response.setStatus("SUCCESS");
				response.setMessage("List of orders..");
				response.setData(ordersList);
			}
			// LOGGER.info("Request completed in getOrderHistory ");
			return response;
		}

}
