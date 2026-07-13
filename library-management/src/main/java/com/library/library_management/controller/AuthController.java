
package com.library.library_management.controller;
import com.library.library_management.Util.JwtUtil;
import com.library.library_management.dto.LoginRequest;
import com.library.library_management.entity.User;
import com.library.library_management.dto.AuthResponse;
import com.library.library_management.response.ApiResponse;
import com.library.library_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ApiResponse response = userService.loginUser(loginRequest);
        if (response.isSuccess()) {
            User user = (User) response.getData();
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getUserId());
            AuthResponse authResponse = new AuthResponse(token, user.getUserId(), user.getEmail(), user.getFullName(), user.getRole());
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(401).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        ApiResponse response = userService.registerUser(user);
        if (response.isSuccess()) {
            User registeredUser = (User) response.getData();
            String token = jwtUtil.generateToken(

                    registeredUser.getEmail(),
                    registeredUser.getRole(),
                    registeredUser.getUserId()
            );
            AuthResponse authResponse = new AuthResponse(
                    token,
                    registeredUser.getUserId(),
                    registeredUser.getEmail(),
                    registeredUser.getFullName(),
                    registeredUser.getRole()
            );
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.badRequest().body(response);
    }





}