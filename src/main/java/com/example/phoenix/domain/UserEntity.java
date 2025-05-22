package com.example.phoenix.domain;

import com.example.phoenix.util.Constants;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = Constants.SCHEMA, name = Constants.TABLE_USER)
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String firstName;
  private String lastName;
  private Instant birthday;
  private String phone;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String email;
  private String password;
  private String role;
  private Boolean isBlocked = Boolean.FALSE;

  @CreatedDate private Instant createdDate = Instant.now();
  private Boolean deleted = Boolean.FALSE;
  @LastModifiedDate private Instant lastModifiedDate = Instant.now();
  @Version private Long version = 0L;
}
