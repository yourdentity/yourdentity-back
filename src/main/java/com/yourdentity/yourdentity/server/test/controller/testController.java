package com.yourdentity.yourdentity.server.test.controller;

import com.yourdentity.yourdentity.server.test.entity.TestEntity;
import com.yourdentity.yourdentity.server.test.service.TestEntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class testController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TestEntityService testEntityService;
    @RequestMapping("/test")
    public String test(){
        log.info("로그테스트");
        log.error("로그테스트 err");
        return "test";
    }

    @PostMapping
    public TestEntity create(@RequestParam String name) {
        return testEntityService.save(name);
    }

    @GetMapping
    public List<TestEntity> getAll() {
        return testEntityService.findAll();
    }

}
