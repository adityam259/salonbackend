package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.salon.app.model.Blog;
import com.salon.app.model.Login;

public interface BlogRepo extends JpaRepository<Blog, Long> {
	@Query(value="select * from blog_master where is_delete=0",nativeQuery = true)
	Page<Blog> getAllListBlog(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE blog_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from blog_master where is_delete=0 LIMIT 5",nativeQuery = true)
	List<Blog> getAllBlogList();
}
