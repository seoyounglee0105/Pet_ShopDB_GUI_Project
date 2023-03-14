package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.dto.MemberDTO;
import project.utils.DBHelper;

public class MemberDAO implements IMemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDAO() {
		conn = DBHelper.getInstance().getConnection();
	}

	// INSERT
	@Override
	public int insert(MemberDTO dto) {
		int result = 0; // 초기화
		
		String sql = " INSERT INTO member(id, password, name, phone_number, address) " + "VALUES (?, ?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhoneNumber());
			pstmt.setString(5, dto.getAddress());
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
	} // end of insert
	
	// 하나의 조건을 이용한 SELECT
	@Override
	public ArrayList<MemberDTO> select(String columnName, String columnValue) {
		ArrayList<MemberDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM member WHERE " + columnName + " = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, columnValue);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setMemberGrade(rs.getString("member_grade"));
				dto.setName(rs.getString("name"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setAddress(rs.getString("address"));
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

	// 두 개의 조건을 이용한 SELECT (완전히 식별하는 목적이므로 단일 객체 반환)
	@Override
	public MemberDTO select(String firstColumn, String firstValue, String secondColumn, String secondValue) {
		MemberDTO resultDto = null;
		String sql = " SELECT * FROM member WHERE " + firstColumn + " = ? AND " + secondColumn + " = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstValue);
			pstmt.setString(2, secondValue);
			rs = pstmt.executeQuery();
			
			// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultDto = new MemberDTO();
				resultDto.setId(rs.getString("id"));
				resultDto.setPassword(rs.getString("password"));
				resultDto.setMemberGrade(rs.getString("member_grade"));
				resultDto.setName(rs.getString("name"));
				resultDto.setPhoneNumber(rs.getString("phone_number"));
				resultDto.setAddress(rs.getString("address"));
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
	} // end of select (조건 2개)
	
}
