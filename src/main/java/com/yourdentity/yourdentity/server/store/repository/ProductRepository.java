package com.yourdentity.yourdentity.server.store.repository;

import com.yourdentity.yourdentity.server.store.entity.Product;
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
    Optional<List<Product>> findAllWithOptionsAndDescriptionsAndTags();

    @Query("select distinct p from Product p " +
            "left join fetch p.options o "+
            "left join fetch o.optionType " +
            "left join fetch p.detail " +
            "left join fetch p.stats " +
            "left join fetch p.tag " +
            "where p.id = :id")
    Optional<Product> findByIdWithOptionsAndDescriptionsAndTags(@Param("id") Long id);
}
