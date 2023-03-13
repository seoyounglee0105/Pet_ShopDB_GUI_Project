package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.dto.MemberDTO;
import project.utils.DBHelper;

public class MemberDAO implements IMemberDAO {

	private DBHelper dbHelper;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDAO() {
		dbHelper = DBHelper.getInstance();
		conn = dbHelper.getConnection();
	}

	// 아이디 중복 확인 기능
	@Override
	public int selectById(String id) {
		int result = 0;
		
		String sql = " SELECT * FROM member WHERE id = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				result = 1; // 아이디 중복
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
		return result;
	}
	
	// 전화번호 중복 확인 기능
	@Override
	public int selectByPhoneNumber(String phoneNumber) {
		int result = 0;
		
		String sql = " SELECT * FROM member WHERE phone_number = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);
			rs = pstmt.executeQuery();

		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				result = 1; // 전화번호 중복
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
		return result;
	}

	// 회원 추가 (회원가입)
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

	// 아이디와 비밀번호를 알 때 (로그인)
	@Override
	public MemberDTO selectByIdAndPassword(String id, String password) {
		MemberDTO resultMemberDTO = null; // 초기화
		
		String sql = " SELECT * FROM member WHERE id = ? AND password = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultMemberDTO = new MemberDTO();
				resultMemberDTO.setId(rs.getString("id"));
				resultMemberDTO.setMemberGrade(rs.getString("member_grade"));
				resultMemberDTO.setName(rs.getString("name"));
				resultMemberDTO.setPhoneNumber(rs.getString("phone_number"));
				resultMemberDTO.setAddress(rs.getString("address"));
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
		return resultMemberDTO;
	} // end of selectByIdAndPassword

	// 비밀번호를 모르고, 아이디와 전화번호를 알 때 (비밀번호 찾기)
	@Override
	public String selectByIdAndPhoneNumber(String id, String phoneNumber) {
		String resultPw = null;
		
		String sql = " SELECT * FROM member WHERE id = ? AND phone_number = ? "; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, phoneNumber);
			rs = pstmt.executeQuery();

			// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultPw = rs.getString("password");
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
		return resultPw;
	} // end of selectByIdAndPhoneNumber
	
}
