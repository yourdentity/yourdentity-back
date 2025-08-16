package com.yourdentity.yourdentity.server.store.service;

import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.mapper.ProductDetailMapper;
import com.yourdentity.yourdentity.server.store.mapper.ProductListMapper;
import com.yourdentity.yourdentity.server.store.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;

    private final ProductListMapper productListMapper;
    private final ProductDetailMapper productDetailMapper;

    public List<ProductListResponse> findAllProducts()
    {
        List<Product> products = productRepository.findAllWithOptionsAndDescriptionsAndTags()
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productListMapper.toListResponses(products);
    }

    public ProductResponse findProductById(long productId)
    {
        Product product= productRepository.findByIdWithOptionsAndDescriptionsAndTags(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDetailMapper.toResponse(product);
    }
}
