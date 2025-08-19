package com.yourdentity.yourdentity.server.store.entity;


import com.yourdentity.yourdentity.global.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//TODO: 유저 엔티티 연결 필요
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String orderNumber;

    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Long totalPrice;
    private Long totalDiscount;
    private Long finalPrice;
    private Long totalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(Product product, ProductOption option, Long quantity) {
        OrderItem item = OrderItem.of(this, product, option, quantity);
        this.items.add(item);
    }
    public static Order of(Long userId,
                           String receiverName,
                           String receiverPhone,
                           String receiverAddress,
                           OrderStatus status,
                           String orderCode) {
        return Order.builder()
                .userId(userId)
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .status(status)
                .orderNumber(orderCode)
                .items(new ArrayList<>())
                .build();
    }
}
