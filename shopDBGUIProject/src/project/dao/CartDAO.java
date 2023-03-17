package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import project.dto.CartDTO;
import project.utils.DBHelper;

public class CartDAO implements ICartDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public CartDAO() {
		conn = DBHelper.getInstance().getConnection();
	}
	
	@Override
	public int insert(CartDTO cartDTO) {
		int result = 0;
		String sql = " INSERT INTO cart VALUES (?, ?, ?) "; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartDTO.getMemberId());
			pstmt.setInt(2, cartDTO.getProductId());
			pstmt.setInt(3, cartDTO.getAmount());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	} // end of insert

	// 조인
	@Override
	public ArrayList<CartDTO> select(String memberId) {
		ArrayList<CartDTO> resultList = new ArrayList<>();
		String sql = " SELECT c.member_id, c.product_id, p.name, c.amount, (c.amount * p.price) as 'totalPrice' FROM cart AS c "
				   + " LEFT JOIN product AS p ON c.product_id = p.id "
				   + " WHERE c.member_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setMemberId(rs.getString("member_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setTotalPrice(rs.getInt("totalPrice"));
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
	public CartDTO select(String memberId, int productId) {
		CartDTO resultDto = null;
		String sql = " SELECT * FROM cart WHERE member_id = ? AND product_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultDto = new CartDTO();
				resultDto.setMemberId(rs.getString("member_id"));
				resultDto.setProductId(rs.getInt("product_id"));
				resultDto.setAmount(rs.getInt("amount"));
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
		return resultDto;
	}

	@Override
	public int update(int amountValue, CartDTO cartDTO) {
		int result = 0;
		String sql = " UPDATE cart SET amount = ? WHERE member_id = ? AND product_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amountValue);
			pstmt.setString(2, cartDTO.getMemberId());
			pstmt.setInt(3, cartDTO.getProductId());
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
	public int delete(CartDTO cartDTO) {
		int result = 0;
		String sql = " DELETE FROM cart WHERE member_id = ? AND product_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartDTO.getMemberId());
			pstmt.setInt(2, cartDTO.getProductId());
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


}
