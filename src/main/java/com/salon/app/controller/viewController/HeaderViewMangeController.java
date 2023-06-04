package com.salon.app.controller.viewController;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

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
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.UploadHeaderRepository;

@Controller
public class HeaderViewMangeController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;
	
	@Value("${image.folder}") 
	private String imageFolder;

	@Autowired
	UploadHeaderRepository uploadHeaderRepository;

	@GetMapping("addHeaderPage")
	public String addHeaderView() {
		return "addHeaderView";
	}

	@PostMapping("updatepageView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<UploadHeader> objData = uploadHeaderRepository.findById(id);
		model.addAttribute("data", objData.get());
		return "editHeaderView";
	}

	@PostMapping("deleteHeaderPage")
	@ResponseBody
	public Response deleteHeaderPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		uploadHeaderRepository.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@PostMapping("editHeaderView")
	@ResponseBody
	public Map<String, String> updateHeaderPage(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}

	@GetMapping("listHeaderTables")
	public String listHeaderTables(Model model,@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
	    long totalHeaders = uploadHeaderRepository.count();
	    Pageable pageable = PageRequest.of(page, size);
	    Page<UploadHeader> headerPage = uploadHeaderRepository.getAllListHeader(pageable);
	    List<UploadHeader> listUploadHeader = headerPage.getContent();
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
		return "listHeaderTables";
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateHeaderImageData")
	public String updateHeaderImageData(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, @RequestParam("originalPrice") String originalPrice,
			@RequestParam("id") String id,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2, Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<UploadHeader> objData = uploadHeaderRepository.findById(Long.parseLong(id));
		if(objData!=null) {
		UploadHeader uploadHeader = new UploadHeader();
        String uploadDir = servletContext.getRealPath(imageFolder);
		if (file != null && !file.isEmpty()) {
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            uploadHeader.setImageUploadDirectory(serverUrl);
	            uploadHeader.setHeaderName(fileName);
	        }
		}else {
			uploadHeader.setImageUploadDirectory(objData.get().getImageUploadDirectory());
			uploadHeader.setHeaderName(objData.get().getHeaderName());
		}
		uploadHeader.setHeaderName(file.getOriginalFilename());
		uploadHeader.setDescription(description);
		uploadHeader.setRate(rate);
		uploadHeader.setTime(time);
		uploadHeader.setTitle(title);
		uploadHeader.setOriginalPrice(originalPrice);
		uploadHeader.setId(Long.parseLong(id));
		uploadHeader.setIsDelete("0");
		uploadHeader.setMembershipDiscount(membershipDiscount);
		uploadHeader.setMembershipDiscount2(membershipDiscount2);		
		UploadHeader uploadedStatus = uploadHeaderRepository.save(uploadHeader);
		if (uploadedStatus != null) {
			response.setStatus("SUCCESS");
			response.setMessage("Data updated Successfully...");
			response.setData(uploadedStatus);
		}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editHeaderView";
	}

}
