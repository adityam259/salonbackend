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
import com.salon.app.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	@Transactional
	@Query(value = "select * from login where user_name = ?1 AND password = ?2", nativeQuery = true)
	Login searchLoginUser(String username, String password);
	
	@Transactional
	@Query(value = "select * from login where phone_no = ?1 AND type = 'User'", nativeQuery = true)
	Login validateLoginUser(String phoneNo);
	
	Login findByphoneNo(String phoneNo);
	
	Login getByuserName(String userName);
	
	Login getBytype(String type);
	
	@Transactional
	@Query(value = "select * from login where type= 'Employee'", nativeQuery = true)
	List<Login> searchtype();
	
	@Transactional
	@Query(value = "select COUNT(*) from login where type= 'User'", nativeQuery = true)
	int selectCountUsers();
	
	@Transactional
	@Query(value = "select COUNT(*) from login where type= 'Employee'", nativeQuery = true)
	int selectCountEmployee();
	
	@Query(value="select * from login where is_delete=0 AND type='Employee'",nativeQuery = true)
	Page<Login> getAllListLogin(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE login SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
}
