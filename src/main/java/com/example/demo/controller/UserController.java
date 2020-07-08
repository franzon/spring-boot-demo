package com.example.demo.controller;

import com.example.demo.dto.SignUpRequestDto;
import com.example.demo.dto.SignUpResponseDto;
import com.example.demo.exception.ApiError;
import com.example.demo.exception.EmailAlreadyUsedException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto user) {
        User newUser = userService.signUp(user);

        SignUpResponseDto response = new SignUpResponseDto(newUser.getId(), newUser.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> authenticatedRouteTest() {
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
