package com.yourdentity.yourdentity.server.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStats
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long viewCount;
    private Long applyCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
