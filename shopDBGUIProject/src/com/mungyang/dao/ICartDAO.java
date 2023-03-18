package com.mungyang.dao;

import java.util.ArrayList;

import com.mungyang.dto.CartDTO;

public interface ICartDAO {

	// insert
	int insert(CartDTO cartDTO);
	
	// select (조건 1개 - product 테이블과 조인)
	ArrayList<CartDTO> select(String memberId);
	
	// select (조건 2개)
	CartDTO select(String memberId, int productId);
	
	// update (수량 수정)
	int update(int amountValue, CartDTO CartDAO);
	
	// delete (조건 2개)
	int delete(CartDTO cartDTO);
}
