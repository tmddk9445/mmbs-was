package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.DtlLikepageDto;
import com.mong.mmbs.dto.SearchDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired ProductRepository productRepository;

    public ResponseDto<?> Bestseller (){
        List<ProductEntity> ProductBast=null;
        try {
            ProductBast= productRepository.findTop10ByOrderByProductLikeDesc();
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        
        return ResponseDto.setSuccess("성공", ProductBast);
    }

    public ResponseDto<?> MainImage(){
        List<ProductEntity> BastImage = null;
        try {
            BastImage = productRepository.findAll();
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("성공", BastImage);
    }

    public ResponseDto<?> dtlPage(int productSeq){
		
		ProductEntity product=null;

		try {
			product=productRepository.findByProductSeq(productSeq);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", product);
	}

//	상세페이지 좋아요
	public ResponseDto<?> dtllikePage(DtlLikepageDto dto ) {
		int productSeq = dto.getProductSeq();
		ProductEntity productEntity = null;
		try {
			productEntity = productRepository.findByProductSeq(productSeq);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		productEntity.setProductLike(productEntity.getProductLike() + 1);

		try {
			productRepository.save(productEntity);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", null);
		
	}

    public ResponseDto<?> search(SearchDto dto) {
        String productTitle = dto.getProductTitle();
        List<ProductEntity> postsList =null;
        try {
            postsList= productRepository.findByProductTitleContaining(productTitle);
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("성공", postsList);
    }
}
