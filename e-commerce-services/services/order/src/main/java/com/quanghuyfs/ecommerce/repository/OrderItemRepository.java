package com.quanghuyfs.ecommerce.repository;

import com.quanghuyfs.ecommerce.model.OrderItem;
import com.quanghuyfs.ecommerce.response.OrderItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllByOrderId(Long orderId);
}
