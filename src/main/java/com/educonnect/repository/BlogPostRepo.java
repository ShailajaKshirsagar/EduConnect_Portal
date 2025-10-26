package com.educonnect.repository;

import com.educonnect.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost,Long> {
}
