package com.salon.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salon.app.dto.Response;
import com.salon.app.dto.SearchResults;

@RestController
public class SearchController {

	@PersistenceContext
	private EntityManager entityManager;

	@CrossOrigin(origins = { "*" })
	@PostMapping("searchText")
	public Response searchText(@RequestParam("name") String name) {
		Response response = new Response();
		String searchTerm = name;
		if(searchTerm.isEmpty()) {
			response.setData(new ArrayList<>());
			response.setStatus("SUCCESS");
			response.setMessage("Search Data");
			return response;
		}
		//System.err.println(searchTerm);
		Query query = entityManager.createNativeQuery(
				"SELECT e.id, e.title,\r\n"
				+ "  CASE\r\n"
				+ "    WHEN e.title IN (SELECT title FROM header_master) THEN 'header'\r\n"
				+ "    WHEN e.title IN (SELECT title FROM sub_category_master) THEN 'category'\r\n"
				+ "    WHEN e.title IN (SELECT title FROM product_master) THEN 'product'\r\n"
				+ "  END AS source\r\n"
				+ "FROM (\r\n"
				+ "  SELECT h.id, h.title FROM header_master h \r\n"
				+ "  UNION SELECT c.id, c.title FROM sub_category_master c \r\n"
				+ "  UNION SELECT p.id, p.title FROM product_master p\r\n"
				+ ") AS e \r\n"
				+ "WHERE e.title LIKE :searchTerm");
		query.setParameter("searchTerm", "%" + searchTerm + "%");
		List<Object[]> results = query.getResultList();
		List<SearchResults> objects = new ArrayList<>();
		for (Object[] result : results) {
			SearchResults obj = new SearchResults();
			obj.setId(result[0].toString());
			obj.setTitle(result[1].toString());
			obj.setSource(result[2].toString());
		    objects.add(obj);
		}
		//System.err.println(objects);
		response.setData(objects);
		response.setStatus("SUCCESS");
		response.setMessage("Search Data");
		return response;
	}

}
