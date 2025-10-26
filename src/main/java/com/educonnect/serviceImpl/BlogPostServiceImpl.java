package com.educonnect.serviceImpl;

import com.educonnect.dto.PostRequestDto;
import com.educonnect.dto.PostResponseDto;
import com.educonnect.entity.BlogPost;
import com.educonnect.entity.User;
import com.educonnect.repository.BlogPostRepo;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepo postRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto dto, String username) {

        User author = userRepository.findByUsername(username);
        if(author==null){
            throw new RuntimeException("Author not found");
        }
        BlogPost post = BlogPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .build();

        BlogPost savedPost = postRepository.save(post);

        PostResponseDto response = PostResponseDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .authorname(author.getUsername())
                .createdAt(savedPost.getCreatedAt())
                .updatedAt(savedPost.getUpdatedAt())
                .authorid(author.getId())
                .build();
        return response;
    }
}
