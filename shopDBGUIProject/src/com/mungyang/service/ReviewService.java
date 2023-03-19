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
	
	// 상품별 리뷰 조회 로직
	public ArrayList<ReviewDTO> selectReview(int productId) {
		ArrayList<ReviewDTO> resultList = null;
		
		resultList = reviewDAO.select(productId);		
		return resultList;
	}
	
	// 회원+상품별 리뷰 조회 로직
	public ReviewDTO selectReviewByMemberId(int productId, String memberId) {
		ReviewDTO resultdto = null;
		
		resultdto = reviewDAO.select(productId, memberId);		
		return resultdto;
	}
	
}
