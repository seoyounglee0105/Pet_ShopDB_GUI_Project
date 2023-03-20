package com.mungyang.service;

import java.util.ArrayList;

import com.mungyang.dao.ReviewDAO;
import com.mungyang.dto.ReviewDTO;

public class ReviewService {

	private ReviewDAO reviewDAO;
	
	public ReviewService() {
		reviewDAO = new ReviewDAO();
	}
	
	// 리뷰 작성 로직
	public int writeReview(ReviewDTO reviewDTO) {
		
		int result = 0;
		
		result = reviewDAO.insert(reviewDTO);
		return result;
	}
	
	// 상품별 리뷰 조회 로직 (최근 리뷰 7개까지만)
	public ArrayList<ReviewDTO> selectReview(int productId) {
		ArrayList<ReviewDTO> resultList = null;
		
		resultList = reviewDAO.select("product_id", productId);		
		return resultList;
	}
	
	// 회원+상품별 리뷰 조회 로직
	public ReviewDTO selectReviewByMemberId(int productId, String memberId) {
		ReviewDTO resultdto = null;
		
		resultdto = reviewDAO.select(productId, memberId);		
		return resultdto;
	}
	
	// id를 사용해서 리뷰 정보 조회 로직
	public ReviewDTO selectReviewById(int reviewId) {
		ReviewDTO resultdto = null;
		
		resultdto = reviewDAO.select("id", reviewId).get(0);		
		return resultdto;
	}
	
}
