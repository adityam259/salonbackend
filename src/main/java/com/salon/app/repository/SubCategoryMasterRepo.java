package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salon.app.model.SubCategoryMaster;

@Repository
public interface SubCategoryMasterRepo extends JpaRepository<SubCategoryMaster, Long> {
	
	@Query(value="select * from sub_category_master where is_delete=0",nativeQuery = true)
	List<SubCategoryMaster> getAllCategoryData();
	
	@Query(value="select * from sub_category_master where is_delete=0",nativeQuery = true)
	Page<SubCategoryMaster> getAllListCategory(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE sub_category_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from sub_category_master where is_delete=0 AND category_id=?1",nativeQuery = true)
	List<SubCategoryMaster> getAllCategoryDataAPI(String id);
}

