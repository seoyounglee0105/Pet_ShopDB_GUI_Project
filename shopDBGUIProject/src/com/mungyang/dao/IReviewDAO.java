package com.mungyang.dao;

import java.util.ArrayList;

import com.mungyang.dto.ReviewDTO;

public interface IReviewDAO {

	// insert
	int insert (ReviewDTO reviewDTO);
	
	// select
	ArrayList<ReviewDTO> select(String column, int value);
	ReviewDTO select(int productId, String memberId);
	
}
