package com.example.phoenix.service;

import com.example.phoenix.context.exception.NotFoundException;
import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.domain.BookEntity;
import com.example.phoenix.domain.OrderEntity;
import com.example.phoenix.model.UserPrincipal;
import com.example.phoenix.model.mapper.BookMapper;
import com.example.phoenix.model.mapper.OrderMapper;
import com.example.phoenix.model.request.CartItem;
import com.example.phoenix.model.request.OrderBookRequest;
import com.example.phoenix.model.request.OrderRequest;
import com.example.phoenix.model.request.OrderResponse;
import com.example.phoenix.model.response.BookResponse;
import com.example.phoenix.repository.BookRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.example.phoenix.repository.OrderCartItemRepository;
import com.example.phoenix.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class OrderService {
  private final BookRepository bookRepository;
  private final OrderRepository orderRepository;
  private final OrderCartItemRepository orderCartItemRepository;

  public OrderResponse createBook(UserPrincipal userPrincipal, OrderRequest request) {
    log.debug("Create order with request: {}", request);

    List<BookResponse> books =
        bookRepository
            .findAllByIdInAndDeletedIsFalse(
                request.books().stream().map(OrderBookRequest::bookId).toList())
            .stream()
            .map(BookMapper.INSTANCE::toResponse)
            .toList();
    if (books.size() != request.books().size()) {
      throw new NotFoundException(ErrorCode.BOOK_NOT_FOUND, "One or more books not found");
    }
    Map<UUID, BookResponse> bookMap =
        books.stream().collect(Collectors.toMap(BookResponse::id, book -> book));

    AtomicReference<Double> calculatedTotal = new AtomicReference<>(0.0);
    List<CartItem> cartItems =
        request.books().stream()
            .map(
                orderBookRequest -> {
                  BookResponse book = bookMap.get(orderBookRequest.bookId());
                  if (book == null) {
                    throw new NotFoundException(ErrorCode.BOOK_NOT_FOUND, "Book not found");
                  }
                  double price = book.price() * orderBookRequest.count();
                  calculatedTotal.updateAndGet(v -> v + price);
                  return new CartItem(null, book, orderBookRequest.count());
                })
            .toList();

    if (!calculatedTotalEquals(request.totalPrice(), calculatedTotal.get())) {
      throw new IllegalArgumentException("Total price does not match");
    }
    OrderEntity order = OrderMapper.INSTANCE.toEntity(request, userPrincipal);
    orderRepository.save(order);
    orderCartItemRepository.saveAll(
        OrderMapper.INSTANCE.toCartItemEntities(cartItems, order.getId()));
    return OrderMapper.INSTANCE.toResponse(order, cartItems);
  }

  public List<OrderResponse> findAll() {
    log.debug("Find all orders");
    List<OrderEntity> orders = orderRepository.findAllByDeletedIsFalse();
    return getOrderResponse(orders);
  }

  public Page<OrderResponse> findAll(Pageable pageable) {
    log.debug("Find all orders with pagination: {}", pageable);
    Page<OrderEntity> orders = orderRepository.findAllByDeletedIsFalse(pageable);

    return new PageImpl<>(
        getOrderResponse(orders.getContent()), pageable, orders.getTotalElements());
  }

  public OrderResponse findById(UUID id) {
    log.debug("Find order by id: {}", id);
    OrderEntity order = orderRepository.findByIdAndDeletedIsFalse(id);
    if (order == null) {
      throw new NotFoundException(ErrorCode.ORDER_NOT_FOUND, "Order not found");
    }
    return OrderMapper.INSTANCE.toResponse(
        order,
        orderCartItemRepository.findByOrderIdAndDeletedFalse(order.getId()).stream()
            .map(OrderMapper.INSTANCE::toCartItem)
            .toList());
  }

  private boolean calculatedTotalEquals(Double requested, double calculated) {
    return Math.abs(requested - calculated) < 0.01;
  }

  private List<OrderResponse> getOrderResponse(List<OrderEntity> orders) {
    List<UUID> orderIds = orders.stream().map(OrderEntity::getId).toList();
    Map<UUID, List<CartItem>> orderCartItems =
        orderCartItemRepository.findByOrderIdInAndDeletedFalse(orderIds).stream()
            .map(OrderMapper.INSTANCE::toCartItem)
            .collect(Collectors.groupingBy(CartItem::orderId));

    return orders.stream()
        .map(order -> OrderMapper.INSTANCE.toResponse(order, orderCartItems.get(order.getId())))
        .toList();
  }
}
