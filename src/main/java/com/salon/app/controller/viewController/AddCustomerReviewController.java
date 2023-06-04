package com.salon.app.controller.viewController;

import java.util.Base64;
import java.util.List;

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
import com.salon.app.model.Blog;
import com.salon.app.model.Login;
import com.salon.app.model.Review;
import com.salon.app.repository.ReviewRepo;

@Controller
public class AddCustomerReviewController {
	
	@Autowired
	ReviewRepo reviewRepo;
	
	@GetMapping("addReview")
	public String addReview() {
		return "addReview";
	}
	
	 @PostMapping("/saveReviewData")
	    public String submitReview(@ModelAttribute("Review") Review reviewForm,Model model) {
		 Response response = new Response();
			response.setStatus("FAIL");
			response.setMessage("Invalid Fields");
	        Review review = new Review(reviewForm.getRating(), reviewForm.getReview(),reviewForm.getName(),
	        		"0");
	        Review savedReview = reviewRepo.save(review);
	        if (savedReview != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Review Submitted");
				response.setData(savedReview);
			}
			model.addAttribute("status", response.getStatus());
			model.addAttribute("message", response.getMessage());

	        return "addReview";
	    }
	 
	 
	 @GetMapping("listReview")
		public String listReview(Model model, @RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "5") int size) {
			Response response = new Response();
			//System.err.println("page ="+ page);
			//System.err.println("size = "+size);
			response.setStatus("FAIL");
			response.setMessage("No Data Avaialble.");
			long totalHeaders = reviewRepo.count();
			Pageable pageable = PageRequest.of(page, size);
			Page<Review> headerPage = reviewRepo.getAllListReview(pageable);
			List<Review> listUploadHeader = headerPage.getContent();
			
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

			return "listReview";
		}
	 
	 //deleteListReview
	 @PostMapping("deleteListReview")
		@ResponseBody
		public Response deleteListReview(@RequestParam("id") String id, Model model) {
			Response response = new Response();
			reviewRepo.softDeleteById(Long.parseLong(id));
			response.setStatus("SUCCESS");
			response.setMessage("Review Deleted...");
			model.addAttribute("status", response.getStatus());
			model.addAttribute("message", response.getMessage());
			model.addAttribute("data", response.getData());
			return response;
		}
	 
	 
	 @CrossOrigin(origins = { "*" })
		@GetMapping("listReviewViewAPI")
		@ResponseBody
		public Response listReviewViewAPI() {
			Response response = new Response();
			List<Review> listBlog = reviewRepo.getAllReviewList();
			response.setStatus("SUCCESS");
			response.setData(listBlog);
			return response;
		}
}
