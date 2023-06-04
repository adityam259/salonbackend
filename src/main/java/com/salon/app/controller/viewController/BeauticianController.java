package com.salon.app.controller.viewController;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.Login;
import com.salon.app.repository.LoginRepository;

@Controller
public class BeauticianController {

	@Autowired
	LoginRepository loginRepository;

	@GetMapping("addBeautician")
	public String addBeautician() {
		return "addBeautician";
	}
	
	//saveEmployeeData
	@CrossOrigin(origins = { "*" })
	@PostMapping("saveEmployeeData")
	public String saveEmployeeData(@ModelAttribute("login") Login login,Model model) {
		login.setType("Employee");
		login.setIsDelete("0");
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
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		return "addBeautician";
	}
	
	@GetMapping("listBeautician")
	public String listBeautician(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		//System.err.println("page ="+ page);
		//System.err.println("size = "+size);
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = loginRepository.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<Login> headerPage = loginRepository.getAllListLogin(pageable);
		List<Login> listUploadHeader = headerPage.getContent();
		
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("totalPages", headerPage.getTotalPages());
		model.addAttribute("pageSize", size);

		return "listBeautician";
	}
	
	@PostMapping("deleteEmp")
	@ResponseBody
	public Response deleteEmp(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		loginRepository.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}
	
	@PostMapping("editEmpView")
	@ResponseBody
	public Map<String, String> editEmpView(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}
	
	@PostMapping("updateEmpPageView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<Login> objData = loginRepository.findById(id);
		model.addAttribute("data", objData.get());
		return "editEmpView";
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("editUpdateEmployeeData")
	public String editUpdateEmployeeData(@ModelAttribute("login") Login login,Model model) {
		login.setType("Employee");
		login.setIsDelete("0");
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		Optional<Login> objData = loginRepository.findById(login.getId());
		if(objData!=null) {
			objData.get().setAddress(login.getAddress());
			objData.get().setFirstName(login.getFirstName());
			objData.get().setLastName(login.getLastName());
			objData.get().setEmailId(login.getEmailId());
			objData.get().setPhoneNo(login.getPhoneNo());
			objData.get().setGender(login.getGender());
			Login loginObject = loginRepository.save(objData.get());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("User/Employee Updated..");
				response.setData(loginObject);
				model.addAttribute("data", loginObject);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		return "editEmpView";
	}
	
}
