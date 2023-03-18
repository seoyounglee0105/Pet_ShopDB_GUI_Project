package com.mungyang.service;

import java.util.ArrayList;

import com.mungyang.dao.CartDAO;
import com.mungyang.dto.CartDTO;
import com.mungyang.dto.MemberDTO;

public class CartService {
	
	private CartDAO cartDAO;
	
	public CartService() {
		cartDAO = new CartDAO();
	}

	// 장바구니에 상품 추가 로직
	public int addProduct(MemberDTO memberDTO, CartDTO cartDto) {
		int result = 0;
		
		// 이미 장바구니에 해당 상품이 존재한다면
		// 수량 몇 개가 이미 존재하는지 알려주고,
		// 현재 추가하려는 수량 만큼 더 추가하시겠습니까? 물어보기
		if (viewCartByProductId(memberDTO, cartDto.getProductId()) != null) {
			result = 2;
			return result;
		// 이미 장바구니의 상품 개수가 10개이면 추가하지 않음
		} else if (viewCart(memberDTO).size() == 10) {
			result = 3;
			return result;
		}
		
		// 성공하면 1 반환
		result = cartDAO.insert(cartDto);
		return result;
	}
	
	// 장바구니에 이미 존재하는 상품의 수량 추가 로직
	public int updateAmount(int amountValue, CartDTO cartDTO) {
		int result = 0;
		
		result = cartDAO.update(amountValue, cartDTO);
		return result;
	}
	
	// 회원의 장바구니 조회 로직
	public ArrayList<CartDTO> viewCart(MemberDTO memberDTO){
		ArrayList<CartDTO> resultList = null;
		
		resultList = cartDAO.select(memberDTO.getId());
		return resultList;
	}
	
	// 회원의 장바구니에 해당 상품의 존재 여부 조회 로직
	public CartDTO viewCartByProductId(MemberDTO memberDTO, int productId) {
		CartDTO resultDto = null;
		
		resultDto = cartDAO.select(memberDTO.getId(), productId);
		return resultDto;		
	}
	
	// 장바구니에서 상품 삭제 로직
	public int deleteProduct(CartDTO cartDTO) {
		int result = 0;
		
		result = cartDAO.delete(cartDTO);
		return result;
	}
	
}
