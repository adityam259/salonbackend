package com.salon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salon.app.model.CategoryImage;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {
	
	@Query("SELECT e FROM CategoryImage e WHERE e.isDelete = 0")
    List<CategoryImage> findAllExcludeIsDelete();
}
