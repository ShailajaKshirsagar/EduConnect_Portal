package com.educonnect.repository;

import com.educonnect.entity.BlogComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCommentRepo extends JpaRepository<BlogComment,Long> {

    @Query("SELECT c FROM BlogComment c WHERE c.post.post_id =:post_id")
    Page<BlogComment> findByPostId(@Param("post_id") long post_id, Pageable pageable);
}
