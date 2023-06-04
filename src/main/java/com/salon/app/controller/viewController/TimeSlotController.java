package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CancelationInstruction;
import com.salon.app.model.TimeSlot;
import com.salon.app.repository.TimeSlotRepo;

@Controller
public class TimeSlotController {
	
	@Autowired
	TimeSlotRepo timeSlotRepo;
	
	@GetMapping("addTimeSlot")
	public String addTimeSlot(Model model) {
		List<TimeSlot> listTimeSlot = timeSlotRepo.findAll();
		model.addAttribute("data", listTimeSlot);
		return "addTimeSlot";
	}

	@PostMapping("saveTimeSlotData")
	public String saveTimeSlotData(
			@RequestParam("timeSlot") String timeSlotData,
			Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Time Slot Addition Failed..");
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setTimeSlot(timeSlotData);
			timeSlot.setIsDelete("0");
			TimeSlot savedCatListData = timeSlotRepo.save(timeSlot);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "TimeSlot  Added Successfully...");
			}
		return "addTimeSlot";
	}
	
	@CrossOrigin(origins = { "*" })
	@GetMapping("getAllTimeSlots")
	@ResponseBody
	public Response getAllTimeSlots(Model model) {
		Response response = new Response();
		List<TimeSlot> deliveryObject = timeSlotRepo.findAll();
		if(deliveryObject !=null ) {
			//model.addAttribute("data", deliveryObject.get());
			response.setStatus("SUCCESS");
			response.setMessage("");
			response.setData(deliveryObject);
		}
		return response;
	}
}
