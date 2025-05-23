package com.example.phoenix.api;

import com.example.phoenix.model.UserPrincipal;
import com.example.phoenix.model.request.OrderPaidRequest;
import com.example.phoenix.model.request.OrderRequest;
import com.example.phoenix.model.response.OrderResponse;
import com.example.phoenix.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public OrderResponse create(
      @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody OrderRequest request) {
    return orderService.createBook(userPrincipal, request);
  }

  @GetMapping("/{id}")
  public OrderResponse findById(@PathVariable("id") final UUID id) {
    return orderService.findById(id);
  }

  @GetMapping
  public Page<OrderResponse> findAll(Pageable pageable) {
    return orderService.findAll(pageable);
  }

  @GetMapping("/all")
  public List<OrderResponse> findAll() {
    return orderService.findAll();
  }

  @PutMapping("/paid")
  public OrderResponse update(@RequestBody OrderPaidRequest request) {
    return orderService.paidOrder(request);
  }



}
