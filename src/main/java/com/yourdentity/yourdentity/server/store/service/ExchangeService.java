package com.yourdentity.yourdentity.server.store.service;

import com.yourdentity.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.yourdentity.global.exception.ErrorCode;
import com.yourdentity.yourdentity.server.store.dto.request.ExchangeItemRequest;
import com.yourdentity.yourdentity.server.store.dto.request.ExchangeRequest;
import com.yourdentity.yourdentity.server.store.dto.response.ExchangeResponse;
import com.yourdentity.yourdentity.server.store.entity.Exchange;
import com.yourdentity.yourdentity.server.store.entity.ExchangeStatus;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.entity.ProductOption;
import com.yourdentity.yourdentity.server.store.repository.ExchangeRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductOptionRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductStatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService
{
    private final ExchangeRepository exchangeRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductStatsRepository productStatsRepository;

    @Transactional
    public List<ExchangeResponse> createExchange(@RequestBody ExchangeRequest exchangeRequest)
    {
        Long userPrice=500L;
        Long totalPrice = exchangeRequest.items().stream()
                .mapToLong(r->{
                    Product product=productRepository.findByIdWithFetch(r.productId())
                            .orElseThrow(()->new BusinessBaseException(ErrorCode.PRODUCT_NOT_FOUND));
                    return product.getPrice() * r.quantity();
                })
                .sum();
        if (userPrice < totalPrice) {
            throw new BusinessBaseException(ErrorCode.INSUFFICIENT_POINT);
        }

        List<ExchangeResponse> responses = new ArrayList<>();

        List<ExchangeItemRequest> items= exchangeRequest.items();
        for(ExchangeItemRequest request:items){
            Long productId=request.productId();
            Long optionId= request.optionId();
            Long quantity = request.quantity();
            int updated= productOptionRepository.decreaseStock(optionId, quantity);
            if(updated==0)
            {
                throw new BusinessBaseException(ErrorCode.INSUFFICIENT_STOCK);
            }

            Product product = productRepository.findByIdWithFetch(productId)
                    .orElseThrow(() -> new BusinessBaseException(ErrorCode.PRODUCT_NOT_FOUND));
            ProductOption option = product.getOptions().stream()
                    .filter(o -> o.getId().equals(optionId))
                    .findFirst()
                    .orElseThrow(() -> new BusinessBaseException(ErrorCode.OPTION_NOT_FOUND));



            Exchange exchange = Exchange.builder()
                    .userId(1L)// 임시 유저 ID
                    .product(product)
                    .option(option)
                    .quantity(quantity)
                    .status(ExchangeStatus.REQUESTED)
                    .build();
            exchangeRepository.save(exchange);
            productStatsRepository.increaseApplyCount(productId);
            responses.add(ExchangeResponse.from(exchange));
        }

        return responses;
    }


}
