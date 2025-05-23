package com.example.phoenix.api;

import com.example.phoenix.model.request.LoginRequest;
import com.example.phoenix.model.request.UserRequest;

import com.example.phoenix.model.response.JwtResponse;
import com.example.phoenix.model.response.UserResponse;
import com.example.phoenix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/admin")
  public UserResponse createAdmin(@RequestBody UserRequest request) {
    return userService.createAdmin(request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/user")
  public UserResponse createUser(@RequestBody UserRequest request) {
    return userService.createUser(request);
  }

  @PostMapping("/login")
  public JwtResponse login(@RequestBody LoginRequest request) {
    return userService.login(request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/block/{userId}")
  public void blockUser(@PathVariable UUID userId) {
    userService.blockUser(userId);
  }
}
