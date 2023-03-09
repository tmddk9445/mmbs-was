package com.mong.mmbs.dto.response.product;

import com.mong.mmbs.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLikePostResponseDto {

  private int productSeq;
//	장르 [국내도서, 외국도서, 전집, 토이/교구, 부모님의 서재]
	private String productGenre;
//	세부 장르
	private String productSubGenre;
//	고유번호
	private int productIsbn;
//	제목s
	private String productTitle;
//	저자
	private String productWriter;
//	출판사
	private String productPublisher;
//	연령 [0~3세, 4~7세, 부모]
	private String productAge;
//	연령 세부 장르
	private String productSubAge;
//	출판일
	private String productPublicationDate;
//	가격
	private int productPrice;
//	할인가격
	private int productSalesPrice;
//	재고
	private int productStock;
//	좋아요
	private int productLike;
//	상세 설명
	private String productIntroduceDtl;
//	이미지
	private String productImageUrl;
//	판매량
	private int productSalesCount;

  public ProductLikePostResponseDto(ProductEntity productEntity) {

      this.productSeq = productEntity.getProductSeq();
      this.productGenre = productEntity.getProductGenre();
      this.productSubGenre = productEntity.getProductSubGenre();
      this.productIsbn = productEntity.getProductIsbn();
      this.productTitle = productEntity.getProductTitle();
      this.productWriter = productEntity.getProductWriter();
      this.productPublisher = productEntity.getProductPublisher();
      this.productAge = productEntity.getProductAge();
      this.productSubAge = productEntity.getProductSubAge();
      this.productPublicationDate = productEntity.getProductPublicationDate();
      this.productPrice = productEntity.getProductPrice();
      this.productSalesPrice = productEntity.getProductSalesPrice();
      this.productStock = productEntity.getProductStock();
      this.productLike = productEntity.getProductLike();
      this.productIntroduceDtl = productEntity.getProductIntroduceDtl();
      this.productImageUrl = productEntity.getProductImageUrl();
      this.productSalesCount = productEntity.getProductSalesCount();

  }
  
}
