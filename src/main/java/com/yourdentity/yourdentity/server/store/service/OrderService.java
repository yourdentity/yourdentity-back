package com.yourdentity.yourdentity.server.store.service;


import com.yourdentity.yourdentity.global.exception.BusinessBaseException;
import com.yourdentity.yourdentity.global.exception.ErrorCode;
import com.yourdentity.yourdentity.server.store.dto.request.OrderCheckRequest;
import com.yourdentity.yourdentity.server.store.dto.request.OrderItemRequest;
import com.yourdentity.yourdentity.server.store.dto.request.OrderRequest;
import com.yourdentity.yourdentity.server.store.dto.response.OrderResponse;
import com.yourdentity.yourdentity.server.store.entity.Order;
import com.yourdentity.yourdentity.server.store.entity.OrderStatus;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.entity.ProductOption;
import com.yourdentity.yourdentity.server.store.mapper.OrderResponseMapper;
import com.yourdentity.yourdentity.server.store.repository.OrderRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductOptionRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductRepository;
import com.yourdentity.yourdentity.server.store.repository.ProductStatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductStatsRepository productStatsRepository;

    private final OrderResponseMapper orderResponseMapper;

    @Transactional
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {

        String orderCode = UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
        Order order = Order.of(
                1L, //임시 사용자
                orderRequest.userName(),
                orderRequest.phoneNumber(),
                orderRequest.address(),
                OrderStatus.REQUESTED,
                orderCode
        );

        long totalQuantity = 0L;
        long totalPrice = 0L;
        long totalDiscount = 0L;

        List<OrderItemRequest> items = orderRequest.items();
        for(OrderItemRequest item:items)
        {
            Long productId = item.productId();
            Long optionId = item.optionId();
            Long quantity = item.quantity();
            int updated = productOptionRepository.decreaseStock(optionId, quantity);
            if (updated == 0) {
                throw new BusinessBaseException(ErrorCode.INSUFFICIENT_STOCK);
            }
            Product product = productRepository.findByIdWithFetch(productId)
                    .orElseThrow(() -> new BusinessBaseException(ErrorCode.PRODUCT_NOT_FOUND));
            ProductOption option = product.getOptions().stream()
                    .filter(o -> o.getId().equals(optionId))
                    .findFirst()
                    .orElseThrow(() -> new BusinessBaseException(ErrorCode.OPTION_NOT_FOUND));
            order.addItem(product, option, quantity);
            productStatsRepository.increaseApplyCount(productId);
            totalQuantity += quantity;
            totalPrice += product.getPrice() * quantity;
        }

        order.setTotalQuantity(totalQuantity);
        order.setTotalPrice(totalPrice);
        order.setTotalDiscount(totalDiscount);
        order.setFinalPrice(totalPrice-totalDiscount);
        orderRepository.save(order);


        return orderResponseMapper.toResponse(order);

    }
    public void checkOrder(OrderCheckRequest orderCheckRequest)
    {
        Long userPrice=510L;// 임시 사용자 포인트

        Long totalPrice = orderCheckRequest.items().stream()
                .mapToLong(r -> {
                    Product product = productRepository.findByIdWithFetch(r.productId())
                            .orElseThrow(() -> new BusinessBaseException(ErrorCode.PRODUCT_NOT_FOUND));

                    ProductOption option = product.getOptions().stream()
                            .filter(o -> o.getId().equals(r.optionId()))
                            .findFirst()
                            .orElseThrow(() -> new BusinessBaseException(ErrorCode.OPTION_NOT_FOUND));

                    boolean enough = productOptionRepository.hasEnoughStock(r.optionId(), r.quantity());
                    if (!enough) {
                        throw new BusinessBaseException(ErrorCode.INSUFFICIENT_STOCK);
                    }
                    return product.getPrice() * r.quantity();
                })
                .sum();
        if (userPrice < totalPrice) {
            throw new BusinessBaseException(ErrorCode.INSUFFICIENT_POINT);
        }
    }


}



