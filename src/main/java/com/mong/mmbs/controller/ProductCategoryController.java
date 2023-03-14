package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductAgeGetListResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductGenreGetListResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductGetResponseDto;
import com.mong.mmbs.service.ProductCategoryService;

@RestController
@RequestMapping(ApiMappingPattern.PRODUCT_CATEGORY)
public class ProductCategoryController<BookListServicevice> {
	
	@Autowired ProductCategoryService productCategoryService;

	private static final String GET_PRODUCT_AGE_LIST = "/bookList1/{produtAgeCategory}/{productSubAgeCategory}";
	private static final String GET_PRODUCT_GENRE_LIST = "/bookList2/{produtGenreCategory}/{productSubGenreCategory}";
	private static final String GET_PRODUCT = "/{productSeq}";
	
	@GetMapping(GET_PRODUCT_AGE_LIST)
	public ResponseDto<ProductAgeGetListResponseDto> getProductAgeList(@PathVariable("produtAgeCategory") String productAgeCategory, @PathVariable("productSubAgeCategory") String productSubAgeCategory) {
		ResponseDto<ProductAgeGetListResponseDto> response 
			= productCategoryService.getProductAgeList(productAgeCategory, productSubAgeCategory);
		return response;
	}
	
	@GetMapping(GET_PRODUCT_GENRE_LIST)
	public ResponseDto<ProductGenreGetListResponseDto> getProductGenreList(@PathVariable("produtGenreCategory") String productGenreCategory, @PathVariable("productSubGenreCategory") String productSubGenreCategory) {
		ResponseDto<ProductGenreGetListResponseDto> response
			= productCategoryService.getProductGenreList(productGenreCategory, productSubGenreCategory);
		return response;
	}
	
	@GetMapping(GET_PRODUCT)
	public ResponseDto<ProductGetResponseDto> getProduct(@PathVariable("productSeq") int productSeq) {
		ResponseDto<ProductGetResponseDto> response 
			= productCategoryService.getProduct(productSeq);
		return response;
	}

}