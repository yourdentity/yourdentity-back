package com.yourdentity.server.test.repository;

import com.yourdentity.server.test.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
