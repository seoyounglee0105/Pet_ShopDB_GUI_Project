package com.mungyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mungyang.dto.ReviewDTO;
import com.mungyang.utils.DBHelper;

public class ReviewDAO implements IReviewDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ReviewDAO() {
		conn = DBHelper.getInstance().getConnection();
	}

	@Override
	public int insert(ReviewDTO reviewDTO) {
		int result = 0;
		String sql = null;
		int temp = 0;
		
		// 사진이 없는 경우
		if (reviewDTO.getPhoto() == null) {
			sql = " INSERT INTO review(writer_id, product_id, star, title, content) VALUES (?, ?, ?, ?, ?) ";
		// 사진이 있는 경우
		} else {
			temp = 1;
			sql = " INSERT INTO review(writer_id, product_id, star, title, content, photo) VALUES (?, ?, ?, ?, ?, ?) ";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getWriterId());
			pstmt.setInt(2, reviewDTO.getProductId());
			pstmt.setInt(3, reviewDTO.getStar());
			pstmt.setString(4, reviewDTO.getTitle());
			pstmt.setString(5, reviewDTO.getContent());
			if (temp == 1) {
				pstmt.setString(6, reviewDTO.getPhoto());
			}
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

	// (최근 리뷰 7개까지만)
	@Override
	public ArrayList<ReviewDTO> select(String column, int value) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM review WHERE " + column + " = ? ORDER BY id DESC LIMIT 7";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setId(rs.getInt("id"));
				dto.setWriterId(rs.getString("writer_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setStar(rs.getInt("star"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				try {
					dto.setPhoto(rs.getString("photo"));
				} catch (Exception e) {
					System.out.println("사진 미등록");
				}
				resultList.add(dto);
			}
			
		} catch (SQLException e) {
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

	// 리뷰는 하나의 상품에 대해서는 1회만 작성 가능
	@Override
	public ReviewDTO select(int productId, String memberId) {
		ReviewDTO resultdto = null;
		String sql = " SELECT * FROM review WHERE product_id = ? AND writer_id = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			pstmt.setString(2, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultdto = new ReviewDTO();
				resultdto.setId(rs.getInt("id"));
				resultdto.setWriterId(rs.getString("writer_id"));
				resultdto.setProductId(rs.getInt("product_id"));
				resultdto.setStar(rs.getInt("star"));
				resultdto.setTitle(rs.getString("title"));
				resultdto.setContent(rs.getString("content"));
				try {
					resultdto.setPhoto(rs.getString("photo"));
				} catch (Exception e) {
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultdto;
	}
	
	
	
}
