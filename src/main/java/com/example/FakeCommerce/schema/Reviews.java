package com.example.FakeCommerce.schema;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
@SQLDelete(sql = "UPDATE reviews SET deleted_at=CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at is NULL")
public class Reviews extends BaseEntity {
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  private BigDecimal rating;
}
