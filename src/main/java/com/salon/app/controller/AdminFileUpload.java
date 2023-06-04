package com.salon.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.util.StringUtils;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salon.app.dto.Response;
import com.salon.app.model.CategoryImage;
import com.salon.app.model.ProductImages;
import com.salon.app.model.UploadHeader;
import com.salon.app.repository.CategoryImageRepository;
import com.salon.app.repository.ProductImagesRepository;
import com.salon.app.repository.UploadHeaderRepository;

@Controller
public class AdminFileUpload {
	
	@Autowired
    private ServletContext servletContext;

	@Autowired
	UploadHeaderRepository uploadHeaderRepository;

	@Autowired
	CategoryImageRepository categoryImageRepository;

	@Autowired
	ProductImagesRepository productImagesRepository;
	
	@Value("${image.upload.directory}") // Assuming you have configured the directory path in your application properties
	private String imageUploadDirectory;

	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile file, Model model) throws IOException {
		if (!file.isEmpty()) {
			//byte[] imageData = file.getBytes();
			UploadHeader uploadHeader = new UploadHeader();
			//uploadHeader.setHeaderImage(imageData);
			uploadHeader.setHeaderName(file.getOriginalFilename());
			UploadHeader uploadedStatus = uploadHeaderRepository.save(uploadHeader);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
			} else {
				model.addAttribute("status", "file upload failed");
			}
		}
		return "admin/uploadHeaderImages";
	}

	@PostMapping("/uploadCategoryImage")
	public String uploadCategoryImage(@RequestParam("imageFile") MultipartFile file, Model model) throws IOException {
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			CategoryImage uploadCategoryHeader = new CategoryImage();
			uploadCategoryHeader.setCategoryImage(imageData);
			uploadCategoryHeader.setCategoryName(file.getOriginalFilename());
			CategoryImage uploadedCategoryStatus = categoryImageRepository.save(uploadCategoryHeader);
			if (uploadedCategoryStatus != null) {
				model.addAttribute("status", "file uploaded");
			} else {
				model.addAttribute("status", "file upload failed");
			}
		}
		return "admin/uploadCategory";
	}

	@PostMapping("/uploadProductImage")
	public String uploadProductImage(@RequestParam("imageFile") MultipartFile file, Model model) throws IOException {
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			ProductImages uploadproduct = new ProductImages();
			//uploadproduct.setProductImage(imageData);
			uploadproduct.setProductName(file.getOriginalFilename());
			ProductImages uploadedStatus = productImagesRepository.save(uploadproduct);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
			} else {
				model.addAttribute("status", "file upload failed");
			}
		}
		return "admin/uploadProductImage";
	}

	//
	@CrossOrigin(origins = { "*" })
	@PostMapping("/submitHeaderImage")
	public String  submitHeaderImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description,
			@RequestParam("originalPrice") String originalPrice,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,
			Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			UploadHeader uploadHeader = new UploadHeader();
			uploadHeader.setHeaderName(file.getOriginalFilename());
			uploadHeader.setDescription(description);
			uploadHeader.setRate(rate);
			uploadHeader.setTime(time);
			uploadHeader.setTitle(title);
			uploadHeader.setOriginalPrice(originalPrice);
			uploadHeader.setIsDelete("0");
			uploadHeader.setMembershipDiscount(membershipDiscount);
			uploadHeader.setMembershipDiscount2(membershipDiscount2);
			String uploadDir = servletContext.getRealPath("/uploads/");
			if (!StringUtils.isEmpty(uploadDir)) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            File localFile = new File(filePath);
	            file.transferTo(localFile);
	            String serverUrl = imageUploadDirectory + "/uploads/" + fileName;
	            uploadHeader.setImageUploadDirectory(serverUrl);
	        }
			UploadHeader uploadedStatus = uploadHeaderRepository.save(uploadHeader);
			if (uploadedStatus != null) {
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
				 
			}
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		return "addHeaderView";
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/listOfHeader")
	@ResponseBody
	public String submitHeaderImage(Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		List<UploadHeader> listUploadHeader = uploadHeaderRepository.findAll();
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}
		model.addAttribute("status", response.getStatus());
		model.addAttribute("message", response.getMessage());
		model.addAttribute("data", response.getData());
		return "listHeaderTables";
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/getHeader")
	@ResponseBody
	public Response getHeader(@RequestParam("id") String id) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		Optional<UploadHeader> listUploadHeader = uploadHeaderRepository.findById(Long.parseLong(id));
		if (listUploadHeader.get() != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader.get());
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateHeaderImage")
	@ResponseBody
	public Response updateHeaderImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, @RequestParam("id") String id,
			@RequestParam("membershipDiscount") String membershipDiscount,
			@RequestParam("membershipDiscount2") String membershipDiscount2,Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			//byte[] imageData = file.getBytes();
			UploadHeader uploadHeader = new UploadHeader();
			//uploadHeader.setHeaderImage(imageData);
			uploadHeader.setHeaderName(file.getOriginalFilename());
			uploadHeader.setDescription(description);
			uploadHeader.setRate(rate);
			uploadHeader.setTime(time);
			uploadHeader.setTitle(title);
			uploadHeader.setId(Long.parseLong(id));
			uploadHeader.setMembershipDiscount(membershipDiscount);
			uploadHeader.setMembershipDiscount2(membershipDiscount2);
			
			UploadHeader uploadedStatus = uploadHeaderRepository.save(uploadHeader);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/submitCategoryImage")
	@ResponseBody
	public Response submitCategoryImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description,
			@RequestParam("originalPrice") String originalPrice
			, Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			CategoryImage uploadCategory = new CategoryImage();
			uploadCategory.setCategoryImage(imageData);
			uploadCategory.setCategoryName(file.getOriginalFilename());
			uploadCategory.setDescription(description);
			uploadCategory.setRate(rate);
			uploadCategory.setTime(time);
			uploadCategory.setTitle(title);
			uploadCategory.setOriginalPrice(originalPrice);
			CategoryImage uploadedStatus = categoryImageRepository.save(uploadCategory);
			if (uploadedStatus != null) {
				model.addAttribute("status", "File uploaded with data");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/listOfCategory")
	@ResponseBody
	public Response listOfCategory() throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		List<CategoryImage> listUploadHeader = categoryImageRepository.findAll();
		for (CategoryImage header : listUploadHeader) {
			String image = Base64.getEncoder().encodeToString(header.getCategoryImage());
			header.setFrontEndBase64(image);
		}
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/getCategory")
	@ResponseBody
	public Response getCategory(@RequestParam("id") String id) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		Optional<CategoryImage> listUploadHeader = categoryImageRepository.findById(Long.parseLong(id));
		String image = Base64.getEncoder().encodeToString(listUploadHeader.get().getCategoryImage());
		listUploadHeader.get().setFrontEndBase64(image);
		if (listUploadHeader.get() != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader.get());
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateCategroyImage")
	@ResponseBody
	public Response updateCategroyImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, @RequestParam("id") String id, Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			CategoryImage uploadHeader = new CategoryImage();
			uploadHeader.setCategoryImage(imageData);
			uploadHeader.setCategoryName(file.getOriginalFilename());
			uploadHeader.setDescription(description);
			uploadHeader.setRate(rate);
			uploadHeader.setTime(time);
			uploadHeader.setTitle(title);
			uploadHeader.setId(Long.parseLong(id));
			CategoryImage uploadedStatus = categoryImageRepository.save(uploadHeader);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}

		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/submitProductImage")
	@ResponseBody
	public Response submitProductImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, Model model) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			ProductImages uploadCategory = new ProductImages();
			//uploadCategory.setProductImage(imageData);
			uploadCategory.setProductName(file.getOriginalFilename());
			uploadCategory.setDescription(description);
			uploadCategory.setRate(rate);
			uploadCategory.setTime(time);
			uploadCategory.setTitle(title);
			ProductImages uploadedStatus = productImagesRepository.save(uploadCategory);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/listOfProduct")
	@ResponseBody
	public Response listOfProduct() throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		List<ProductImages> listUploadHeader = productImagesRepository.findAll();
		
		if (listUploadHeader != null && !listUploadHeader.isEmpty()) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader);
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/getProduct")
	@ResponseBody
	public Response getProduct(@RequestParam("id") String id) throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("No Data Avaialble.");
		Optional<ProductImages> listUploadHeader = productImagesRepository.findById(Long.parseLong(id));
		//String image = Base64.getEncoder().encodeToString(listUploadHeader.get().getProductImage());
		//listUploadHeader.get().setFrontEndBase64(image);
		if (listUploadHeader.get() != null) {
			response.setStatus("SUCCESS");
			response.setMessage("List of header data");
			response.setData(listUploadHeader.get());
		}
		return response;
	}

	@CrossOrigin(origins = { "*" })
	@PostMapping("/updateProductImage")
	@ResponseBody
	public Response updateProductImage(@RequestParam("headerImage") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("rate") String rate, @RequestParam("time") String time,
			@RequestParam("description") String description, @RequestParam("id") String id, Model model)
			throws IOException {
		Response response = new Response();
		response.setStatus("FAIL");
		response.setMessage("Unable to save data.");
		if (!file.isEmpty()) {
			byte[] imageData = file.getBytes();
			ProductImages uploadHeader = new ProductImages();
			//uploadHeader.setProductImage(imageData);
			uploadHeader.setProductName(file.getOriginalFilename());
			uploadHeader.setDescription(description);
			uploadHeader.setRate(rate);
			uploadHeader.setTime(time);
			uploadHeader.setTitle(title);
			uploadHeader.setId(Long.parseLong(id));
			ProductImages uploadedStatus = productImagesRepository.save(uploadHeader);
			if (uploadedStatus != null) {
				model.addAttribute("status", "file uploaded");
				response.setStatus("SUCCESS");
				response.setMessage("file uploaded with data");
			}
		}
		return response;
	}
}
