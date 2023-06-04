package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.salon.app.model.CategoryListData;
import com.salon.app.model.CouponMaster;

public interface CouponMasterRepo extends JpaRepository<CouponMaster, Long> {
	
	@Query(value="select * from coupon_master where is_delete=0",nativeQuery = true)
	Page<CouponMaster> getAllListCouponMaster(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE coupon_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from coupon_master where is_delete=0",nativeQuery = true)
	List<CouponMaster> getAllListCouponData();
	
	@Query(value = "SELECT COUNT(*) FROM coupon_master WHERE is_delete = 0", nativeQuery = true)
	int getCountOfCouponData();
}
