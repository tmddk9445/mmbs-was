package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.DtlLikepageDto;
import com.mong.mmbs.dto.SearchDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.repository.ProductRepository;
import com.mong.mmbs.service.ProductService;

@RestController
@RequestMapping("/apis/product")
public class ProductController {
    
    @Autowired ProductService productService;
    @Autowired ProductRepository productRepository;
    
    @GetMapping("/best")
    public ResponseDto<?> Bestseller(){
        return productService.Bestseller();
    }

    @GetMapping("/Image")
    public ResponseDto<?>MainImage(){
        return productService.MainImage();
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

    @PostMapping("/search")
    public ResponseDto<?> search(@RequestBody SearchDto requsetBody) {
        ResponseDto<?> result = productService.search(requsetBody);
        return result;
    }
}
