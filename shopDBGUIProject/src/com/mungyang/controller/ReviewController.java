package com.mungyang.controller;

import java.util.ArrayList;

import com.mungyang.dto.ReviewDTO;
import com.mungyang.service.OrderService;
import com.mungyang.service.ReviewService;
import com.mungyang.viewFrame.ShopFrame;

public class ReviewController {

	private ReviewService reviewService;
	private OrderService orderService;
	
	public ReviewController() {
		reviewService = new ReviewService();
		orderService = new OrderService();
	}
	
	// 리뷰 작성 요청
	public int requestWriteReview(ReviewDTO reviewDTO) {
		int response = 0;
		// 작성 권한 확인 : 구매 이력이 있는지
		String writerId = reviewDTO.getWriterId();
		int productId = reviewDTO.getProductId();
		
		// 구매 이력이 없으면 작성 불가능
		if (orderService.checkOrder(writerId, productId) == false) {
			response = 2;
			return response;
		}
		
		response = reviewService.writeReview(reviewDTO);
		return response;
	}
	
	// 상품별 리뷰 조회 요청 (최근 리뷰 7개까지만)
	public ArrayList<ReviewDTO> requestSelectReview(int productId){
		ArrayList<ReviewDTO> responseList = null;
		
		responseList = reviewService.selectReview(productId);
		return responseList;
	}
	
	// 회원+상품별 리뷰가 존재하는지 확인 요청
	public boolean requestCheckReview(int productId, String writerId) {
		boolean response = false;
		
		ReviewDTO reviewDTO = reviewService.selectReviewByMemberId(productId, writerId);
		// 존재한다면 true 반환
		if (reviewDTO != null) {
			response = true;			
		}
		return response;
	}
	
	// id로 리뷰 내용 확인 요청
	public ReviewDTO requestReviewInfo(int reviewId) {
		ReviewDTO responseDto = null;
		responseDto = reviewService.selectReviewById(reviewId);
		return responseDto;
	}
}
