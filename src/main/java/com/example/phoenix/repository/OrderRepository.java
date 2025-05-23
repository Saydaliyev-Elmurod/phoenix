package com.example.phoenix.repository;

import com.example.phoenix.domain.BookEntity;
import java.util.List;
import java.util.UUID;

import com.example.phoenix.domain.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {

  List<OrderEntity> findAllByDeletedIsFalse();
  OrderEntity findByIdAndDeletedIsFalse(final UUID id);

  Page<OrderEntity> findAllByDeletedIsFalse(Pageable pageable);
}
