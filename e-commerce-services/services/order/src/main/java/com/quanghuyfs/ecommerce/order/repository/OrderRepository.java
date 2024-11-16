package com.quanghuyfs.ecommerce.order.repository;

import com.quanghuyfs.ecommerce.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long> {

}
