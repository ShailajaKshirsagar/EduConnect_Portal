package com.educonnect.service;

import com.educonnect.dto.CommentRequestDto;
import com.educonnect.dto.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogCommentService {
    CommentResponseDto createComment(long postid, CommentRequestDto requestDto, String username);

    Page<CommentResponseDto> getCommentsByPostId(long postId, Pageable pageable);

}
