package com.example.phoenix.model.mapper;

import com.example.phoenix.domain.BookEntity;
import com.example.phoenix.domain.UserEntity;
import com.example.phoenix.model.request.BookRequest;
import com.example.phoenix.model.request.CartItem;
import com.example.phoenix.model.request.UserRequest;
import com.example.phoenix.model.response.BookResponse;
import com.example.phoenix.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class BookMapper {

  public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

  public abstract BookEntity toEntity(final BookRequest request);

  public abstract BookEntity toUpdate(
      final @MappingTarget BookEntity entity, final BookRequest request);

  public abstract BookResponse toResponse(final BookEntity request);
}
