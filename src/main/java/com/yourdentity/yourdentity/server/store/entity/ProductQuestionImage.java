package com.yourdentity.yourdentity.server.store.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_question_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuestionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private ProductQuestion question;
}
