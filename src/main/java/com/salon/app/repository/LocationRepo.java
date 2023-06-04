package com.salon.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salon.app.model.Location;
@Repository
public interface LocationRepo extends JpaRepository<Location, Long>{
	@Query(value="select * from location_master where user_id = ?1",nativeQuery = true)
	List<Location> findByuserId(String userId);
}
