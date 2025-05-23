package com.example.phoenix.repository;

import com.example.phoenix.domain.UserEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
  UserEntity findByIdAndDeletedIsFalse(final UUID id);
  UserEntity findByPhoneAndDeletedFalse(final String phone);
}
