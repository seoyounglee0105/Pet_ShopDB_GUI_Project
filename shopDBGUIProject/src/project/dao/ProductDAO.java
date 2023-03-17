package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.dto.ProductDTO;
import project.utils.DBHelper;

public class ProductDAO implements IProductDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProductDAO() {
		conn = DBHelper.getInstance().getConnection();
	}

	// 전체 SELECT
	@Override
	public ArrayList<ProductDTO> select() {
		ArrayList<ProductDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM product ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setMainPhoto(rs.getString("main_photo"));
				dto.setSubPhoto(rs.getString("sub_photo"));
				dto.setSales(rs.getInt("sales"));
				dto.setInsertDate(rs.getString("insert_date"));
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
	} // end of select (전체)

	// 조건 1개 SELECT
	@Override
	public ArrayList<ProductDTO> select(String columnName, int value) {
		ArrayList<ProductDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM product WHERE " + columnName + " = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setMainPhoto(rs.getString("main_photo"));
				dto.setSubPhoto(rs.getString("sub_photo"));
				dto.setSales(rs.getInt("sales"));
				dto.setInsertDate(rs.getString("insert_date"));
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
	} // end of select (조건 1개)
	
	@Override
	public ProductDTO select(String columnName, String value) {
		ProductDTO resultDto = null;
		String sql = " SELECT * FROM product WHERE " + columnName + " = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultDto = new ProductDTO();
				resultDto.setId(rs.getInt("id"));
				resultDto.setName(rs.getString("name"));
				resultDto.setPrice(rs.getInt("price"));
				resultDto.setCategoryId(rs.getInt("category_id"));
				resultDto.setMainPhoto(rs.getString("main_photo"));
				resultDto.setSubPhoto(rs.getString("sub_photo"));
				resultDto.setSales(rs.getInt("sales"));
				resultDto.setInsertDate(rs.getString("insert_date"));
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
		return resultDto;
	}

	@Override
	public ArrayList<ProductDTO> selectOrdeyBy(int orderIndex) {
		ArrayList<ProductDTO> resultList = new ArrayList<>();
		String sql = null;
		
		if (orderIndex == 0) {
			sql = " SELECT * FROM product ORDER BY name ";
		} else if (orderIndex == 1) {
			sql = " SELECT * FROM product ORDER BY insert_date DESC ";			
		} else {
			sql = " SELECT * FROM product ORDER BY sales DESC ";						
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setMainPhoto(rs.getString("main_photo"));
				dto.setSubPhoto(rs.getString("sub_photo"));
				dto.setSales(rs.getInt("sales"));
				dto.setInsertDate(rs.getString("insert_date"));
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

	@Override
	public ArrayList<ProductDTO> selectOrdeyBy(int orderIndex, String columnName, int value) {
		ArrayList<ProductDTO> resultList = new ArrayList<>();
		String sql = null;
		
		if (orderIndex == 0) {
			sql = " SELECT * FROM product WHERE " + columnName + " = ? ORDER BY name ";
		} else if (orderIndex == 1) {
			sql = " SELECT * FROM product WHERE " + columnName + " = ? ORDER BY insert_date DESC ";			
		} else {
			sql = " SELECT * FROM product WHERE " + columnName + " = ? ORDER BY sales DESC ";						
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setMainPhoto(rs.getString("main_photo"));
				dto.setSubPhoto(rs.getString("sub_photo"));
				dto.setSales(rs.getInt("sales"));
				dto.setInsertDate(rs.getString("insert_date"));
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





}
