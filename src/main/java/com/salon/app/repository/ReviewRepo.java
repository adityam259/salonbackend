package com.salon.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.salon.app.model.Blog;
import com.salon.app.model.Login;
import com.salon.app.model.ProductImages;
import com.salon.app.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

	@Query(value = "select * from review_master where is_delete=0", nativeQuery = true)
	Page<Review> getAllListReview(Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE review_master SET is_delete = '1' WHERE id = ?1", nativeQuery = true)
	void softDeleteById(Long id);
	
	@Query(value="select * from review_master where is_delete=0 LIMIT 5",nativeQuery = true)
	List<Review> getAllReviewList();
}
