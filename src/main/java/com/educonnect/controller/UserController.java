package com.educonnect.controller;

import com.educonnect.dto.UserRequestDto;
import com.educonnect.entity.User;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.CustomUserService;
import com.educonnect.serviceImpl.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private FileService fileService;

    @PostMapping("/addUser")
    @Operation(summary = "Add a new user", description = "Creates a user")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userdto){
        String msg = customUserService.saveUser(userdto);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    //upload photo
    @PostMapping("/uploadPhoto/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> uploadProfilePhoto(@PathVariable long id,
                                                     @RequestParam("file") MultipartFile file,
                                                     Authentication authentication) throws IOException {

        User target = userRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));

        String callerusername = authentication.getName();
        if(!isAdmin && !callerusername.equals(target.getUsername())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not allowed to upload photo");
        }

        String storedpath = fileService.saveProfileImg(file,id);
        target.setProfilephotopath(storedpath);
        userRepository.save(target);

        return new ResponseEntity<>("Profile photo uploaded successfully",HttpStatus.OK);
    }

    //get profile photo of user
    @GetMapping("/getProfilePhoto/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable long id) throws IOException {

        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        String storedpath = user.getProfilephotopath();
        if(storedpath==null || storedpath.isBlank()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No profile photo found for this id");
        }
        byte[] imageBytes = fileService.readfilebyte(storedpath);
        String contentType = fileService.detectContentType(storedpath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDisposition(ContentDisposition.inline().filename("profile_" + user.getUsername()).build());
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes,headers,HttpStatus.OK);
    }
}
