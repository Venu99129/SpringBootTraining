package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.dto.SignUpDto;
import com.example.spring_security_assignment.dto.UserDto;
import com.example.spring_security_assignment.entities.User;
import com.example.spring_security_assignment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .orElseThrow(()->new BadCredentialsException("user not found with given email :"+username));
    }

    public UserDto signUpNewUser(SignUpDto signUpDto) {

        User toSavedUser = modelMapper.map(signUpDto , User.class);
        toSavedUser.setPassword(passwordEncoder.encode(toSavedUser.getPassword()));
        return modelMapper.map(userRepository.save(toSavedUser),UserDto.class);
    }
}
