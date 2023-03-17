package project.controller;

import java.util.ArrayList;

import project.dto.CartDTO;
import project.dto.MemberDTO;
import project.service.CartService;

public class CartController {

	private CartService cartService;
	
	public CartController() {
		cartService = new CartService();
	}
	
	// 장바구니 추가 요청
	public int requestAddProduct(MemberDTO memberDTO, CartDTO cartDTO) {
		int responseType = 0;
		
		// 성공 : 1, 상품 중복 : 2 반환
		responseType = cartService.addProduct(memberDTO, cartDTO);
		return responseType;
	}
	
	// 장바구니 조회 요청
	public ArrayList<CartDTO> requestViewCart(MemberDTO memberDTO) {
 		ArrayList<CartDTO> responseList = null;
 		
 		responseList = cartService.viewCart(memberDTO);
 		return responseList;
	}
	
	// 회원의 장바구니에 해당 상품의 존재 여부 조회 요청
	public CartDTO requestViewCartByProductId(MemberDTO memberDTO, int productId) {
		CartDTO responseDto = null;
		
		responseDto = cartService.viewCartByProductId(memberDTO, productId);
		return responseDto;
	}
	
	// 장바구니 수량 수정 요청
	public int requestUpdateAmount(int amountValue, CartDTO cartDTO) {
		int responseType = 0;
		
		responseType = cartService.updateAmount(amountValue, cartDTO);
		return responseType;
	}
	
	// 장바구니 삭제 요청
	public int requestDeleteProduct(CartDTO cartDTO) {
		int responseType = 0;
		
		responseType = cartService.deleteProduct(cartDTO);
		return responseType;
	}
	
}
