package project.service;

import project.dao.OrderDAO;
import project.dto.OrderDTO;

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
	
}
