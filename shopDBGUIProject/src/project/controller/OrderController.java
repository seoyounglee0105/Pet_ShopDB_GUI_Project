package project.controller;

import project.dto.OrderDTO;
import project.service.OrderService;

public class OrderController {

	private OrderService orderService;
	
	public OrderController() {
		orderService = new OrderService();
	}
	
	// 주문 추가 요청
	public int requestAddOrder(OrderDTO orderDTO) {
		int response = 0;
		
		response = orderService.addOrder(orderDTO);
		return response;
	}
	
}
