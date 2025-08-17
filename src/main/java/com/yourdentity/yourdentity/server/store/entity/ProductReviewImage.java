package com.yourdentity.yourdentity.server.store.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_review_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private ProductReview review;
}
