package com.yourdentity.server.test.service;

import com.yourdentity.server.test.entity.TestEntity;
import com.yourdentity.server.test.repository.TestEntityRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestEntityService {

    private final TestEntityRepository repository;

    public TestEntityService(TestEntityRepository repository) {
        this.repository = repository;
    }

    public TestEntity save(String name) {
        return repository.save(new TestEntity(name));
    }

    public List<TestEntity> findAll() {
        return repository.findAll();
    }
}
