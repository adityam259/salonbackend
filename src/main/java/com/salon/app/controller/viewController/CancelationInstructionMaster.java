package com.salon.app.controller.viewController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salon.app.dto.Response;
import com.salon.app.model.CancelationInstruction;
import com.salon.app.model.DeliveryInstruction;
import com.salon.app.repository.CancelationInstructionRepo;

@Controller
public class CancelationInstructionMaster {
	
	@Autowired
	CancelationInstructionRepo cancelationInstructionRepo;
	
	@GetMapping("cancelationInstructionListData")
	public String cancelationInstructionListData(Model model) {
		String id = "1";
		Optional<CancelationInstruction> deliveryObject = cancelationInstructionRepo.findById(Long.parseLong(id));
		if(deliveryObject.isPresent()) {
			model.addAttribute("data", deliveryObject.get());
		}
		return "cancelationInstructionList";
	}
	
	@PostMapping("editCancelationInstruction")
	public String editCancelationInstruction(@RequestParam("id") String id,
			@RequestParam("cancelationInstruction") String deliveryInstructionData,Model model) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<CancelationInstruction> deliveryObject = cancelationInstructionRepo.findById(Long.parseLong(id));
		if(deliveryObject.isPresent()) {
			CancelationInstruction deliveryInstruction = new CancelationInstruction();
			deliveryInstruction.setCancelationInstruction(deliveryInstructionData);
			CancelationInstruction data = cancelationInstructionRepo.save(deliveryInstruction);
			if(data!=null) {
				response.setStatus("SUCCESS");
				response.setMessage("Data updated Successfully...");
				response.setData(data);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "cancelationInstructionList";
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("fetchCancelationInstructions")
	@ResponseBody
	public Response fetchCancelationInstructions	(Model model) {
		Response response = new Response();
		String id = "1";
		Optional<CancelationInstruction> deliveryObject = cancelationInstructionRepo.findById(Long.parseLong(id));
		if(deliveryObject.isPresent()) {
			//model.addAttribute("data", deliveryObject.get());
			response.setStatus("SUCCESS");
			response.setMessage("");
			response.setData(deliveryObject.get());
		}
		return response;
	}
}
