package com.samsam.info_final.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samsam.info_final.entity.*;



@Repository
public interface CommentRepository extends JpaRepository<INFOCOMMENT, Integer> {


	
	//페이징처리 테스트중
	Page<INFOCOMMENT> findAllByParentID(INFO info, Pageable pageable);
}
