package com.salon.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.salon.app.model.CategoryListData;
import com.salon.app.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	
	@Transactional
	@Query(value = "select * from order_master where user_id = ?1", nativeQuery = true)
	List<OrderDetails> orderList(String id);
	
	@Transactional
	@Query(value = "select COUNT(*) from order_master where status = 'completed'", nativeQuery = true)
	int orderCompleted();
	
	@Transactional
	@Query(value = "select COUNT(*) from order_master where status = 'pending'", nativeQuery = true)
	int orderPending();
	
	@Query(value="select * from order_master",nativeQuery = true)
	Page<OrderDetails> getAllListOrders(Pageable pageable);
	
	@Query(value="select * from order_master where order_tracking_id = ?1",nativeQuery = true)
	Page<OrderDetails> findByOrderNumberContaining(String orderId,Pageable pageable);
	
	@Transactional
	@Query(value = "select * from order_master where order_tracking_id = ?1", nativeQuery = true)
	OrderDetails orderDataOrderTracking(String orderId);
	
	@Transactional
	@Modifying
	@Query(value = "update order_master set status = ?2 where order_tracking_id = ?1", nativeQuery = true)
	void updateStatusByOrderTrackingId(String orderTrackingId, String newStatus);
	
	@Transactional
	@Query(value = "select * from order_master where user_id = ?1 AND status='pending'", nativeQuery = true)
	List<OrderDetails> orderListPendingData(String id);
	
	@Transactional
	@Query(value = "select * from order_master where user_id = ?1 AND status='completed'", nativeQuery = true)
	List<OrderDetails> orderListCompletedData(String id);
	
	@Transactional
	@Query(value = "select * from order_master where user_id = ?1 AND status='pending'", nativeQuery = true)
	List<OrderDetails> orderListPending(String id);
	
	@Transactional
	@Query(value = "select * from order_master where user_id = ?1 AND status='completed'", nativeQuery = true)
	List<OrderDetails> orderListCompleted(String id);

}
