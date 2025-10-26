package com.educonnect.controller;

import com.educonnect.dto.PostRequestDto;
import com.educonnect.dto.PostResponseDto;
import com.educonnect.service.BlogPostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/addPost")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetails userDetails){

        if(userDetails==null){
            return ResponseEntity.status(401).build();
        }
       PostResponseDto response = blogPostService.createPost(dto,userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
