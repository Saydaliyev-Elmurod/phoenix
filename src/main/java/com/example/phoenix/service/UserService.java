package com.example.phoenix.service;

import com.example.phoenix.context.exception.AlreadyExistException;
import com.example.phoenix.context.exception.NotFoundException;
import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.domain.Role;
import com.example.phoenix.domain.UserEntity;

import com.example.phoenix.model.mapper.UserMapper;
import com.example.phoenix.model.request.LoginRequest;
import com.example.phoenix.model.request.UserRequest;
import com.example.phoenix.model.response.JwtResponse;
import com.example.phoenix.model.response.UserResponse;
import com.example.phoenix.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {
  private final UserRepository userRepository;
  private final JwtService jwtService;

  public UserResponse createAdmin(UserRequest request) {
    log.debug("Create admin user with request: {}", request);
    UserEntity user = userRepository.findByPhoneAndDeletedFalse(request.phone());
    if (user != null) {
      if (user.getRole().equals(Role.ADMIN)) {
        throw new AlreadyExistException(ErrorCode.USER_ALREADY_EXISTS, "User already exists");
      }
      user.setRole(Role.ADMIN);
      return UserMapper.INSTANCE.toResponse(userRepository.save(user));
    } else {
      user = UserMapper.INSTANCE.toEntity(request);
      user.setRole(Role.ADMIN);
      user.setPassword(
          BCrypt.hashpw(
              request.password(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()));

      return UserMapper.INSTANCE.toResponse(userRepository.save(user));
    }
  }

  public UserResponse createUser(UserRequest request) {
    log.debug("Create  user with request: {}", request);
    UserEntity user = userRepository.findByPhoneAndDeletedFalse(request.phone());
    if (user != null) {
      throw new AlreadyExistException(ErrorCode.USER_ALREADY_EXISTS, "User already exists");
    }
    user = UserMapper.INSTANCE.toEntity(request);
    user.setRole(Role.USER);
    user.setPassword(
        BCrypt.hashpw(
            request.password(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()));

    return UserMapper.INSTANCE.toResponse(userRepository.save(user));
  }

  public JwtResponse login(final LoginRequest request) {
    log.debug("Login user with request: {}", request);
    userRepository.findAll().forEach(user -> System.out.println(user.getId().toString()));
    UserEntity user = userRepository.findByPhoneAndDeletedFalse(request.phone());
    if (user == null) {
      throw new NotFoundException(ErrorCode.USER_NOT_FOUND, "User not found");
    }
    if (!BCrypt.checkpw(request.password(), user.getPassword())) {
      throw new NotFoundException(ErrorCode.PASSWORD_NOT_MATCH_ERROR, "Password not match");
    }
    if (Boolean.TRUE.equals(user.getIsBlocked())) {
      throw new NotFoundException(ErrorCode.USER_BLOCKED_ERROR_CODE, "User is blocked");
    }
    return new JwtResponse(
        jwtService.generateToken(user.getId(), "user",  86400 * 1000L));
  }

  public void blockUser(final UUID userId) {
    log.debug("Blocking user with id: {}", userId);
    UserEntity user = userRepository.findByIdAndDeletedIsFalse(userId);
    if (user == null) {
      throw new NotFoundException(ErrorCode.USER_NOT_FOUND, "User not found");
    }
    user.setIsBlocked(Boolean.TRUE);
    userRepository.save(user);
  }
}
