package com.yourdentity.yourdentity.server.store.controller;


import com.yourdentity.yourdentity.server.store.dto.request.ExchangeItemRequest;
import com.yourdentity.yourdentity.server.store.dto.request.ExchangeRequest;
import com.yourdentity.yourdentity.server.store.dto.response.ExchangeResponse;
import com.yourdentity.yourdentity.server.store.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store/exchange")
@RequiredArgsConstructor
//TODO: @AuthenticationPrincipal 유저 엔티티 연동 필요
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<List<ExchangeResponse>> createExchange(@RequestBody ExchangeRequest request) {

        List<ExchangeResponse> exchangeResponse=exchangeService.createExchange(request);

        return ResponseEntity.ok(exchangeResponse);

    }
}
