package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salon.app.model.GiftVoucher;

@Repository
public interface GiftVoucherRepo extends JpaRepository<GiftVoucher, Long> {
	
	@Query(value="select * from gift_voucher_master where is_delete=0",nativeQuery = true)
	Page<GiftVoucher> getAllListGiftVoucher(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE gift_voucher_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from gift_voucher_master where is_delete=0",nativeQuery = true)
	List<GiftVoucher> getListGiftVoucher();

}
