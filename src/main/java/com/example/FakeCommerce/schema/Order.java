package com.example.FakeCommerce.schema;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Builder
@SQLDelete(sql = "UPDATE orders SET deleted_at=CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at is NULL")
public class Order extends BaseEntity {
  private OrderStatus status;
}
