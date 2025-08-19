package com.yourdentity.yourdentity.server.store.mapper;


import com.yourdentity.yourdentity.server.store.dto.OrderDto;
import com.yourdentity.yourdentity.server.store.dto.ProductOptionSummaryDto;
import com.yourdentity.yourdentity.server.store.dto.response.OrderResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.Order;
import com.yourdentity.yourdentity.server.store.entity.OrderItem;
import com.yourdentity.yourdentity.server.store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderResponseMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "totalAmount", source = "totalQuantity")
    @Mapping(target = "totalDiscount", source = "totalDiscount")
    @Mapping(target = "finalPrice", source = "finalPrice")
    @Mapping(target = "orders", expression = "java(toOrderDtos(order.getItems()))")
    @Mapping(target = "orderStatus", source = "status")
    OrderResponse toResponse(Order order);

    default List<OrderDto> toOrderDtos(List<OrderItem> items) {
        return items.stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }
    default OrderDto toOrderDto(OrderItem item) {
        return new OrderDto(
                item.getProduct().getCompanyName(),
                item.getProduct().getId(),
                item.getProduct().getProductName(),
                new ProductOptionSummaryDto(
                        item.getOption().getId(),
                        item.getOption().getOptionType().getName(),
                        item.getOption().getValue(),
                        item.getOption().getImageUrl()
                ),
                item.getProduct().getPrice(),
                item.getQuantity()
        );
    }
}
