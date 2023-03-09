package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.product.ProductLikePostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.product.BestSellerGetResponseDto;
import com.mong.mmbs.dto.response.product.ProductDetailGetProductSeqResponseDto;
import com.mong.mmbs.dto.response.product.ProductLikePostResponseDto;
import com.mong.mmbs.dto.response.product.ProductSearchGetResponseDto;
import com.mong.mmbs.dto.response.product.RandomProductImageGetResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired ProductRepository productRepository;

	public ResponseDto<ProductLikePostResponseDto> postPoductLike(ProductLikePostRequestDto dto) {

        ProductLikePostResponseDto data = null;
		int productSeq = dto.getProductSeq();

		try {

			ProductEntity productEntity = productRepository.findByProductSeq(productSeq);
            productEntity.setProductLike(productEntity.getProductLike() + 1);

            productRepository.save(productEntity);

            data = new ProductLikePostResponseDto(productEntity);

		} catch (Exception exception) {
            exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
		
	}

    public ResponseDto<ProductSearchGetResponseDto> getProductSearch(String productTitle) {

        ProductSearchGetResponseDto data = null;

        try {

            List<ProductEntity> productList = productRepository.findByProductTitleContaining(productTitle);

            data = new ProductSearchGetResponseDto(productList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }

    public ResponseDto<BestSellerGetResponseDto> getBestSeller() {

        BestSellerGetResponseDto data = null;

        try {

            List<ProductEntity> bestSellerList = productRepository.findTop10ByOrderByProductLikeDesc();

            data = new BestSellerGetResponseDto(bestSellerList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<RandomProductImageGetResponseDto> getRandomProductImage(){

        RandomProductImageGetResponseDto data = null;
        try {

            List<ProductEntity> randomProductImageList = productRepository.findAll();
            
            data = new RandomProductImageGetResponseDto(randomProductImageList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ProductDetailGetProductSeqResponseDto> getProductDetailPage(int productSeq){
		
        ProductDetailGetProductSeqResponseDto data = null;

		try {

			ProductEntity productEntity = productRepository.findByProductSeq(productSeq);

            data = new ProductDetailGetProductSeqResponseDto(productEntity);

		} catch (Exception exception) {
            exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

}
