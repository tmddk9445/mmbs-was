package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductAgeGetListResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductGenreGetListResponseDto;
import com.mong.mmbs.dto.response.productCategory.ProductGetResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class ProductCategoryService {
	
	@Autowired ProductRepository productRepository;
	
	public ResponseDto<ProductAgeGetListResponseDto> getProductAgeList(String productAgeCategory, String productSubAgeCategory) {
		
		ProductAgeGetListResponseDto data = null;

		List<ProductEntity> productAgeList = null;
	
		if(productSubAgeCategory.equals("0"))

			try {

				productAgeList = productRepository.findByProductAge(productAgeCategory);
				data = new ProductAgeGetListResponseDto(productAgeList);

			} catch (Exception exception) {
				exception.printStackTrace();
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

		else 

			try {

				productAgeList = productRepository.findByProductAgeAndProductSubAge(productAgeCategory, productSubAgeCategory);
				data = new ProductAgeGetListResponseDto(productAgeList);

			}catch (Exception exception) {
				exception.printStackTrace();
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}
		
	public ResponseDto<ProductGenreGetListResponseDto> getProductGenreList(String productGenreCategory, String productSubGenreCategory) {
		
		ProductGenreGetListResponseDto data = null;

		List<ProductEntity> productGenreList = null; 
		
		if(productSubGenreCategory.equals("0"))

			try {

				productGenreList = productRepository.findByProductGenre(productGenreCategory);
				
				data = new ProductGenreGetListResponseDto(productGenreList);

			}catch (Exception exception) {
				exception.printStackTrace();
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

		else 

			try {

				productGenreList = productRepository.findByProductGenreAndProductSubGenre(productGenreCategory, productSubGenreCategory);
			
				data = new ProductGenreGetListResponseDto(productGenreList);

			}catch (Exception exception) {
				exception.printStackTrace();
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

			return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}

	public ResponseDto<ProductGetResponseDto> getProduct(int productSeq) {

		ProductGetResponseDto data = null;
		
		try {

			ProductEntity productEntity = productRepository.findByProductSeq(productSeq);

			data = new ProductGetResponseDto(productEntity);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}
	
}