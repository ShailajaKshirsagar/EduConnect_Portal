package com.educonnect.controller;

import com.educonnect.dto.CommentRequestDto;
import com.educonnect.dto.CommentResponseDto;
import com.educonnect.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogcomment")
public class BlogCommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    @PostMapping("/{postid}/addComment")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable long postid, @RequestBody CommentRequestDto requestDto, Authentication authentication){

        String username = authentication.getName();
        CommentResponseDto response = blogCommentService.createComment(postid,requestDto,username);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
