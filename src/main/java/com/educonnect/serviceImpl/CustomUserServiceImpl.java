package com.educonnect.serviceImpl;

import com.educonnect.dto.UserRequestDto;
import com.educonnect.entity.User;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements CustomUserService , UserDetailsService {

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
}
