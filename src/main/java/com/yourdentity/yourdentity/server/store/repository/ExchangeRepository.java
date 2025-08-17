package com.yourdentity.yourdentity.server.store.repository;

import com.yourdentity.yourdentity.server.store.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
