package com.yourdentity.yourdentity.server.store.repository;

import com.yourdentity.yourdentity.server.store.entity.Product;
import com.yourdentity.yourdentity.server.store.entity.ProductOption;
import com.yourdentity.yourdentity.server.store.entity.ProductQuestion;
import com.yourdentity.yourdentity.server.store.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("select distinct p from Product p " +
            "left join fetch p.options o "+
            "left join fetch o.optionType " +
            "left join fetch p.detail " +
            "left join fetch p.stats " +
            "left join fetch p.tag")
    List<Product> findAllWithFetch();

    @Query("select distinct p from Product p " +
            "left join fetch p.options o " +
            "left join fetch o.optionType " +
            "left join fetch p.detail " +
            "left join fetch p.stats " +
            "left join fetch p.tag " +
            "where p.id = :id")
    Optional<Product> findByIdWithFetch(@Param("id") Long id);

    @Query("select distinct r from ProductReview r " +
            "left join fetch r.images " +
            "left join fetch r.files " +
            "where r.product.id = :productId")
    List<ProductReview> findReviewsByProductId(@Param("productId") Long productId);

    @Query("select distinct q from ProductQuestion q " +
            "left join fetch q.images " +
            "left join fetch q.files " +
            "where q.product.id = :productId")
    List<ProductQuestion> findQuestionsByProductId(@Param("productId") Long productId);
}
