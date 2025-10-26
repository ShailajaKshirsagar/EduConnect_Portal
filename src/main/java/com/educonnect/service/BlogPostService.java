package com.educonnect.service;

import com.educonnect.dto.PostRequestDto;
import com.educonnect.dto.PostResponseDto;

public interface BlogPostService {
    PostResponseDto createPost(PostRequestDto dto, String username);
}
