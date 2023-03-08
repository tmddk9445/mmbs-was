package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.DtlLikepageDto;
import com.mong.mmbs.dto.request.product.ProductSearchPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.product.ProductSearchPostResponseDto;
import com.mong.mmbs.repository.ProductRepository;
import com.mong.mmbs.service.ProductService;

@RestController
@RequestMapping(ApiMappingPattern.PRODUCT)
public class ProductController {
    
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;

    private static final String PRODUCT_POST_FIND = "/findProduct";
    private static final String BEST_SELLER = "/bestSeller";

    @PostMapping(PRODUCT_POST_FIND)
    public ResponseDto<ProductSearchPostResponseDto> postProductSearch(@RequestBody ProductSearchPostRequestDto requestBody) {
        ResponseDto<ProductSearchPostResponseDto> result 
            = productService.searchPostProduct(requestBody);
        return result;
    }
    
    @GetMapping(BEST_SELLER)
    public ResponseDto<?> bestSeller(){

        return productService.bestSeller();
    }

    @GetMapping("/Image")
    public ResponseDto<?> mainImage(){
        return productService.mainImage();
    }

    @GetMapping("/dtlPage/{productSeq}")
	public ResponseDto<?>dtlPage(@PathVariable("productSeq")int productSeq){
	return productService.dtlPage(productSeq);
	}
    
	@PostMapping("/dtlLikePage")
	public ResponseDto<?>dtllikePage(@RequestBody DtlLikepageDto requestbody){
		ResponseDto<?> LikePage = productService.dtllikePage(requestbody);
		return LikePage;
	}


}
