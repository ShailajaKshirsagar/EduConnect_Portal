package com.educonnect.serviceImpl;

import com.educonnect.dto.UserRequestDto;
import com.educonnect.entity.User;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

@Service
public class CustomUserServiceImpl implements CustomUserService , UserDetailsService{

    @Autowired
    private UserRepo userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String saveUser(UserRequestDto userdto) {

        User user = User.builder()
                .username(userdto.getUsername())
                .password(encoder.encode(userdto.getPassword()))
                .active(true)
                .role(userdto.getRole())
                .build();

        userRepository.save(user);
        return "User saved with username : "+user.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user!=null){

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(String.valueOf(user.getRole()))
                    .build();
        }
        throw new RuntimeException("User with  "  + user.getUsername() + " username not found ");
    }


    @Override
    public String softDeleteUser(long id, String username) throws AccessDeniedException {

        User currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            throw new RuntimeException("Current user not found");
        }

        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new AccessDeniedException("Only admin can delete users");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        user.setDeletedat(Instant.now());
        userRepository.save(user);

        return "User Softly deleted";
    }
}
