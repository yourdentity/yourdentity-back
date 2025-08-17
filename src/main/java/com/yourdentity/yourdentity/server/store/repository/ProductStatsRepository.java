package com.yourdentity.yourdentity.server.store.repository;

import com.yourdentity.yourdentity.server.store.entity.ProductStats;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatsRepository extends JpaRepository<ProductStats, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE ProductStats p SET p.viewCount = p.viewCount + 1 WHERE p.product.id = :productId")
    void increaseViewCount(@Param("productId") Long productId);
}
