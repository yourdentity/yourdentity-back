package com.yourdentity.yourdentity.server.store.dto.response;

import com.yourdentity.yourdentity.server.store.dto.ProductOptionDto;
import com.yourdentity.yourdentity.server.store.dto.ProductQuestionDto;
import com.yourdentity.yourdentity.server.store.dto.ProductReviewDto;

import java.util.List;

public record ProductResponse(
        Long productId,
        String productName, //상품 이름
        String companyName,
        String status,
        String description, //상품 설명
        String imageUrl,
        Long price,
        Long viewCount,
        Long applyCount, //신청 수
        Long reviewCount,
        Long questionCount,
        String tagName, //태그(ex: 나다움)
        List<ProductOptionDto> productOptions,  // 상품 옵션(색상:레드, 이미지, 재고)
        String detailedTitle, // 상품 상세정보 제목
        String detailedContent, //상품 상세정보 내용
        List<ProductReviewDto> reviews,
        List<ProductQuestionDto> questions

) {
}
