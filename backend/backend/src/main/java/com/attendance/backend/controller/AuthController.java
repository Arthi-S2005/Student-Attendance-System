package com.attendance.backend.controller;

import com.attendance.backend.model.Student;
import com.attendance.backend.security.JwtUtil;
import com.attendance.backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Student student) {
        if (studentService.findByEmail(student.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        Student saved = studentService.registerStudent(student);
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            String token = jwtUtil.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @Data
    static class AuthRequest {
        private String email;
        private String password;
    }

    @Data
    static class AuthResponse {
        private final String token;
    }
}
