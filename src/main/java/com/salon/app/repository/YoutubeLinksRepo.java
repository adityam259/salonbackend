package com.salon.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.salon.app.model.YoutubeLinks;

public interface YoutubeLinksRepo extends JpaRepository<YoutubeLinks, Long> {
	@Query(value="select * from youtube_link_master where is_delete=0",nativeQuery = true)
	Page<YoutubeLinks> getAllListLinks(Pageable pageable);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE youtube_link_master SET is_delete = '1' WHERE id = ?1",nativeQuery = true)
    void softDeleteById(Long id);
	
	@Query(value="select * from youtube_link_master where is_delete=0",nativeQuery = true)
	List<YoutubeLinks> getAllYoutubeData();
	
	@Query(value="select * from youtube_link_master where is_delete=0",nativeQuery = true)
	List<YoutubeLinks> getAllListLinksAPI();
}
