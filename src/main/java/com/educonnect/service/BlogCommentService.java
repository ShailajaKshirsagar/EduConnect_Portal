package com.educonnect.service;

import com.educonnect.dto.CommentRequestDto;
import com.educonnect.dto.CommentResponseDto;

public interface BlogCommentService {
    CommentResponseDto createComment(long postid, CommentRequestDto requestDto, String username);
}
