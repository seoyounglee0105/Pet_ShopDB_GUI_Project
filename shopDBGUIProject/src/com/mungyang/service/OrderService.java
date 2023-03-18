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
	
	// 주문 조회
	public ArrayList<OrderDTO> selectOrder(String memberId){
		ArrayList<OrderDTO> resultList = new ArrayList<>();
		
		resultList = orderDAO.select(memberId);
		return resultList;
	}
	
}
