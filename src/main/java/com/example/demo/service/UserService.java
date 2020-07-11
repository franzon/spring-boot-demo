package com.example.demo.service;

import java.text.MessageFormat;
import java.util.Optional;

import com.example.demo.dto.SignUpRequestDto;
import com.example.demo.exception.EmailAlreadyUsedException;
import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
    }

    private boolean isEmailAlreadyUsed(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent();
    }

    public User signUp(SignUpRequestDto user) {
        if (isEmailAlreadyUsed(user.getEmail())) {
            throw new EmailAlreadyUsedException(user.getEmail());
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }
}
