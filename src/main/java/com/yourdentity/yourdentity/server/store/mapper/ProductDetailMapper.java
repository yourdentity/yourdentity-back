package com.yourdentity.yourdentity.server.store.mapper;


import com.yourdentity.yourdentity.server.store.dto.ProductOptionDto;
import com.yourdentity.yourdentity.server.store.dto.ProductQuestionDto;
import com.yourdentity.yourdentity.server.store.dto.ProductReviewDto;
import com.yourdentity.yourdentity.server.store.dto.response.ProductListResponse;
import com.yourdentity.yourdentity.server.store.dto.response.ProductResponse;
import com.yourdentity.yourdentity.server.store.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductDetailMapper
{
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "tagName", source = "tag.tagName")
    @Mapping(target = "viewCount", source = "stats.viewCount")
    @Mapping(target = "applyCount", source = "stats.applyCount")
    @Mapping(target = "detailedTitle", source = "detail.title")
    @Mapping(target = "detailedContent", source = "detail.content")
    @Mapping(target = "productOptions", source = "options")
    @Mapping(target = "reviews",source = "reviews")
    @Mapping(target = "questions",source = "questions")
    ProductResponse toResponse(Product product);

    @Mapping(target = "optionName", source = "optionType.name")
    @Mapping(target = "optionValue", source = "value")
    @Mapping(target = "optionImageUrl", source = "imageUrl")
    @Mapping(target = "stock", source = "stock")
    ProductOptionDto toOptionDto(ProductOption productOption);

    @Mapping(target = "reviewId", source = "id")
    @Mapping(target = "imageUrls", expression = "java(mapReviewImages(productReview.getImages()))")
    @Mapping(target = "fileUrls", expression = "java(mapReviewFiles(productReview.getFiles()))")
    ProductReviewDto toReviewDto(ProductReview productReview);

    @Mapping(target = "questionId", source = "id")
    @Mapping(target = "imageUrls", expression = "java(mapQuestionImages(productQuestion.getImages()))")
    @Mapping(target = "fileUrls", expression = "java(mapQuestionFiles(productQuestion.getFiles()))")
    ProductQuestionDto toQuestionDto(ProductQuestion productQuestion);


    List<ProductOptionDto> toOptionDtos(List<ProductOption> options);
    List<ProductReviewDto> toReviewDtos(List<ProductReview> productReviews);
    List<ProductQuestionDto> toQuestionDtos(List<ProductQuestion> productQuestions);

    default List<String> mapReviewImages(Set<ProductReviewImage> images) {
        if (images == null) return List.of();
        return images.stream()
                .map(ProductReviewImage::getImageUrl)
                .toList();
    }

    default List<String> mapReviewFiles(Set<ProductReviewFile> files) {
        if (files == null) return List.of();
        return files.stream()
                .map(ProductReviewFile::getFileUrl)
                .toList();
    }

    default List<String> mapQuestionImages(Set<ProductQuestionImage> images) {
        if (images == null) return List.of();
        return images.stream()
                .map(ProductQuestionImage::getImageUrl)
                .toList();
    }

    default List<String> mapQuestionFiles(Set<ProductQuestionFile> files) {
        if (files == null) return List.of();
        return files.stream()
                .map(ProductQuestionFile::getFileUrl)
                .toList();
    }
}
