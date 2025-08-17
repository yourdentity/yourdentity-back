package com.yourdentity.yourdentity.server.store.entity;


import com.yourdentity.yourdentity.global.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "product_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//TODO: 유저 엔티티 연결 필요
public class ProductReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OrderBy("id ASC")
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductReviewImage> images = new HashSet<>();

    @OrderBy("id ASC")
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductReviewFile> files = new HashSet<>();

}
