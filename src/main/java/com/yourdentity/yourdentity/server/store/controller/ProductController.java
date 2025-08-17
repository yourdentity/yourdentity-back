package com.yourdentity.yourdentity.server.store.controller;


import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductListResponse> getProducts()
    {
        return productService.findAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ProductResponse getProduct(
            @PathVariable Long productId
    )
    {
        return productService.findProductById(productId);
    }
}
