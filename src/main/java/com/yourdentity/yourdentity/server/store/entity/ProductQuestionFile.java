package com.yourdentity.yourdentity.server.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_question_file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuestionFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private ProductQuestion question;
}
