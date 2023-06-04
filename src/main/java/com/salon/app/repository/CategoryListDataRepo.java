package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salon.app.model.CategoryListData;

@Repository
public interface CategoryListDataRepo extends JpaRepository<CategoryListData, Long> {
	
	@Query(value="select * from category_list_data where is_delete=0",nativeQuery = true)
	Page<CategoryListData> getAllListCategory(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE category_list_data SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from category_list_data where is_delete=0",nativeQuery = true)
	List<CategoryListData> getAllCategoryData();
	
	@Query(value="select * from category_list_data where is_delete=0",nativeQuery = true)
	List<CategoryListData> getAllListCategoryData();
	
	
	
	
}
