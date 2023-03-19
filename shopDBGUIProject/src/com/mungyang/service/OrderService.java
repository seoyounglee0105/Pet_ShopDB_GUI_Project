package com.mungyang.service;

import java.util.ArrayList;

import com.mungyang.dao.OrderDAO;
import com.mungyang.dto.OrderDTO;

public class OrderService {

	private OrderDAO orderDAO;
	
	public OrderService() {
		orderDAO = new OrderDAO();
	}
	
	// 주문 추가 로직 처리
	public int addOrder(OrderDTO orderDTO) {
		int result = 0;
		
		result = orderDAO.insert(orderDTO);
		return result;
	}
	
	// 회원별 주문 조회
	public ArrayList<OrderDTO> selectOrder(String memberId){
		ArrayList<OrderDTO> resultList = new ArrayList<>();
		
		resultList = orderDAO.select(memberId);
		return resultList;
	}
	
	// 회원이 해당 상품에 주문한 내역이 있는지 조회
	public boolean checkOrder(String memberId, int productId) {
		boolean result = false;
		ArrayList<OrderDTO> list = orderDAO.select(memberId, productId);
		if (list.size() != 0) {
			result = true;
		}
		
		return result; // 주문 내역 없으면 false 반환
	}
	
}
