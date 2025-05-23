package com.example.phoenix.model.mapper;

import com.example.phoenix.domain.BookEntity;
import com.example.phoenix.domain.OrderCartItemEntity;
import com.example.phoenix.domain.OrderEntity;
import com.example.phoenix.model.UserPrincipal;
import com.example.phoenix.model.request.BookRequest;
import com.example.phoenix.model.request.CartItem;
import com.example.phoenix.model.request.OrderRequest;
import com.example.phoenix.model.request.OrderResponse;
import com.example.phoenix.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class OrderMapper {

  public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "userFirstName", source = "userPrincipal.user.firstName")
  @Mapping(target = "userLastName", source = "userPrincipal.user.lastName")
  @Mapping(target = "comment", source = "request.comment")
  public abstract OrderEntity toEntity(OrderRequest request, UserPrincipal userPrincipal);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderId", source = "orderId")
  @Mapping(target = "bookId", expression = "java(cartItem.book().id())")
  @Mapping(target = "count", source = "cartItem.count")
  @Mapping(target = "book", source = "cartItem.book")
  public abstract OrderCartItemEntity toCartItemEntity(CartItem cartItem, UUID orderId);

  public abstract CartItem toCartItem(OrderCartItemEntity cartItem);

  public List<OrderCartItemEntity> toCartItemEntities(List<CartItem> cartItems, UUID orderId) {
    return cartItems.stream().map(cartItem -> toCartItemEntity(cartItem, orderId)).toList();
  }

  public abstract OrderResponse toResponse(OrderEntity order, List<CartItem> cartItems);
  
  
}
