package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.request.product.ProductLikePostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.product.BestSellerGetResponseDto;
import com.mong.mmbs.dto.response.product.ProductDetailGetProductSeqResponseDto;
import com.mong.mmbs.dto.response.product.ProductLikePostResponseDto;
import com.mong.mmbs.dto.response.product.ProductSearchGetResponseDto;
import com.mong.mmbs.dto.response.product.RandomProductImageGetResponseDto;
import com.mong.mmbs.repository.ProductRepository;
import com.mong.mmbs.service.ProductService;

@RestController
@RequestMapping(ApiMappingPattern.PRODUCT)
public class ProductController {
    
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;

    private static final String POST_PRODUCT_LIKE = "/productLike";
    private static final String PRODUCT_GET_FIND = "/findProduct/{productTitle}";
    private static final String GET_BEST_SELLER = "/bestSeller";
    private static final String GET_RANDOM_PRODUCT = "/randomProduct";
    private static final String GET_PRODUCT_DETAIL_PRODUCTSEQ = "/productDetail/{productSeq}";
    
    @PostMapping(POST_PRODUCT_LIKE)
	public ResponseDto<ProductLikePostResponseDto> postPoductLike(@Valid @RequestBody ProductLikePostRequestDto requestBody){
		ResponseDto<ProductLikePostResponseDto> LikePage = productService.postPoductLike(requestBody);
		return LikePage;
	}

    @GetMapping(PRODUCT_GET_FIND)
    public ResponseDto<ProductSearchGetResponseDto> getProductSearch(@PathVariable("productTitle") String productTitle) {
        ResponseDto<ProductSearchGetResponseDto> result 
            = productService.getProductSearch(productTitle);
        return result;
    }
    
    @GetMapping(GET_BEST_SELLER)
    public ResponseDto<BestSellerGetResponseDto> getBestSeller(){
        ResponseDto<BestSellerGetResponseDto> response = productService.getBestSeller();
        return response;
    }

    @GetMapping(GET_RANDOM_PRODUCT)
    public ResponseDto<RandomProductImageGetResponseDto> getRandomProductImage(){
        ResponseDto<RandomProductImageGetResponseDto> response 
            = productService.getRandomProductImage();
        return response;
    }

    @GetMapping(GET_PRODUCT_DETAIL_PRODUCTSEQ)
	public ResponseDto<ProductDetailGetProductSeqResponseDto> getProductDetailPage(@PathVariable("productSeq") int productSeq){
	    ResponseDto<ProductDetailGetProductSeqResponseDto> response 
            = productService.getProductDetailPage(productSeq);
        return response; 
	}
    
}
