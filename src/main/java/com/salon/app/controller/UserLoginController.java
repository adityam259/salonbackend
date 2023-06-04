package com.salon.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.model.Login;
import com.salon.app.repository.LoginRepository;

@RestController
public class UserLoginController {

	@Autowired
	LoginRepository loginRepository;

	@CrossOrigin(origins = { "*" })
	@PostMapping("saveUserLogin")
	public Response valiateUserLogin(@RequestBody Login login) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Registeration failed...");
		response.setData(null);
		if (login.getPhoneNo() != null) {
			Login loginMobileObject = loginRepository.findByphoneNo(login.getPhoneNo());
			if (loginMobileObject != null) {
				response.setStatus("FAIL");
				response.setMessage("Mobile Number Already Exist...");
				response.setData(null);
				return response;
			}
			login.setType("User");
			Login loginObject = loginRepository.save(login);
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Registered Successfully...");
				response.setData(loginObject);
			}

		}

		return response;
	}

}
