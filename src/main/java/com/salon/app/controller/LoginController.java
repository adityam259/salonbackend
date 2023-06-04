package com.salon.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.Login;
import com.salon.app.repository.LoginRepository;
import com.salon.app.service.LoginService;

@RestController
public class LoginController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	LoginService loginService;

	@CrossOrigin(origins = { "*" })
	@PostMapping("validateLogin")
	public Response validateLogin(@RequestBody Login login) {
		// LOGGER.info("Request received in validate Login ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.findByphoneNo(login.getPhoneNo());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Login Validation Passed.");
				loginObject.setVerificationCode(loginService.generateVerificationCode());
				response.setData(loginObject);
			}
		}
		// LOGGER.info("Request completed in validate Login ");
		return response;
	}
	
	//Employee Login Validate
	@CrossOrigin(origins = { "*" })
	@PostMapping("employeeLoginValidate")
	public Response employeeLoginValidate(@RequestBody Login login) {
		// LOGGER.info("Request received in validate Login ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.findByphoneNo(login.getPhoneNo());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Login Validation Passed.");
				loginObject.setVerificationCode(loginService.generateVerificationCode());
				response.setData(loginObject);
			}
		}
		// LOGGER.info("Request completed in validate Login ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("getDataByMobileNo")
	public Response getDataByMobileNo(@RequestBody Login login) {
		// LOGGER.info("Request received in validate Login ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.findByphoneNo(login.getPhoneNo());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Login Validation Passed.");
				//String base64String = Base64.getEncoder().encodeToString(loginObject.getImage());
				//loginObject.setFrontendBase64(base64String);
				response.setData(loginObject);
			}
		}
		// LOGGER.info("Request completed in validate Login ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("registerUserEmployee")
	public Response registerUserEmployee(@RequestBody Login login) {
		// LOGGER.info("Request received in validate Login ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		Login loginObject = loginRepository.save(login);
		if (loginObject != null) {
			response.setStatus("SUCCESS");
			response.setMessage("User/Employee Saved");
			response.setData(loginObject);
		}
		// LOGGER.info("Request completed in validate Login ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("getLoginUserEmployee")
	public Response getLoginUserEmployee() {
		// LOGGER.info("Request received in getLoginUserEmployee ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		List<Login> loginObject = loginRepository.findAll();
		if (loginObject != null) {
			response.setStatus("SUCCESS");
			response.setMessage("User Employee list");
			response.setData(loginObject);
		}
		// LOGGER.info("Request completed in getLoginUserEmployee ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/getLoginData")
	@ResponseBody
	public Response getLoginData(@RequestParam("id") String id) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		Optional<Login> listUploadHeader = loginRepository.findById(Long.parseLong(id));
		if (listUploadHeader.get() != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader.get());
		}

		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("updateEmployeeUser")
	public Response updateEmployeeUser(@RequestParam("id") String id, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("gender") String gender,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("phoneNo") String phoneNo, @RequestParam("address") String address,
			@RequestParam("emailId") String emailId, @RequestParam("type") String type) {
		// LOGGER.info("Request received in updateEmployeeUser ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		Login userData = new Login();
		userData.setId(Long.parseLong(id));
		userData.setAddress(address);
		userData.setEmailId(emailId);
		userData.setFirstName(firstName);
		userData.setGender(gender);
		userData.setLastName(lastName);
		userData.setPassword(password);
		userData.setPhoneNo(phoneNo);
		userData.setType(type);
		userData.setUserName(userName);

		Login loginObject = loginRepository.save(userData);
		if (loginObject != null) {
			response.setStatus("SUCCESS");
			response.setMessage("User/Employee Edited");
			response.setData(loginObject);
		}
		// LOGGER.info("Request completed in updateEmployeeUser ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getLoginDataEmployee")
	@ResponseBody
	public Response getLoginDataEmployee() throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		List<Login> listUploadHeader = loginRepository.searchtype();
		if (listUploadHeader != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}

		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("validateLoginUser")
	public Response validateLoginUser(@RequestBody Login login) {
		// LOGGER.info("Request received in validate Login ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.validateLoginUser(login.getPhoneNo());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Login Validation Passed.");
				loginObject.setVerificationCode(loginService.generateVerificationCode());
				response.setData(loginObject);
			}
		}
		// LOGGER.info("Request completed in validate Login ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("getLoginUser")
	public Response getLoginUser(@RequestBody Login login) {
		// LOGGER.info("Request received in getLoginUser ");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginObject = loginRepository.validateLoginUser(login.getPhoneNo());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Login Validation Passed.");
				String base64String = Base64.getEncoder().encodeToString(loginObject.getImage());
				loginObject.setFrontendBase64(base64String);
				loginObject.setVerificationCode(loginService.generateVerificationCode());
				response.setData(loginObject);
			}
		}
		// LOGGER.info("Request completed in getLoginUser ");
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("updateProfileDetails")
	public Response updateProfileDetails(
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("emailId") String emailId, @RequestParam("address") String address,
			@RequestParam("gender") String gender, @RequestParam("phoneNo") String phoneNo,
			@RequestParam("id") String id,
			@RequestParam("alternatephoneNo") String alternatephoneNo,
			@RequestParam("dob") String dob,
			@RequestParam("dateOfAnniversary") String dateOfAnniversary) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Login Validation Failed.! Invalid MobileNo or Field");
		response.setData(null);
		if (phoneNo != null) {
			Login loginObject = loginRepository.findByphoneNo(phoneNo);
			if (loginObject != null) {
				loginObject.setAddress(address);
				loginObject.setEmailId(emailId);
				loginObject.setFirstName(firstName);
				loginObject.setLastName(lastName);
				loginObject.setGender(gender);
				loginObject.setPhoneNo(phoneNo);
				loginObject.setAlternatephoneNo(alternatephoneNo);
				loginObject.setDob(dob);
				loginObject.setDateOfAnniversary(dateOfAnniversary);
				loginRepository.save(loginObject);
				response.setStatus("SUCCESS");
				response.setMessage("Profile Updated.");
				response.setData(loginObject);
			}
		}
		return response;
	}
}
