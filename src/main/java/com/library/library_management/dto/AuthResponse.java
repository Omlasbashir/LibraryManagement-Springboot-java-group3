package com.library.library_management.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private int userId;
    private String email;
    private String fullName;
    private String role; // Admin ama Librarian

//    public AuthResponse(Integer userId, String email, String fullName, String role) {
//    }
}
