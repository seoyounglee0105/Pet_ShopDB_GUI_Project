package com.mungyang.dao;

import java.util.ArrayList;

import com.mungyang.dto.OrderDTO;

public interface IOrderDAO {

	// insert
	int insert(OrderDTO orderDTO);
	
	// select
	ArrayList<OrderDTO> select(String memberId);
	
}
