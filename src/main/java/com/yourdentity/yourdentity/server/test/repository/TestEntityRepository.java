package com.yourdentity.yourdentity.server.test.repository;

import com.yourdentity.yourdentity.server.test.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
