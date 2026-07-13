package com.library.library_management.service;

import com.library.library_management.dto.LoginRequest;
import com.library.library_management.entity.User;
import com.library.library_management.repository.UserRepository;
import com.library.library_management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiResponse loginUser(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPasswordHash().equals(loginRequest.getPassword())) {
                return new ApiResponse(true, "Login-ku waa guulaystay!", user);
            }
        }
        return new ApiResponse(false, "Iimeelka ama Password-ka ayaa khaldan!", null);
    }

    public ApiResponse registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ApiResponse(false, "Email-kan horay ayaa loo isticmaalay!", null);
        }
        User savedUser = userRepository.save(user);
        return new ApiResponse(true, "Diiwaan-gelinta waa guulaysatay!", savedUser);
    }


   public List<User> getAllUsers() {
    return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User-ka lama helin!"));
    }

    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}