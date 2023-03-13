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

	// 회원 가입 기능
	@Override
	public int memberSignUp(MemberDTO dto) {
		int result = 0; // 초기화
		
		String sql = " INSERT INTO member(id, password, name, phone_number, address) " + "VALUES (?, ?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone_number());
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
	}

	// 아이디 중복 확인 기능
	@Override
	public int memberIdCheck(String id) {
		int result = 0; // 초기화
		
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

	// 로그인 기능
	@Override
	public int memberLogin(String id, String password) {
		int result = 0; // 초기화
		
		String sql = " SELECT * FROM member WHERE id = ? AND password = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				result = 1;
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

	// 비밀번호 찾기 기능
	@Override
	public String memberPwFind(String id, String phoneNumber) {
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
	}

	// 전화번호 중복 확인 기능
	@Override
	public int memberPhoneCheck(String phoneNumber) {
		int result = 0; // 초기화
		
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

}
