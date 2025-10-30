package com.educonnect.controller;

import com.educonnect.dto.CommentRequestDto;
import com.educonnect.dto.CommentResponseDto;
import com.educonnect.service.BlogCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogcomment")
@Tag(name = "Blog comment API",description = "API's For Blog Post/comments")
public class BlogCommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    @PostMapping("/{postid}/addComment")
    @Operation(summary = "Add Comment",description = "Adds comment on blog post")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable long postid, @RequestBody CommentRequestDto requestDto, Authentication authentication){

        String username = authentication.getName();
        CommentResponseDto response = blogCommentService.createComment(postid,requestDto,username);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}/getCommentsByPostId")
    @Operation(summary = "Get Post",description = "Get blog post by id")
    public Page<CommentResponseDto> getComments(@PathVariable long postid,@RequestParam(defaultValue = "0") int page ,
                                                @RequestParam(defaultValue = "2") int size){

        return blogCommentService.getCommentsByPostId(postid, PageRequest.of(page,size));
    }
}
