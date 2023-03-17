package project.dao;

import java.util.ArrayList;

import project.dto.OrderDTO;

public interface IOrderDAO {

	// insert
	int insert(OrderDTO orderDTO);
	
	// select
	ArrayList<OrderDTO> select(String memberId);
	
}
