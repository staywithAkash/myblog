package com.blog9.controller;

import com.blog9.entity.User;
import com.blog9.payload.UserDto;
import com.blog9.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/api/blog")
public class SignUpController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserDto userDto
    ){
        if(userRepo.existsByEmail(userDto.getEmail())){
            return new  ResponseEntity<>("user with this email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepo.existsByUsername(userDto.getUsername())){
            return new ResponseEntity<>("username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user1 = mapToUser(userDto);
        user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user1);

        return new ResponseEntity<>("Registration Successful",HttpStatus.CREATED);
    }
    public User mapToUser(UserDto userDto){
        User mappedUser=modelMapper.map(userDto,User.class);
        return mappedUser;
    }
}
