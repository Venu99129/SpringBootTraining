package com.example.auth_service.services;

import com.example.auth_service.dto.SignUpDto;
import com.example.auth_service.dto.UserDto;
import com.example.auth_service.entities.User;
import com.example.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto signupUser(SignUpDto signUpDto){
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent())
            throw new BadCredentialsException("user already exists with this email :"+signUpDto.getEmail());

        User toCreatedUser = modelMapper.map(signUpDto,User.class);
        toCreatedUser.setPassword(passwordEncoder.encode(toCreatedUser.getPassword()));

        log.info("saving user have this details : {}",toCreatedUser);
        User savedUser = userRepository.save(toCreatedUser);
        return modelMapper.map(savedUser , UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("user with email: "+username+" is not found"));
    }

    public User loadByUserId(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user with id: "+userId+" is not found"));
    }
}
