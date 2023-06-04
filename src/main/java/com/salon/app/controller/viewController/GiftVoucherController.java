package com.salon.app.controller.viewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.GiftVoucher;
import com.salon.app.model.Login;
import com.salon.app.model.OrderDetails;
import com.salon.app.model.ProductImages;
import com.salon.app.model.SubCategoryMaster;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.GiftVoucherRepo;
import java.io.File;
import javax.servlet.ServletContext;
@Controller
public class GiftVoucherController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;
	
	@Autowired
	GiftVoucherRepo giftVoucherRepo;

	@GetMapping("addGiftVoucher")
	public String addGiftVoucher() {
		return "addGiftVoucher";
	}
	
	@PostMapping("saveGiftData")
	public String saveGiftData(@RequestParam("giftImage") MultipartFile file,
			@RequestParam("giftTitle") String title,
			@RequestParam("description") String description,
			@RequestParam("giftPrice") String price,Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		if (!file.isEmpty()) {
			GiftVoucher giftVoucher = new GiftVoucher();
			giftVoucher.setDescription(description);
			giftVoucher.setGiftPrice(price);
			giftVoucher.setGiftTitle(title);
			giftVoucher.setIsDelete("0");
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            giftVoucher.setImageUploadDirectory(serverUrl);
	            giftVoucher.setImageName(fileName);
	        }
			GiftVoucher savedCatListData = giftVoucherRepo.save(giftVoucher);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Gift Voucher Added Successfully...");
			}
		}
		return "addGiftVoucher";
	}
	
	@GetMapping("listGiftVoucherView")
	public String listGiftVoucherView(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = giftVoucherRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<GiftVoucher> headerPage = giftVoucherRepo.getAllListGiftVoucher(pageable);
		List<GiftVoucher> listUploadHeader = headerPage.getContent();
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of gift voucher data");
			response.setData(listUploadHeader);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("totalPages", headerPage.getTotalPages());
		model.addAttribute("pageSize", size);

		return "listGiftVoucherView";
	}
	
	@PostMapping("deleteGiftPage")
	@ResponseBody
	public Response deleteGiftPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		giftVoucherRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}
	
	@PostMapping("editGiftView")
	@ResponseBody
	public Map<String, String> editGiftView(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}
	
	@PostMapping("updateGiftPageView")
	public String updateGiftPageView(@RequestParam("id") Long id, Model model) {
		Optional<GiftVoucher> objData = giftVoucherRepo.findById(id);
		model.addAttribute("data", objData.get());
		return "editGiftView";
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateGiftData")
	public String updateGiftData(@RequestParam("giftImage") MultipartFile file,
			@RequestParam("giftTitle") String title,
			@RequestParam("description") String description,
			@RequestParam("giftPrice") String price, @RequestParam("id") String id, Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<GiftVoucher> objData = giftVoucherRepo.findById(Long.parseLong(id));
		if (objData != null) {
			GiftVoucher uploadHeader = new GiftVoucher();
			if (file != null && !file.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = file.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            file.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            uploadHeader.setImageUploadDirectory(serverUrl);
		            uploadHeader.setImageName(fileName);
		        }
			} else {
				uploadHeader.setImageUploadDirectory(objData.get().getImageUploadDirectory());
	            uploadHeader.setImageName(objData.get().getImageName());
			}
			uploadHeader.setDescription(description);
			uploadHeader.setGiftPrice(price);
			uploadHeader.setGiftTitle(title);
			uploadHeader.setId(Long.parseLong(id));
			uploadHeader.setIsDelete("0");
			GiftVoucher uploadedStatus = giftVoucherRepo.save(uploadHeader);
			if (uploadedStatus != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Data updated Successfully...");
				response.setData(uploadedStatus);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editGiftView";
	}
	
	@CrossOrigin(origins = { "*" })
	@GetMapping("getGiftVouchers")
	@ResponseBody
	public Response getGiftVouchers() {
		// LOGGER.info("Request received in getOrderHistory ");
		Response response = new Response();
		List<OrderDetails> listOrders = new ArrayList<>();
		response.setStatus("FAIL");
		response.setMessage("Request Failed due to missing mobileNo");
			List<GiftVoucher> ordersList = giftVoucherRepo.getListGiftVoucher();
			response.setStatus("SUCCESS");
			response.setMessage("List of orders..");
			response.setData(ordersList);
		return response;
	}

}
