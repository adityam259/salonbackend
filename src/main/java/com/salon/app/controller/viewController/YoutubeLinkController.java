package com.salon.app.controller.viewController;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.YoutubeLinks;
import com.salon.app.repository.YoutubeLinksRepo;

@Controller
public class YoutubeLinkController {
	@Autowired
	YoutubeLinksRepo youtubeLinksRepo;

	@GetMapping("addYouTubelink")
	public String addYouTubelink(Model model) {
		return "addYoutubeLink";
	}

	// saveYoutubeLinkData
	@PostMapping("saveYoutubeLinkData")
	public String saveYoutubeLinkData(@RequestParam("youtubeLink") String youlink,
			@RequestParam("linkName") String linkName, Model model) throws IOException {
		model.addAttribute("status", "FAILED");
		model.addAttribute("message", "Category Addition Failed..");
		if (!youlink.isEmpty()) {
			YoutubeLinks data = new YoutubeLinks();
			data.setLinkName(linkName);
			data.setYoutubeLink(youlink);
			data.setIsDelete("0");
			YoutubeLinks savedCatListData = youtubeLinksRepo.save(data);
			if (savedCatListData != null) {
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("message", "Link Added Successfully...");
			}
		}
		return "addYoutubeLink";
	}

	// listYouTubelink
	@GetMapping("listYouTubelink")
	public String listYouTubelink(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = youtubeLinksRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<YoutubeLinks> headerPage = youtubeLinksRepo.getAllListLinks(pageable);
		List<YoutubeLinks> listUploadHeader = headerPage.getContent();

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

		return "listYouTubelink";
	}

	// deletelinkPage
	@PostMapping("deletelinkPage")
	@ResponseBody
	public Response deletelinkPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		youtubeLinksRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@PostMapping("editYoutubeLinkView")
	@ResponseBody
	public Map<String, String> editYoutubeLinkView(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}

	// updateLinkPageView
	@PostMapping("updateLinkPageView")
	public String updatepageView(@RequestParam("id") Long id, Model model) {
		Optional<YoutubeLinks> objData = youtubeLinksRepo.findById(id);
		model.addAttribute("data", objData.get());
		return "editLinkPageView";
	}

	// updateYoutubeLinkData
	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateYoutubeLinkData")
	public String updateYoutubeLinkData(@RequestParam("youtubeLink") String youlink,
			@RequestParam("linkName") String linkName, @RequestParam("id") String id, Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to update data.");
		Optional<YoutubeLinks> objData = youtubeLinksRepo.findById(Long.parseLong(id));
		if (objData != null) {
			YoutubeLinks uploadHeader = new YoutubeLinks();
			uploadHeader.setLinkName(linkName);
			uploadHeader.setYoutubeLink(youlink);
			uploadHeader.setId(Long.parseLong(id));
			uploadHeader.setIsDelete("0");
			YoutubeLinks uploadedStatus = youtubeLinksRepo.save(uploadHeader);
			if (uploadedStatus != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Data updated Successfully...");
				response.setData(uploadedStatus);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "editLinkPageView";
	}

	// getAllYoutubeLinkAPI
	@CrossOrigin(origins = { "*" })
	@GetMapping("/getAllYoutubeLinkAPI")
	@ResponseBody
	public Response getAllYoutubeLinkAPI() {
		Response response = new Response();
		List<YoutubeLinks> youLink = youtubeLinksRepo.getAllListLinksAPI();
		response.setData(youLink);
		response.setMessage("Youtube Links ..");
		response.setStatus("SUCCESS");
		return response;
	}
}
