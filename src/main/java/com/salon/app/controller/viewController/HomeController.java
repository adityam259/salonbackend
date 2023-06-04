package com.salon.app.controller.viewController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salon.app.dto.Response;
import com.salon.app.model.Login;

import com.salon.app.repository.LoginRepository;
import com.salon.app.repository.OrderDetailsRepository;

@Controller
public class HomeController {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("userCount", loginRepository.selectCountUsers());
		model.addAttribute("empCount", loginRepository.selectCountEmployee());
		model.addAttribute("totalOrderStatusCompleted", orderDetailsRepository.orderCompleted());
		model.addAttribute("totalOrderStatusPending", orderDetailsRepository.orderPending());
		return "index";
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getOrderCompleteCount")
	@ResponseBody
	public Response getOrderCompleteCount() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		int count = orderDetailsRepository.orderCompleted();
		response.setData(String.valueOf(count));
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/getOrderPendingCount")
	@ResponseBody
	public Response getOrderPendingCount() {
		Response response = new Response();
		response.setStatus("SUCCESS");
		int count = orderDetailsRepository.orderPending();
		response.setData(String.valueOf(count));
		return response;
	}

	@PostMapping("submitLoginView")
	public String validateLogin(@RequestParam("mobileNo") String mobileNo, Model model, HttpSession session) {
		if (mobileNo != null && !mobileNo.isEmpty()) {
			Login loginObject = loginRepository.findByphoneNo(mobileNo);
			if (loginObject != null) {
				session.setAttribute("loggedInUser", loginObject);
				model.addAttribute("userCount", loginRepository.selectCountUsers());
				model.addAttribute("empCount", loginRepository.selectCountEmployee());
				model.addAttribute("totalOrderStatusCompleted", orderDetailsRepository.orderCompleted());
				model.addAttribute("totalOrderStatusPending", orderDetailsRepository.orderPending());
				return "index";
			} else {
				model.addAttribute("message", "Invalid Mobile Number");
				return "login";
			}

		}
		model.addAttribute("message", "Mobile number cannot be empty");
		return "login";
	}

}
