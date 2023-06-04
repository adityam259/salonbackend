package com.salon.app.controller.viewController;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import javax.servlet.ServletContext;
import com.salon.app.dto.Response;
import com.salon.app.model.Blog;
import com.salon.app.model.CategoryListData;
import com.salon.app.model.Login;
import com.salon.app.repository.BlogRepo;

@Controller
public class BlogController {
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${image.upload.directory}") 
	private String imageUploadDirectory;

	@Autowired
	BlogRepo blogRepo;

	@GetMapping("addBlog")
	public String addBlog() {
		return "addBlog";
	}

	@GetMapping("listBlog")
	public String listBlog(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		long totalHeaders = blogRepo.count();
		Pageable pageable = PageRequest.of(page, size);
		Page<Blog> headerPage = blogRepo.getAllListBlog(pageable);
		List<Blog> listUploadHeader = headerPage.getContent();

		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of blog data");
			response.setData(listUploadHeader);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalHeaders", totalHeaders);
		model.addAttribute("totalPages", headerPage.getTotalPages());
		model.addAttribute("pageSize", size);

		return "listBlog";
	}

	// saveBlog
	@CrossOrigin(origins = { "*" })
	@PostMapping("saveBlog")
	public String saveBlog(@RequestParam("blogImage") MultipartFile file, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("description") String description, Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		if (!file.isEmpty()) {
			Blog blog = new Blog();
			blog.setBlogTitle(blogTitle);
			blog.setDescription(description);
			blog.setIsDelete("0");
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            blog.setImageUploadDirectory(serverUrl);
	            blog.setImageName(fileName);
	        }
			
			Blog blogObject = blogRepo.save(blog);
			if (blogObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Blog Added...");
				response.setData(blogObject);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		return "addBlog";
	}

	@PostMapping("deleteBlogPage")
	@ResponseBody
	public Response deleteCatPage(@RequestParam("id") String id, Model model) {
		Response response = new Response();
		blogRepo.softDeleteById(Long.parseLong(id));
		response.setStatus("SUCCESS");
		response.setMessage("Item Deleted...");
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return response;
	}

	@PostMapping("editBlogView")
	@ResponseBody
	public Map<String, String> editBlogView(@RequestParam("id") String id, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return map;
	}

	@PostMapping("updateBlogPageView")
	public String updateBlogPageView(@RequestParam("id") Long id, Model model) {
		Optional<Blog> objData = blogRepo.findById(id);
		model.addAttribute("data", objData.get());
		return "editBlog";
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("editBlogData")
	public String editBlogData(@RequestParam(value = "blogImage", required = false) MultipartFile file,
			@RequestParam("blogTitle") String blogTitle, @RequestParam("description") String description,
			@RequestParam("id") String id, Model model) throws IOException {

		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Invalid Fields");
		response.setData(null);
		Optional<Blog> objData = blogRepo.findById(Long.parseLong(id));
		if (objData.get() != null) {
			objData.get().setBlogTitle(blogTitle);
			objData.get().setDescription(description);
			objData.get().setIsDelete("0");
			if (!file.isEmpty()) {
				String uploadDir = servletContext.getRealPath("/uploads/");
				if (!StringUtils.isEmpty(uploadDir)) {
		            String fileName = file.getOriginalFilename();
		            String filePath = uploadDir + fileName;
		            File localFile = new File(filePath);
		            file.transferTo(localFile);
		            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
		            objData.get().setImageUploadDirectory(serverUrl);
		            objData.get().setImageName(fileName);
		        }
			} else {
				//objData.get().setBlogImage(objData.get().getBlogImage());
				objData.get().setImageUploadDirectory(objData.get().getImageUploadDirectory());
	            objData.get().setImageName(objData.get().getImageName());
			}
			Blog loginObject = blogRepo.save(objData.get());
			if (loginObject != null) {
				response.setStatus("SUCCESS");
				response.setMessage("Blog Updated..");
				response.setData(loginObject);
				model.addAttribute("data", loginObject);
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		return "editBlog";
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("listBlogViewAPI")
	@ResponseBody
	public Response listBlogViewAPI() {
		Response response = new Response();
		List<Blog> listBlog = blogRepo.getAllBlogList();
		
		response.setStatus("SUCCESS");
		response.setData(listBlog);
		return response;
	}
}
