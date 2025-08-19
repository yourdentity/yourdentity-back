package com.yourdentity.yourdentity.server.store.repository;

import com.yourdentity.yourdentity.server.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
