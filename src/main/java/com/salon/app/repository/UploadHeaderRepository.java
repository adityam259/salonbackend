package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.salon.app.model.UploadHeader;

@Repository
public interface UploadHeaderRepository extends JpaRepository<UploadHeader, Long> {
	
	@Transactional
	@Modifying
    @Query(value="UPDATE header_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from header_master where is_delete=0",nativeQuery = true)
	Page<UploadHeader> getAllListHeader(Pageable pageable);
	
	@Query(value="select * from header_master where is_delete=0",nativeQuery = true)
	List<UploadHeader> getAllListHeaderData();
}
