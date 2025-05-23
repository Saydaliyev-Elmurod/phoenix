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
@Table(schema = Constants.SCHEMA, name = Constants.TABLE_BOOK)
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String image;
  private String name;
  private Double price;
  private String description;

  @CreatedDate private Instant createdDate = Instant.now();
  private Boolean deleted = Boolean.FALSE;
  @LastModifiedDate private Instant lastModifiedDate = Instant.now();
}
