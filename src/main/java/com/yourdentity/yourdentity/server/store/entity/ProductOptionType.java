package com.yourdentity.yourdentity.server.store.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "product_option_type")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

