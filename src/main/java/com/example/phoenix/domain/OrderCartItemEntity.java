package com.example.phoenix.domain;

import com.example.phoenix.model.response.BookResponse;
import com.example.phoenix.util.Constants;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = Constants.SCHEMA, name = Constants.TABLE_ORDER_PRODUCT)
public class OrderCartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID orderId;
    private UUID bookId;
    private Integer count;
    private Double price;

    @JdbcTypeCode(SqlTypes.JSON)
    private BookResponse book;

    @CreatedDate
    private Instant createdDate = Instant.now();
    private Boolean deleted = Boolean.FALSE;
    @LastModifiedDate
    private Instant lastModifiedDate = Instant.now();
}
