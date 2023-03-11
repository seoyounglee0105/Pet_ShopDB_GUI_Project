package ex05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO implements IMemberDAO {

	private DBHelper dbHelper;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result;

	public MemberDAO() {
		dbHelper = DBHelper.getInstance();
		conn = dbHelper.getConnection();
	}

	// 회원 가입 기능
	@Override
	public int memberSignUp(String id, String password, String name, String phoneNumber, String address) {
		result = 0; // 초기화
		
		String sql = " INSERT INTO member(id, password, name, phone_number, address) " + "VALUES (?, ?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, address);
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
	public int memberIdCheck(String id) {
		result = 0; // 초기화
		
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

	@Override
	public int memberLogin(String id, String password) {
		result = 0; // 초기화
		
		String sql = " SELECT * FROM member " + " WHERE id = ? AND password = ? ";

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

}
