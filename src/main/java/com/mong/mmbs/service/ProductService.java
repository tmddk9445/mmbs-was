package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.DtlLikepageDto;
import com.mong.mmbs.dto.request.product.ProductSearchPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.product.ProductSearchPostResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired ProductRepository productRepository;

    public ResponseDto<ProductSearchPostResponseDto> searchPostProduct(ProductSearchPostRequestDto dto) {

        ProductSearchPostResponseDto data = null;

        String productTitle = dto.getProductTitle();

        try {

            List<ProductEntity> productList = productRepository.findByProductTitleContaining(productTitle);

            data = new ProductSearchPostResponseDto(productList);

        } catch (Exception exception) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }

    public ResponseDto<?> bestSeller (){

        try {

            List<ProductEntity> ProductBest = productRepository.findTop10ByOrderByProductLikeDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    public ResponseDto<?> mainImage(){

        List<ProductEntity> BastImage = null;

        try {

            BastImage = productRepository.findAll();

        } catch (Exception exception) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, BastImage);
    }

    public ResponseDto<?> dtlPage(int productSeq){
		
		ProductEntity product=null;

		try {

			product=productRepository.findByProductSeq(productSeq);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, product);

	}

//	상세페이지 좋아요
	public ResponseDto<?> dtllikePage(DtlLikepageDto dto ) {

		int productSeq = dto.getProductSeq();
		ProductEntity productEntity = null;

		try {

			productEntity = productRepository.findByProductSeq(productSeq);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		productEntity.setProductLike(productEntity.getProductLike() + 1);

		try {

			productRepository.save(productEntity);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
		
	}

}
