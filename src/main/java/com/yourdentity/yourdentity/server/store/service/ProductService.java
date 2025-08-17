package com.yourdentity.yourdentity.server.store.service;

import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.entity.ProductOption;
import com.yourdentity.yourdentity.server.store.entity.ProductQuestion;
import com.yourdentity.yourdentity.server.store.entity.ProductReview;
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
        List<Product> products = productRepository.findAllWithFetch();
        return productListMapper.toListResponses(products);
    }

    public ProductResponse findProductById(long productId)
    {
        Product product = productRepository.findByIdWithFetch(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        List<ProductQuestion> questions = productRepository.findQuestionsByProductId(productId);
        List<ProductReview> reviews = productRepository.findReviewsByProductId(productId);
        product.setQuestions(questions);
        product.setReviews(reviews);


        return productDetailMapper.toResponse(product);
    }
}
