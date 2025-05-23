package com.example.phoenix.model.mapper;

import com.example.phoenix.domain.UserEntity;
import com.example.phoenix.model.request.UserRequest;
import com.example.phoenix.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  public abstract UserResponse toResponse(final UserEntity user);
  public abstract UserEntity toEntity(final UserRequest user);
}
