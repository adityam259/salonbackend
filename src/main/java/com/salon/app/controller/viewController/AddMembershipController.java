package com.salon.app.controller.viewController;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salon.app.dto.Response;
import com.salon.app.model.MembershipMaster;
import com.salon.app.model.Review;
import com.salon.app.repository.MembershipMasterRepo;

@Controller
public class AddMembershipController {

	@Autowired
	MembershipMasterRepo membershipMasterRepo;

	@GetMapping("addMemberShipMaster")
	public String addMemberShipMaster() {
		return "addMemberShipMaster";
	}

	@PostMapping("/addMembershipDetails")
	public String addMembershipDetails(@ModelAttribute("heading") String heading,
			@ModelAttribute("description") String description,
			@ModelAttribute("termsConditions") String termsConditions,
			@ModelAttribute("price") String price, Model model) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		MembershipMaster membershipData = new MembershipMaster(heading, description, termsConditions, "0",price);
		MembershipMaster savedMembership = membershipMasterRepo.save(membershipData);
		if (savedMembership != null) {
			response.setStatus("SUCCESS");
			response.setMessage("Membership Submited");
			response.setData(savedMembership);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());

		return "addMemberShipMaster";
	}

	// listMemberShipMaster
	@GetMapping("listMemberShipMaster")
	public String listMemberShipMaster(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		//System.err.println("page =" + page);
		//System.err.println("size = " + size);
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = membershipMasterRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<MembershipMaster> headerPage = membershipMasterRepo.getAllListMembership(pageable);
		List<MembershipMaster> listUploadHeader = headerPage.getContent();

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

		return "listMemberShip";
	}

	// deleteListReview
	@PostMapping("deleteListMembership")
	@ResponseBody
	public Response deleteListMembership(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		membershipMasterRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Membership Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("fetchAllMembershipData")
	@ResponseBody
	public Response fetchAllMembershipData() {
		Response response = new Response();
		List<MembershipMaster> membershipData = membershipMasterRepo.getAllListMembership();
		response.setStatus("SUCCESS");
		response.setData(membershipData);
		return response;
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("fetchAllMembershipDataWithId")
	@ResponseBody
	public Response fetchAllMembershipDataWithId(@ModelAttribute("id") String id) {
		Response response = new Response();
		Optional<MembershipMaster> membershipData = membershipMasterRepo.findById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setData(membershipData.get());
		return response;
	}
	
	

}
