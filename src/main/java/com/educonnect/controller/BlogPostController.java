package com.educonnect.controller;

import com.educonnect.dto.PostRequestDto;
import com.educonnect.dto.PostResponseDto;
import com.educonnect.service.BlogPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogpost")
@Tag(name = "BlogPost API",description = "API's For Blog Post/comments")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/addPost")
    @Operation(summary = "Add post",description = "Creates a new Blog post")
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetails userDetails){

        if(userDetails==null){
            return ResponseEntity.status(401).build();
        }
       PostResponseDto response = blogPostService.createPost(dto,userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //search blog by id
    @GetMapping("/getPostById/{id}")
    @Operation(summary = "Get post by id",description = "Returns Blog Post by id")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("id") long id,
                                                       @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails==null){
            return ResponseEntity.status(401).build();
        }
       PostResponseDto responseDto  = blogPostService.getPostById(id,userDetails.getUsername());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
