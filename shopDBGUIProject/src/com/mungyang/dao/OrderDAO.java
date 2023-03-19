package com.mungyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mungyang.dto.CartDTO;
import com.mungyang.dto.OrderDTO;
import com.mungyang.utils.DBHelper;

public class OrderDAO implements IOrderDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public OrderDAO() {
		conn = DBHelper.getInstance().getConnection();
	}
	
	@Override
	public int insert(OrderDTO orderDTO) {
		int result = 0;
		String sql = " INSERT INTO `order`(id, member_id, product_id, amount, order_date) "
				   + " VALUES (?, ?, ?, ?, NOW()) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderDTO.getId());
			pstmt.setString(2, orderDTO.getMemberId());
			pstmt.setInt(3, orderDTO.getProductId());
			pstmt.setInt(4, orderDTO.getAmount());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<OrderDTO> select(String memberId) {
		ArrayList<OrderDTO> resultList = new ArrayList<>();
		String sql = " SELECT o.id, o.member_id, o.product_id, p.name, o.amount, (o.amount * p.price) as 'totalPrice', o.order_date, o.state FROM `order` AS o "
				   + " LEFT JOIN product AS p ON o.product_id = p.id "
				   + " WHERE o.member_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setProductId(rs.getInt("id"));
				dto.setMemberId(rs.getString("member_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setTotalPrice(rs.getInt("totalPrice"));
				dto.setOrderDate(rs.getString("order_date"));
				dto.setState(rs.getInt("state"));				
				resultList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public ArrayList<OrderDTO> select(String memberId, int productId) {
		ArrayList<OrderDTO> resultList = new ArrayList<>();
		String sql = " SELECT o.id, o.member_id, o.product_id, p.name, o.amount, (o.amount * p.price) as 'totalPrice', o.order_date, o.state FROM `order` AS o "
				   + " LEFT JOIN product AS p ON o.product_id = p.id "
				   + " WHERE o.member_id = ? AND o.product_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setProductId(rs.getInt("id"));
				dto.setMemberId(rs.getString("member_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setTotalPrice(rs.getInt("totalPrice"));
				dto.setOrderDate(rs.getString("order_date"));
				dto.setState(rs.getInt("state"));				
				resultList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

}
