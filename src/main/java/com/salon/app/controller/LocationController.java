package com.salon.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.salon.app.dto.Response;
import com.salon.app.model.Location;
import com.salon.app.repository.LocationRepo;

@RestController
public class LocationController {
	
	@Autowired
	LocationRepo locationRepo;

	public LocationController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private final RestTemplate restTemplate;
	private final String apiUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=";

	@CrossOrigin(origins = { "*" })
	@PostMapping("getLocation")
	public Response callApi(@RequestParam("lat") String lat, @RequestParam("lon") String lon) {
		Response responseObj = new Response();
		HttpHeaders headers = new HttpHeaders();
		StringBuilder sb = new StringBuilder();
		sb.append(apiUrl);
		sb.append(lat + "&lon=");
		sb.append(lon + "&zoom=18&addressdetails=1");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity, String.class);
		//System.err.println("response = " + response.getBody());
		JSONObject jObject = new JSONObject(response.getBody());
		Map<String, String> map = new HashMap<>();
		//System.err.println("jObject = "+jObject);
		JSONObject subObject = (JSONObject) jObject.get("address");
		map.put("country", subObject.getString("country"));
		map.put("city", subObject.getString("city"));
		map.put("state_district", subObject.getString("state_district"));
		map.put("postcode", subObject.getString("postcode"));
		if(subObject.has("suburb")) {
		map.put("suburb", subObject.getString("suburb"));
		}
		map.put("state", subObject.getString("state"));
		responseObj.setData(map);
		responseObj.setMessage("SUCCESS");
		responseObj.setStatus("SUCCESS");
		return responseObj;
	}
	
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("AddLocationAddress")
	public Response AddLocation(@RequestParam("flatApartment") String flatApartment, 
			@RequestParam("streetAddress") String streetAddress,
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("zipCode") String zipCode,
			@RequestParam("userId") String userId) {
		Response responseObj = new Response();
		Location location = new Location();
		location.setCity(city);
		location.setFlatApartment(flatApartment);
		location.setState(state);
		location.setStreetAddress(streetAddress);
		location.setUserId(userId);
		location.setZipCode(zipCode);
		Location locationSaved = locationRepo.save(location);
		if(locationSaved!=null) {
			responseObj.setData(locationSaved);
			responseObj.setMessage("SUCCESS");
			responseObj.setStatus("Location Added");	
		}else {
			responseObj.setData(null);
			responseObj.setMessage("Failed");
			responseObj.setStatus("Location Adding failed!Please try after some time.");	
		}
		
		return responseObj;
	}
	
	@CrossOrigin(origins = { "*" })
	@PostMapping("getLocationAddressByUserId")
	public Response AddLocation(
			@RequestParam("userId") String userId) {
		Response responseObj = new Response();
		List<Location> locationSaved = locationRepo.findByuserId(userId);
		if(locationSaved!=null) {
			responseObj.setData(locationSaved);
			responseObj.setMessage("SUCCESS");
			responseObj.setStatus("SUCCESS");	
		}else {
			responseObj.setData(null);
			responseObj.setMessage("Failed");
			responseObj.setStatus("Location Adding failed!Please try after some time.");	
		}
		//System.err.println(responseObj.getData());
		return responseObj;
	}

}
