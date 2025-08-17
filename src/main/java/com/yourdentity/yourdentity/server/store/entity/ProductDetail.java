package com.yourdentity.yourdentity.server.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
