package com.example.phoenix.repository;

import com.example.phoenix.domain.BookEntity;
import com.example.phoenix.domain.UserEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, UUID> {
  BookEntity findByIdAndDeletedIsFalse(final UUID id);

  List<BookEntity> findAllByDeletedFalse();
  List<BookEntity> findAllByIdInAndDeletedIsFalse(List<UUID> ids);

  Page<BookEntity> findAllByDeletedFalse(Pageable pageable);

  @Transactional
  @Modifying
  @Query("update BookEntity set  deleted = true where id = ?1")
  void deleteById(final UUID id);
}
