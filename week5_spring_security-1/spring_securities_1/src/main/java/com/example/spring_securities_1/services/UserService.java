package com.example.spring_securities_1.services;

import com.example.spring_securities_1.dto.UserDto;
import com.example.spring_securities_1.entities.User;
import com.example.spring_securities_1.exceptions.ResourceNotFoundException;
import com.example.spring_securities_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user with email: "+username+" is not found"));
    }

    public UserDto createNewUser(UserDto userDto) {

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());

        if(user.isPresent())
            throw new BadCredentialsException("user already exists with this email :"+userDto.getEmail());

        User toCreatedUser = modelMapper.map(userDto,User.class);
        toCreatedUser.setPassword(passwordEncoder.encode(toCreatedUser.getPassword()));

        User savedUser = userRepository.save(toCreatedUser);
        return modelMapper.map(savedUser , UserDto.class);
    }
}
