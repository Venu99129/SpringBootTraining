package com.example.spring_securities_1.services;

import com.example.spring_securities_1.dto.SignupDto;
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
                .orElseThrow(()-> new BadCredentialsException("user with email: "+username+" is not found"));
    }

    public User loadByUserId(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with id: "+userId+" is not found"));
    }

    public UserDto signupUser(SignupDto signupDto) {

        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        if(user.isPresent())
            throw new BadCredentialsException("user already exists with this email :"+signupDto.getEmail());

        User toCreatedUser = modelMapper.map(signupDto,User.class);
        toCreatedUser.setPassword(passwordEncoder.encode(toCreatedUser.getPassword()));

        log.info("saving user have this details : {}",toCreatedUser);
        User savedUser = userRepository.save(toCreatedUser);
        return modelMapper.map(savedUser , UserDto.class);
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User loadUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with given userId :"+userId));
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
