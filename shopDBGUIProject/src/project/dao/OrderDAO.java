package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.dto.OrderDTO;
import project.utils.DBHelper;

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
		// TODO Auto-generated method stub
		return null;
	}

}
