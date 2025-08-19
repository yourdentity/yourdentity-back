package com.yourdentity.yourdentity.server.store.controller;


import com.yourdentity.yourdentity.server.store.dto.request.OrderCheckRequest;
import com.yourdentity.yourdentity.server.store.dto.request.OrderRequest;
import com.yourdentity.yourdentity.server.store.dto.response.OrderResponse;
import com.yourdentity.yourdentity.server.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/store/order")
@RequiredArgsConstructor
//TODO: @AuthenticationPrincipal 유저 엔티티 연동 필요
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/check")
    public ResponseEntity<Void> checkExchange(@RequestBody OrderCheckRequest request) {
        orderService.checkOrder(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createExchange(@RequestBody OrderRequest request) {
        OrderResponse exchangeResponse=orderService.createOrder(request);
        return ResponseEntity.ok(exchangeResponse);

    }
}
